package ru.karelin.tm.service;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.karelin.tm.api.repository.ProjectRepository;
import ru.karelin.tm.api.repository.TaskRepository;
import ru.karelin.tm.api.service.ProjectService;
import ru.karelin.tm.entity.Project;
import ru.karelin.tm.entity.Task;
import ru.karelin.tm.enumeration.Status;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public final class ProjectServiceImpl extends AbstractSecuredEntityService<Project> implements ProjectService {
    private final static String SERIALIZE_FILE_NAME = "projects.ser";
    private final static String JAX_XLM_FILE_NAME = "projects.xml";

    final private TaskRepository taskRepository;


    public ProjectServiceImpl(@NotNull final ProjectRepository projectRepository, @NotNull final TaskRepository taskRepository) {
        super(projectRepository);
        this.taskRepository = taskRepository;

    }

    @Override
    public void create(@NotNull final String userId, final String name, final String description, final Date startDate, final Date finishDate) {
        @NotNull final Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setStartDate(startDate);
        project.setFinishDate(finishDate);
        project.setUserId(userId);
        project.setStatus(Status.PLANNED);
        entityRepository.persist(project);
    }

    @Override
    public void edit(final String userId, final String id, final String name, final String description, final Date startDate, final Date finishDate, Status status) {
        @Nullable final Project project = entityRepository.findOneByIdAndUserId(id, userId);
        if (project != null) {
            if (!name.isEmpty()) project.setName(name);
            if (!description.isEmpty()) project.setDescription(description);
            if (startDate != null) project.setStartDate(startDate);
            if (finishDate != null) project.setFinishDate(finishDate);
            if (status!=null) project.setStatus(status);
            entityRepository.merge(project);
        }
    }

    @Override
    public List<Project> getSortedList(String userId, String sortField, boolean isStraight) {
        return entityRepository.findAllByUserId(userId, sortField, isStraight);
    }

    @Override
    public void remove(final String userId, final String projectID) {
        super.remove(userId, projectID);
        final List<Task> taskList = taskRepository.findAllByProjectId(projectID);
        taskRepository.removeAll(taskList);
    }

    @Override
    public List<Project> getListByKeyword(String userId, String keyword) {
        return ((ProjectRepository)entityRepository).findAllByUserIdAndKeyword(userId, keyword);
    }

    @Override
    public void saveSerialize() throws IOException {
        File f = new File(SERIALIZE_FILE_NAME);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(f));
        List<Project> list = entityRepository.findAll();
        for (Project p: list) {
            objectOutputStream.writeObject(p);
        }
        objectOutputStream.close();
    }

    @Override
    public void getSerialize() throws IOException, ClassNotFoundException {
        File f = new File(SERIALIZE_FILE_NAME);
        Object o;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(f));){
            while((o = objectInputStream.readObject())!=null) {
                System.out.println(o.getClass().getSimpleName());
                if(o instanceof Project) {
                    entityRepository.merge((Project)o);
                }
            }
        }
        catch (EOFException e){
            e.printStackTrace();
        }
    }

    @Override
    public void saveJaxXML() throws JAXBException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(ProjectHolder.class, Project.class);
        final Marshaller marshaller = jaxbContext.createMarshaller();
        final List<Project> list = entityRepository.findAll();
        final ProjectHolder projectHolder = new ProjectHolder();
        projectHolder.projectList = list;
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(projectHolder, new File(JAX_XLM_FILE_NAME));

    }

    @Override
    public void getJaxXML() throws JAXBException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(ProjectHolder.class, Project.class);
        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        final ProjectHolder projectHolder = (ProjectHolder)unmarshaller.unmarshal(new File(JAX_XLM_FILE_NAME));
        for(Project p: projectHolder.projectList){
            entityRepository.merge(p);
        }
    }

    @XmlRootElement(name = "Projects")
    @XmlAccessorType(XmlAccessType.FIELD)
    static class ProjectHolder {
        @XmlElement(name = "Project")
        List<Project> projectList = new ArrayList<>();
    }
}
