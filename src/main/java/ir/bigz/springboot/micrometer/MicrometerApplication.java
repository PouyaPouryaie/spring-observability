package ir.bigz.springboot.micrometer;

import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.config.MeterFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

//actuator metrics: http://localhost:8080/actuator/metrics

@SpringBootApplication
public class MicrometerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrometerApplication.class, args);
	}


	/*
	This filter adds a common tag with the application name to all metrics, which is helpful when you’re monitoring multiple
	applications and want to distinguish between them.
	 */
//	@Bean
//	MeterFilter commonTagsMeterFilter() {
//		return MeterFilter.commonTags(Arrays.asList(Tag.of("my-tag", "my-Demo-Spring-boot-app")));
//	}

}
