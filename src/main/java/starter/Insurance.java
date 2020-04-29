package starter;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

public class Insurance extends AbstractPersistable<String> {

    @Id
    @Column("INSURANCE_ID")
    String id;

    @Column("INSURANCE_NAME")
    String name;

    @Override
    public String getId() {
        return id;
    }

    public Insurance(String id, String name) {
        this.id = id;
        this.name = name;
    }

}