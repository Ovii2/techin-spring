package hellopaths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloPathsController {

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "", defaultValue = "") String str) {
        return "Hello" + str;
    }

    @GetMapping("/paths")
    public String paths(@RequestParam(value = "", defaultValue = "") String str) {
        return "Paths" + str;
    }

}
