package starter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("ADDRESS")
public class Address implements Persistable<Long>{
 @Id 
	@Column("ADDRESS_ID")
	Long id;
	@Column("LINE1")
	String line1;
	@Column("LINE2")
	String line2;
    @Column("ZIPCODE")
    String zipcode; 
    
    @Transient
    Operation crudOperation=Operation.READ;

	public Address(long id,String line1,String line2, String zipcode) {
		this.id=id;
		this.line1=line1;
        this.line2=line2;
        this.zipcode=zipcode;
	}

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return crudOperation==Operation.CREATE;
    }


}