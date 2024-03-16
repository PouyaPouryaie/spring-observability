package ir.bigz.springboot.micrometer;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import ir.bigz.springboot.micrometer.service.ObservationInterfaceService;
import ir.bigz.springboot.micrometer.service.ObservedService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class DemoController {

    private final Random random = new Random();

    private final ObservedService observedService;
    private final ObservationInterfaceService observationInterfaceService;
    private final Counter counter;
    private final AtomicInteger myGauge = new AtomicInteger(0);

    public DemoController(ObservedService observedService, ObservationInterfaceService observationInterfaceService, MeterRegistry registry) {
        this.observedService = observedService;
        this.observationInterfaceService = observationInterfaceService;
        counter = Counter.builder("DemoController.Counter")
                .description("count call this class")
                .tag("stage", "dev")
                .register(registry);

        Gauge.builder("my.gauge", myGauge, AtomicInteger::get)
                .description("gauge test")
                .tag("stage", "dev")
                .register(registry);
    }

    @GetMapping("/hello")
    @ResponseBody
    public String getGreeting() {
        return observedService.greet();
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<?> getAllData() {
        counter.increment();
        myGauge.set(random.nextInt(1000));
        List<DemoEntity> allData = observationInterfaceService.getAllData();
        return ResponseEntity.ok(allData);
    }
}
