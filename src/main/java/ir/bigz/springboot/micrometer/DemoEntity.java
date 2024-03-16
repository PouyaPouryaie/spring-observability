package ir.bigz.springboot.micrometer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.annotation.AccessType;

@Entity
@AccessType(AccessType.Type.FIELD)
public class DemoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long id;

    public String name;
    public boolean exist;

    public DemoEntity() {
    }

    public DemoEntity(String name, boolean exist) {
        this.name = name;
        this.exist = exist;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }
}
