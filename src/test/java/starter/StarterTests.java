package starter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

@SpringJUnitConfig(ApplicationConfig.class)
@Transactional
@SqlConfig(dataSource = "applicationDataSource")
class StarterTests {
	@Autowired
	@Qualifier("applicationNamedParameterJdbcTemplate")
	NamedParameterJdbcTemplate pdrNamedParameterJdbcTemplate;

	@Autowired
	PersonRepository repository;

	@Autowired
	AddressRepository addressRepository;

	@Test
	@Sql("/sql/person.sql")
	@DisplayName("Test Spring Data Insert - auto increment id")
	void testSpringDataJdbcAutoIncrement() {
		Person p = new Person("John1", "Doe2", 2003);
		p = repository.save(p);
		assertNotNull(p);
		for (Person p1 : repository.findAll()) {
			System.out.println(p1.id + " " + p1.firstName + " " + p1.lastName);
		}

		Person person = repository.findById(p.id).orElseThrow(() -> new RuntimeException("no person"));

		assertEquals("John1", person.firstName);

	}

	@Test
	@Sql("/sql/address.sql")
	@DisplayName("Test Spring Data Insert - assigned id with persistable")
	void testSpringDataJdbcAssignedId() {
		Address a = new Address(2l, "productivity road", "alkapuri", "39020");
		a.crudOperation=Operation.CREATE;
		a = addressRepository.save(a);
		assertNotNull(a);

		Address addr = addressRepository.findById(a.id).orElseThrow(() -> new RuntimeException("no address"));

		assertEquals("productivity road", addr.line1);

	}
}
