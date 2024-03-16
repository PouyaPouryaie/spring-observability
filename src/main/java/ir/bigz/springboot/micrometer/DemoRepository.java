package ir.bigz.springboot.micrometer;

import io.micrometer.observation.annotation.Observed;
import org.springframework.data.jpa.repository.JpaRepository;

@Observed(name = "demoRepo")
public interface DemoRepository extends JpaRepository<DemoEntity, Long> {
}
