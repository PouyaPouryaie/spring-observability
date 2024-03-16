# Spring boot3 with Micrometer


### Trace and span id in log messages
By default, log messages will not include the trace and span id. It needs to be manually added to the logger pattern.

```properties
# Take the span id and trace id from the Mapped Diagnostic Context (MDC) and 
# put them into the log message.
logging.pattern.level=%5p [${spring.application.name:},%X{traceId:-},%X{spanId:-}]
```

### Observation filters
In the case that there is some data we want present in all observation contexts we can use an implementation  <br> 
of the ObservationFilter interface <br>
sample: CustomObservationFilter

### Observation predicates
Sometimes it might be useful to not create an observation under specific conditions. Maybe the observed method has <br>
a frequently occurring case or there is a situation we never want to observe. <br>
sample: CustomObservationPredicate

### Observation handler
Observation handlers allow us to hook into the lifecycles of observations. By implementing the ObservationHandler <br>
interface on a class and overriding the interface’s default implementations, it is possible to perform custom actions <br>
on specific lifecycle events. <br>
sample: SimpleLoggingHandler

***
## Resource
[Switching from Spring Cloud Sleuth to Micrometer Tracing / Micrometer Observation for Spring Boot 3](https://openvalue.blog/posts/2022/12/16/tracing-in-spring-boot-2-and-3/)