package hellopath;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloPathController {

    @GetMapping("/path")
    public String response(@RequestParam(value = "", defaultValue = "") String getResponse) {
        return "Correct!" + getResponse;
    }

}
