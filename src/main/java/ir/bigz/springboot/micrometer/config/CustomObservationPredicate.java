package ir.bigz.springboot.micrometer.config;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationPredicate;

import java.util.Objects;

public class CustomObservationPredicate implements ObservationPredicate {

    @Override
    public boolean test(String ignoredObservationName, Observation.Context context) {
        if(context.getLowCardinalityKeyValues().stream().findAny().isEmpty() || Objects.isNull(context.getLowCardinalityKeyValue("methodName").getKey())) {
            return true;
        } else {
            return !ignoredObservationName.equalsIgnoreCase(Objects.requireNonNull(context.getLowCardinalityKeyValue("methodName")).getValue());
        }
    }
}
