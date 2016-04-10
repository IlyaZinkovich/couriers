package by.couriers.operator.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@CrossOrigin
public class OperatorController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
