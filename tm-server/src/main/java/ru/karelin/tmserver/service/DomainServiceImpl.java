package ru.karelin.tmserver.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.jetbrains.annotations.NotNull;
import ru.karelin.tmserver.api.repository.ProjectRepository;
import ru.karelin.tmserver.api.repository.TaskRepository;
import ru.karelin.tmserver.api.repository.UserRepository;
import ru.karelin.tmserver.api.service.DomainService;
import ru.karelin.tmserver.entity.AbstractEntity;
import ru.karelin.tmserver.entity.Project;
import ru.karelin.tmserver.entity.Task;
import ru.karelin.tmserver.entity.User;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DomainServiceImpl implements DomainService {
    private final static String SERIALIZE_FILE_NAME = "domain.ser";
    private final static String JAX_XLM_FILE_NAME = "domainJAX.xml";
    private final static String JAX_JSON_FILE_NAME = "domainJAX.json";
    private final static String FASTER_XML_FILE_NAME = "domainFASTER.xml";
    private final static String FASTER_JSON_FILE_NAME = "domainFASTER.json";

    @NotNull
    private final UserRepository userRepository;
    @NotNull
    private final TaskRepository taskRepository;
    @NotNull
    private final ProjectRepository projectRepository;


    public DomainServiceImpl(@NotNull UserRepository userRepository, @NotNull TaskRepository taskRepository, @NotNull ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public void saveSerialize() throws IOException {
        File f = new File(SERIALIZE_FILE_NAME);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(f));
        List<AbstractEntity> list = new ArrayList<AbstractEntity>(userRepository.findAll());
        list.addAll(taskRepository.findAll());
        list.addAll(projectRepository.findAll());
        for (AbstractEntity t : list) {
            objectOutputStream.writeObject(t);
        }
        objectOutputStream.close();
    }

    @Override
    public void getSerialize() throws IOException, ClassNotFoundException {
        File f = new File(SERIALIZE_FILE_NAME);
        Object o;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(f));) {
            while ((o = objectInputStream.readObject()) != null) {
                if (o instanceof Task) {
                    taskRepository.merge((Task) o);
                }
                if (o instanceof User) {
                    userRepository.merge((User) o);
                }
                if (o instanceof Project) {
                    projectRepository.merge((Project) o);
                }
            }
        } catch (EOFException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveJaxXML() throws JAXBException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(Holder.class, User.class, Project.class, Task.class);
        final Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(createHolder(), new File(JAX_XLM_FILE_NAME));
    }

    @Override
    public void getJaxXML() throws JAXBException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(Holder.class, User.class, Project.class, Task.class);
        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        extractHolder((Holder) unmarshaller.unmarshal(new File(JAX_XLM_FILE_NAME)));

    }

    @Override
    public void saveJaxJSON() throws JAXBException {
        Map<String, Object> properties = new HashMap<>();
        properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
        properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
        final JAXBContext jaxbContext = (JAXBContext) JAXBContextFactory.createContext(new Class[]{Holder.class, User.class, Task.class, Project.class}, properties);
        final Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, MediaType.APPLICATION_JSON);
        marshaller.marshal(createHolder(), new File(JAX_JSON_FILE_NAME));
    }

    @Override
    public void getJaxJSON() throws JAXBException {
        Map<String, Object> properties = new HashMap<>();
        properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
        properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
        final JAXBContext jaxbContext = (JAXBContext) JAXBContextFactory.createContext(new Class[]{Holder.class, User.class, Task.class, Project.class}, properties);
        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        extractHolder(unmarshaller.unmarshal(new StreamSource(JAX_JSON_FILE_NAME), Holder.class).getValue());
    }

    @Override
    public void saveFasterXML() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        xmlMapper.writeValue(new File(FASTER_XML_FILE_NAME), createHolder());

    }

    @Override
    public void getFasterXML() throws IOException {
        XmlMapper mapper = new XmlMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        extractHolder(mapper.readValue(new File(FASTER_XML_FILE_NAME), Holder.class));
    }

    @Override
    public void saveFasterJSON() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File(FASTER_JSON_FILE_NAME), createHolder());
    }

    @Override
    public void getFasterJSON() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        extractHolder(mapper.readValue(new File(FASTER_JSON_FILE_NAME), Holder.class));
    }

    private Holder createHolder() {
        final Holder holder = new Holder();
        holder.projectList = projectRepository.findAll();
        holder.taskList = taskRepository.findAll();
        holder.userList = userRepository.findAll();
        return holder;
    }

    private void extractHolder(Holder holder) {
        for (Task t : holder.taskList) {
            taskRepository.merge(t);
        }
        for (Project p : holder.projectList) {
            projectRepository.merge(p);
        }
        for (User u : holder.userList) {
            userRepository.merge(u);
        }
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
