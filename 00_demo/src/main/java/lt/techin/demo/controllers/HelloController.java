package lt.techin.demo.controllers;


import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    //    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    @GetMapping("/hello")
    public User hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new User(name);
    }

    @PostMapping("/hello")
    public User postUser(@RequestBody User user) {
        System.out.println("Name: " + user.getName());

        user.setName(user.getName().toUpperCase());
        return user;
    }

}
