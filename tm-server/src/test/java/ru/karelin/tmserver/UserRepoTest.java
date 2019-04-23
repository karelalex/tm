package ru.karelin.tmserver;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.karelin.tmserver.api.repository.UserRepository;
import ru.karelin.tmserver.config.MainConfig;
import ru.karelin.tmserver.entity.User;
import ru.karelin.tmserver.enumeration.RoleType;



@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {MainConfig.class, TestConfig.class})
public class UserRepoTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void createUser() {
        User user = new User();
        user.setLogin("jora");
        user.setPasswordHash("daskjgfbjakdvgfkuadfs");
        user.setUserName("jopa2");
        user.setRole(RoleType.ADMIN);
        userRepository.save(user);
        User user2 = userRepository.findOneByLogin("jora");
        Assert.assertEquals(user.getUserName(), user2.getUserName());
        user2.setUserName("gaga");
        userRepository.save(user2);
        User user3 = userRepository.findOneByLoginAndPassword("jora", "daskjgfbjakdvgfkuadfs");
        Assert.assertEquals("gaga", user3.getUserName());
        userRepository.delete(user3);
        Assert.assertNull(userRepository.findOneByLoginAndPassword("jora", "daskjgfbjakdvgfkuadfs"));
    }


}
