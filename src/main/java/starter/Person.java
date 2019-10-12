package starter;

import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.*;
@Table("PERSON")
public class Person {

    @Id 
	@Column("PERSON_ID")
	private Long id;
	@Column("FST_NM")
	String firstName;
	@Column("LST_NM")
	String lastName;
    @Column("EMP_ID")
	Integer employeeId; 
	public Person(Long id,String firstName,String lastName,Integer employeeId) {
		this.id = id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.employeeId=employeeId;
	}

	
}
