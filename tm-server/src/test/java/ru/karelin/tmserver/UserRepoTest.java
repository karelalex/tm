package ru.karelin.tmserver;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.karelin.tmserver.api.repository.UserRepository;
import ru.karelin.tmserver.entity.User;
import ru.karelin.tmserver.enumeration.RoleType;

import javax.inject.Inject;

@RunWith(CdiTestRunner.class)
public class UserRepoTest {

    @Inject
    UserRepository userRepository;

    @Test
    public void createUser() {
        User user = new User();
        user.setLogin("jora");
        user.setPasswordHash("daskjgfbjakdvgfkuadfs");
        user.setUserName("jopa2");
        user.setRole(RoleType.ADMIN);
        userRepository.persist(user);
        User user2 = userRepository.findOneByLogin("jora");
        Assert.assertEquals(user.getUserName(), user2.getUserName());
        user2.setUserName("gaga");
        userRepository.merge(user2);
        User user3 = userRepository.findOneByLoginAndPassword("jora", "daskjgfbjakdvgfkuadfs");
        Assert.assertEquals("gaga", user3.getUserName());

    }


}
