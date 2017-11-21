package com.alex.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/user/{id}", method= RequestMethod.GET)
    @ResponseBody
    public User getUser(@PathVariable Integer id){
        return userRepository.findOne(id);
    }

    }
}
