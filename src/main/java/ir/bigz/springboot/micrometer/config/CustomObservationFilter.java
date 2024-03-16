package ir.bigz.springboot.micrometer.config;

import io.micrometer.common.KeyValue;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationFilter;

public class CustomObservationFilter implements ObservationFilter {

    @Override
    public Observation.Context map(Observation.Context context) {
        return context
                .put("my-tag", "this is a custom tag for all span")
                .addLowCardinalityKeyValue(KeyValue.of("my-tag", "this is customTag"));
    }
}
