package com.ks.projectxservices.Controllers;

import com.ks.projectxservices.Models.User;
import com.ks.projectxservices.Models.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/user")
public class UserRepositoryController {

    @Autowired
    private UserRepository userRepository;
    @PostMapping(path="/add")
    public @ResponseBody String addUser(@RequestBody String jsonBody){
        try {
            JSONObject jsonBodyObject = new JSONObject(jsonBody);
            User user = new User();
            user.setUsername(jsonBodyObject.get("username").toString());
            user.setPassword(jsonBodyObject.get("password").toString());
            user.setUser_role(jsonBodyObject.get("user_role").toString());
            userRepository.save(user);
            return "Saved";
        }
        catch (Exception exception) {
            return exception.getMessage();
        }

    }

    @GetMapping(path="/allUsers")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path="/getUserById")
    public @ResponseBody
    List<User> getUserById(@RequestBody String userId) {
        return userRepository.findByUserid(Integer.parseInt(userId));
    }

    @GetMapping("/getUserByUsername")
    public @ResponseBody
    List<User> getUserByUsername(@RequestBody String username) {
        return userRepository.findByUsername(username);
    }

}
