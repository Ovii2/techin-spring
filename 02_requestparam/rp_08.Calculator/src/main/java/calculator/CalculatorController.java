package calculator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    @GetMapping("/add")
    public int calcSum(int first, int second) {
        return first + second;
    }

    @GetMapping("/multiply")
    public int multSum(int first, int second) {
        return first * second;
    }

}
