package ir.bigz.springboot.micrometer.service;

import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Service;


@Service
public class ObservedService {

    @Observed(name = "demoService",
            contextualName = "call simple endpoint with observed annotation",
            lowCardinalityKeyValues =  {"class.name", "ObservedService"}
    )
    public String greet() {
        return "Hello World";
    }
}
