package ir.bigz.springboot.micrometer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleDataInitializer implements CommandLineRunner {

    private final DemoRepository demoRepository;

    public SampleDataInitializer(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        demoRepository.save(new DemoEntity("demo 1", false));
        demoRepository.save(new DemoEntity("demo 2", false));
        demoRepository.save(new DemoEntity("demo 3", true));
        System.out.println("sample data inserted to database");
    }
}
