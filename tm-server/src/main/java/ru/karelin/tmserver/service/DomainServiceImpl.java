package ru.karelin.tmserver.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.jetbrains.annotations.NotNull;
import ru.karelin.tmserver.api.repository.ProjectRepository;
import ru.karelin.tmserver.api.repository.TaskRepository;
import ru.karelin.tmserver.api.repository.UserRepository;
import ru.karelin.tmserver.api.service.DomainService;
import ru.karelin.tmserver.entity.Project;
import ru.karelin.tmserver.entity.Task;
import ru.karelin.tmserver.entity.User;
import ru.karelin.tmserver.exception.PermissionException;

import javax.persistence.EntityManagerFactory;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomainServiceImpl implements DomainService {
    private final static String SERIALIZE_FILE_NAME = "domain.ser";
    private final static String JAX_XLM_FILE_NAME = "domainJAX.xml";
    private final static String JAX_JSON_FILE_NAME = "domainJAX.json";
    private final static String FASTER_XML_FILE_NAME = "domainFASTER.xml";
    private final static String FASTER_JSON_FILE_NAME = "domainFASTER.json";

    @NotNull
    private final EntityManagerFactory factory;


    public DomainServiceImpl(@NotNull EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public void saveSerialize(String userId) throws IOException, PermissionException {
       /* File f = new File(SERIALIZE_FILE_NAME);
        try (SqlSession session = factory.openSession(); ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(f));) {
            UserRepository userRepository = session.getMapper(UserRepositoryBatis.class);
            ProjectRepository projectRepository = session.getMapper(ProjectRepositoryBatis.class);
            TaskRepository taskRepository = session.getMapper(TaskRepositoryBatis.class);
            if (userRepository.findOne(userId).getRole() != RoleType.ADMIN)
                throw new PermissionException("You must be an admin to perform this action");
            List<AbstractEntity> list = new ArrayList<AbstractEntity>(userRepository.findAll());
            list.addAll(taskRepository.findAll());
            list.addAll(projectRepository.findAll());
            for (AbstractEntity t : list) {
                objectOutputStream.writeObject(t);
            }
        }*/

    }

    @Override
    public void getSerialize(String userId) throws IOException, ClassNotFoundException, PermissionException {
       /* File f = new File(SERIALIZE_FILE_NAME);
        Object o;
        SqlSession session = factory.openSession();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(f));) {
            UserRepository userRepository = session.getMapper(UserRepositoryBatis.class);
            ProjectRepository projectRepository = session.getMapper(ProjectRepositoryBatis.class);
            TaskRepository taskRepository = session.getMapper(TaskRepositoryBatis.class);
            if (userRepository.findOne(userId).getRole() != RoleType.ADMIN)
                throw new PermissionException("You must be an admin to perform this action");
            while ((o = objectInputStream.readObject()) != null) {
                if (o instanceof Task) {
                    taskRepository.persist((Task) o);
                }
                if (o instanceof User) {
                    userRepository.persist((User) o);
                }
                if (o instanceof Project) {
                    projectRepository.persist((Project) o);
                }

            }
            session.commit();
        } catch (EOFException e) {
            session.commit();
            e.printStackTrace();
        } catch (PersistenceException e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }*/

    }

    @Override
    public void saveJaxXML(String userId) throws JAXBException, PermissionException {
        /*try (SqlSession session = factory.openSession()) {
            UserRepository userRepository = session.getMapper(UserRepositoryBatis.class);
            ProjectRepository projectRepository = session.getMapper(ProjectRepositoryBatis.class);
            TaskRepository taskRepository = session.getMapper(TaskRepositoryBatis.class);

            if (userRepository.findOne(userId).getRole() != RoleType.ADMIN)
                throw new PermissionException("You must be an admin to perform this action");
            final JAXBContext jaxbContext = JAXBContext.newInstance(Holder.class, User.class, Project.class, Task.class);
            final Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(createHolder(taskRepository, projectRepository, userRepository), new File(JAX_XLM_FILE_NAME));
        }*/
    }

    @Override
    public void getJaxXML(String userId) throws JAXBException, PermissionException {
       /* SqlSession session = factory.openSession();
        try  {
            UserRepository userRepository = session.getMapper(UserRepositoryBatis.class);
            ProjectRepository projectRepository = session.getMapper(ProjectRepositoryBatis.class);
            TaskRepository taskRepository = session.getMapper(TaskRepositoryBatis.class);
            if (userRepository.findOne(userId).getRole() != RoleType.ADMIN)
                throw new PermissionException("You must be an admin to perform this action");
            final JAXBContext jaxbContext = JAXBContext.newInstance(Holder.class, User.class, Project.class, Task.class);
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            extractHolder((Holder) unmarshaller.unmarshal(new File(JAX_XLM_FILE_NAME)), taskRepository, projectRepository, userRepository);
            session.commit();
        } catch (PersistenceException e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }*/
    }

    @Override
    public void saveJaxJSON(String userId) throws JAXBException, PermissionException {
        /*try (SqlSession session = factory.openSession();) {
            UserRepository userRepository = session.getMapper(UserRepositoryBatis.class);
            ProjectRepository projectRepository = session.getMapper(ProjectRepositoryBatis.class);
            TaskRepository taskRepository = session.getMapper(TaskRepositoryBatis.class);
            if (userRepository.findOne(userId).getRole() != RoleType.ADMIN)
                throw new PermissionException("You must be an admin to perform this action");
            Map<String, Object> properties = new HashMap<>();
            properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
            properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
            final JAXBContext jaxbContext = (JAXBContext) JAXBContextFactory.createContext(new Class[]{Holder.class, User.class, Task.class, Project.class}, properties);
            final Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, MediaType.APPLICATION_JSON);
            marshaller.marshal(createHolder(taskRepository, projectRepository, userRepository), new File(JAX_JSON_FILE_NAME));
        }*/
    }

    @Override
    public void getJaxJSON(String userId) throws JAXBException, PermissionException {
       /* SqlSession session = factory.openSession();
        try  {
            UserRepository userRepository = session.getMapper(UserRepositoryBatis.class);
            ProjectRepository projectRepository = session.getMapper(ProjectRepositoryBatis.class);
            TaskRepository taskRepository = session.getMapper(TaskRepositoryBatis.class);
            if (userRepository.findOne(userId).getRole() != RoleType.ADMIN)
                throw new PermissionException("You must be an admin to perform this action");
            Map<String, Object> properties = new HashMap<>();
            properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
            properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
            final JAXBContext jaxbContext = (JAXBContext) JAXBContextFactory.createContext(new Class[]{Holder.class, User.class, Task.class, Project.class}, properties);
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            extractHolder(unmarshaller.unmarshal(new StreamSource(JAX_JSON_FILE_NAME), Holder.class).getValue(), taskRepository, projectRepository, userRepository);
        } catch (PersistenceException e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }*/
    }

    @Override
    public void saveFasterXML(String userId) throws IOException, PermissionException {
        /*try (SqlSession session = factory.openSession()) {
            UserRepository userRepository = session.getMapper(UserRepositoryBatis.class);
            ProjectRepository projectRepository = session.getMapper(ProjectRepositoryBatis.class);
            TaskRepository taskRepository = session.getMapper(TaskRepositoryBatis.class);
            if (userRepository.findOne(userId).getRole() != RoleType.ADMIN)
                throw new PermissionException("You must be an admin to perform this action");
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            xmlMapper.writeValue(new File(FASTER_XML_FILE_NAME), createHolder(taskRepository, projectRepository, userRepository));
        }*/

    }

    @Override
    public void getFasterXML(String userId) throws IOException, PermissionException {
        /*SqlSession session = factory.openSession();
        File f = new File(FASTER_XML_FILE_NAME);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(f));) {
            UserRepository userRepository = session.getMapper(UserRepositoryBatis.class);
            ProjectRepository projectRepository = session.getMapper(ProjectRepositoryBatis.class);
            TaskRepository taskRepository = session.getMapper(TaskRepositoryBatis.class);
            if (userRepository.findOne(userId).getRole() != RoleType.ADMIN)
                throw new PermissionException("You must be an admin to perform this action");
            XmlMapper mapper = new XmlMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            extractHolder(mapper.readValue(new File(FASTER_XML_FILE_NAME), Holder.class), taskRepository, projectRepository, userRepository);
        } catch (PersistenceException e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }*/
    }

    @Override
    public void saveFasterJSON(String userId) throws IOException, PermissionException {
       /* try (SqlSession session = factory.openSession()) {
            UserRepository userRepository = session.getMapper(UserRepositoryBatis.class);
            ProjectRepository projectRepository = session.getMapper(ProjectRepositoryBatis.class);
            TaskRepository taskRepository = session.getMapper(TaskRepositoryBatis.class);
            if (userRepository.findOne(userId).getRole() != RoleType.ADMIN)
                throw new PermissionException("You must be an admin to perform this action");
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(FASTER_JSON_FILE_NAME), createHolder(taskRepository, projectRepository, userRepository));
        }*/
    }

    @Override
    public void getFasterJSON(String userId) throws IOException, PermissionException {
       /* SqlSession session = factory.openSession();
        try  {
            UserRepository userRepository = session.getMapper(UserRepositoryBatis.class);
            ProjectRepository projectRepository = session.getMapper(ProjectRepositoryBatis.class);
            TaskRepository taskRepository = session.getMapper(TaskRepositoryBatis.class);
            if (userRepository.findOne(userId).getRole() != RoleType.ADMIN)
                throw new PermissionException("You must be an admin to perform this action");
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            extractHolder(mapper.readValue(new File(FASTER_JSON_FILE_NAME), Holder.class), taskRepository, projectRepository, userRepository);
        } catch (PersistenceException e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }*/
    }

    private Holder createHolder(TaskRepository taskRepository, ProjectRepository projectRepository, UserRepository userRepository) {
        final Holder holder = new Holder();
        /*holder.projectList = projectRepository.findAll();
        holder.taskList = taskRepository.findAll();
        holder.userList = userRepository.findAll();*/
        return holder;
    }

    private void extractHolder(Holder holder, TaskRepository taskRepository, ProjectRepository projectRepository, UserRepository userRepository) {
        /*for (Task t : holder.taskList) {
            taskRepository.persist(t);
        }
        for (Project p : holder.projectList) {
            projectRepository.persist(p);
        }
        for (User u : holder.userList) {
            userRepository.persist(u);
        }*/
    }

    @XmlRootElement(name = "Domain")
    @XmlAccessorType(XmlAccessType.FIELD)
    @JacksonXmlRootElement(localName = "Domain")
    @JsonRootName("Domain")
    static class Holder {

        @XmlElement(name = "Task")
        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "Task")
        @JsonProperty("Task")
        List<Task> taskList = new ArrayList<>();

        @XmlElement(name = "Project")
        @JacksonXmlProperty(localName = "Project")
        @JacksonXmlElementWrapper(useWrapping = false)
        @JsonProperty("Project")
        List<Project> projectList = new ArrayList<>();

        @XmlElement(name = "User")
        @JacksonXmlProperty(localName = "User")
        @JacksonXmlElementWrapper(useWrapping = false)
        @JsonProperty("User")
        List<User> userList = new ArrayList<>();
    }

}
