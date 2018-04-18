package com.alex.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

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
    public void whenFakeTryingToFindAUserAndItIsFound() throws Exception {
        User someFoundUser = new User();

        someFoundUser.setFirstName("BANANAVILLE");

        when(userRepository.findById(99999)).thenReturn(Optional.of(someFoundUser));

        User foundUser = userController.getUser(99999).get();

        assertEquals(someFoundUser, foundUser);
        assertEquals(someFoundUser.getFirstName(), foundUser.getFirstName());
    }

    @Test
    public void whenTryingToFindAUserAndItIsNotFound() throws Exception {

        when(userRepository.findById(any(Integer.class))).thenReturn(null);

        Optional<User> foundUser = userController.getUser(99999);

        assertEquals(null, foundUser);
    }

    @Test
    public void whenSuccessfullyCreatingAUser() throws Exception {
        User someUserFromTheService = new User();

        someUserFromTheService.setFirstName("SOME FIRST NAME");

        when(userService.saveOrFindUser(any(User.class))).thenReturn(someUserFromTheService);

        User resultingUser = userController.createOrFindUser(new User());

        assertEquals(someUserFromTheService, resultingUser);

    }
}
