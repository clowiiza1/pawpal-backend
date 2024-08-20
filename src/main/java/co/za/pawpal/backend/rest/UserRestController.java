package co.za.pawpal.backend.rest;

import co.za.pawpal.backend.entity.User;
import co.za.pawpal.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {
    private UserService userService;

    @Autowired
    public UserRestController(UserService theUserService) {
        userService = theUserService;
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable int userId) {
        User theUser = userService.findById(userId);

        if (theUser == null) {
            throw new RuntimeException("User id not found - " + userId);
        }
        return theUser;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User theUser) {
        //also just in case they pass an id in JSON, set id to 0
        //this is to force a save a new item .. instead of an update
        //specifying it is a save instead of an update
        theUser.setId(0);
        User dbUser = userService.save(theUser);
        return dbUser;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User theUser) {

        User dbUser = userService.save(theUser);
        return dbUser;
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable int userId) {
        User theUser = userService.findById(userId);
        if (theUser == null) {
            throw new RuntimeException("User id not found - " + userId);
        }

        userService.deleteByID(userId);
        return "User deleted with id: " + userId;
    }



}