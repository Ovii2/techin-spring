package hellorequestparams;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRequestParamsController {

    @GetMapping("/hello")
    public String greeting(@RequestParam(value = "param", defaultValue = "...") String name) {
        return "Hello " + name;
    }

    @GetMapping("/params")
    public Map<String, String> greetings(@RequestParam Map<String, String> params) {
        return params;
    }

}
