package com.alex.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @RequestMapping(value="/user/{id}", method= RequestMethod.GET)
    @ResponseBody
    public void getUser(@PathVariable Integer id){

    }
}
