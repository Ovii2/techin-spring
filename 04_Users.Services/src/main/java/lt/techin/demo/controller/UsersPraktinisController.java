package lt.techin.demo.controller;

import lt.techin.demo.dto.UserCreationDTO;
import lt.techin.demo.model.User;
import lt.techin.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/users")

public class UsersPraktinisController {

    private UserService userService;

    @Autowired
    public UsersPraktinisController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/")
    public User createUser(@RequestBody UserCreationDTO userCreationDTO) {
        System.out.println(userCreationDTO);
        return userService.createUser(userCreationDTO);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        System.out.println("Deleting user with id: " + id);
        userService.deleteUser(id);

    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        System.out.printf("Updating user: %s with id: %d %n", user, id);
        return userService.updateUser(id, user);
    }

}
