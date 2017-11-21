package com.alex.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User saveOrFindUser(User user) {
        User foundOrSavedUser;

        List<User> userByPhoneNumber = userRepository.findByPhoneNumber(user.getPhoneNumber());

        if(userByPhoneNumber.size() == 0){
            foundOrSavedUser = userRepository.save(user);
        } else {
            foundOrSavedUser = userByPhoneNumber.get(0);
        }

        return foundOrSavedUser;
    }

}
