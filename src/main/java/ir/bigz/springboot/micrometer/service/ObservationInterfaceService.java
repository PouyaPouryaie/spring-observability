package ir.bigz.springboot.micrometer.service;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import ir.bigz.springboot.micrometer.DemoEntity;
import ir.bigz.springboot.micrometer.DemoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObservationInterfaceService {

    private final DemoRepository demoRepository;
    private final ObservationRegistry registry;
    private final Observation observation;

    public ObservationInterfaceService(DemoRepository demoRepository, ObservationRegistry registry) {
        this.demoRepository = demoRepository;
        this.registry = registry;

        observation = Observation.createNotStarted("custom-span", registry)
                .contextualName("ObservationInterfaceService")
                .lowCardinalityKeyValue("class", this.getClass().getName());
    }

    public List<DemoEntity> getAllData() {
        List<DemoEntity> listOfData;
        listOfData = getAllDataWithSimpleApproach();
        listOfData = getAllDataWithManuallyOpenScope();
        return listOfData;
    }

    /***
     * observe(): Start a span before the functional interface begins and close the span after the functional interface is finished.
     */
    public List<DemoEntity> getAllDataWithSimpleApproach() {

        return observation
                .lowCardinalityKeyValue("methodName", "getAllDataWithSimpleApproach")
                .observe(() -> demoRepository.findAll());
    }

    /***
     * 1. Low cardinality: describes key values that are bounded - The value of the key changes infrequently or rarely.
     *  Low cardinality keys will be tagged to the span and metrics created by the observation.
     * 2. High cardinality: describes key values that are unbounded - The value of the key changes frequently or regularly.
     *  High cardinality keys will only be tagged to spans created by the observation.
     * 3. Fundamentally, using scopes is mostly the same as when using observe(). You have access to the same methods
     *  and can chain them together if desired. The major differences are that you get control of when
     *  the observation ends, and you can create events.
     */
    public List<DemoEntity> getAllDataWithManuallyOpenScope() {

        observation.start();
        try (var igonred = observation.openScope()){
            observation.event(Observation.Event.of("start fetch-data"));
            List result = demoRepository.findAll();
            observation.lowCardinalityKeyValue("methodName", "getAllDataWithManuallyOpenScope");
            observation.highCardinalityKeyValue("fetch-size", String.valueOf(result.size()));
            return result;
        }finally {
            observation.event(Observation.Event.of("end fetch-data"));
            observation.stop();
        }
    }


}
