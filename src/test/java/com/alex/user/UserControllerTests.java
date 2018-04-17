package com.alex.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserControllerTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    UserController userController;

    @Test
    public void whenTryingToFindAUserAndItIsFound() throws Exception{
        User someFoundUser = new User();

        someFoundUser.setFirstName("BANANAVILLE");

        when(userRepository.findOne(any(Integer.class))).thenReturn(someFoundUser);

        User foundUser = userController.getUser(99999);

        assertEquals(someFoundUser, foundUser);
        assertEquals(someFoundUser.getFirstName(), foundUser.getFirstName());
    }

    @Test
    public void whenTryingToFindAUserAndItIsNotFound() throws Exception{

        when(userRepository.findOne(any(Integer.class))).thenReturn(null);

        User foundUser = userController.getUser(99999);

        assertEquals(null, foundUser);
    }

    @Test
    public void whenSuccessfullyCreatingAUser() throws Exception{
        User someUserFromTheService = new User();

        someUserFromTheService.setFirstName("SOME FIRST NAME");

        when(userService.saveOrFindUser(any(User.class))).thenReturn(someUserFromTheService);

        User resultingUser = userController.createOrFindUser(new User());

        assertEquals(someUserFromTheService, resultingUser);

    }
}