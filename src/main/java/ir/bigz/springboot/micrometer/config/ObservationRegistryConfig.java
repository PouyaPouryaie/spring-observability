package ir.bigz.springboot.micrometer.config;

import io.micrometer.observation.ObservationRegistry;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationRegistryCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObservationRegistryConfig implements ObservationRegistryCustomizer<ObservationRegistry> {

    @Override
    public void customize(ObservationRegistry registry) {
        registry.observationConfig()
                .observationPredicate(new CustomObservationPredicate())
                .observationFilter(new CustomObservationFilter());
    }
}
