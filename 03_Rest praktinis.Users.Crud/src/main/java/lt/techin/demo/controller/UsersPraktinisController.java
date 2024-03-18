package lt.techin.demo.controller;

import lt.techin.demo.dto.UserCreationDTO;
import lt.techin.demo.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/users")

public class UsersPraktinisController {

    private List<User> database = new ArrayList<>();
    private final AtomicLong id = new AtomicLong();


    @GetMapping("/")
    public List<User> getAllUsers() {
        for (User user : database) {
            System.out.println(user);
        }
        return database;
    }

    @PostMapping("/")
    public User createUser(@RequestBody UserCreationDTO userCreationDTO) {
        System.out.println(userCreationDTO);

        User newUser = new User(id.incrementAndGet(), userCreationDTO.getUsername(), userCreationDTO.getFirstName(), userCreationDTO.getLastName(), userCreationDTO.getEmail());
        database.add(newUser);
        return newUser;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return database.stream().
                filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        System.out.println("Deleting user with id: " + id);
        database.removeIf(user -> user.getId().equals(id));

    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = database.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);


        if (updatedUser.getUsername() != null) {
            updatedUser.setUsername(user.getUsername());
        }
        if (updatedUser.getFirstName() != null) {
            updatedUser.setFirstName(user.getFirstName());
        }
        if (updatedUser.getLastName() != null) {
            updatedUser.setLastName(user.getLastName());
        }
        if (updatedUser.getEmail() != null) {
            updatedUser.setEmail(user.getEmail());
        }
        return updatedUser;
    }

}
