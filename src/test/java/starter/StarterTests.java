package starter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTableWhere;
import static starter.AutomationTestUtils.assertEqualsInMap;
import static starter.AutomationTestUtils.populateTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.xml.sax.SAXException;

import freemarker.template.TemplateException;

@SpringJUnitConfig(TestConfig.class)
@SqlConfig(dataSource = "applicationDataSource")
class StarterTests {
	@Autowired
	@Qualifier("applicationNamedParameterJdbcTemplate")
	NamedParameterJdbcTemplate pdrNamedParameterJdbcTemplate;

	@Autowired
	PersonRepository repository;

	@Test
	@Sql("/sql/test.sql")
	@DisplayName("Test Spring Data Insert")
	void testSpringDataJdbc()  {
		Person p = new Person(123l,"John1","Doe2",2003);
		repository.save(p);

		int rows = countRowsInTableWhere(pdrNamedParameterJdbcTemplate.getJdbcTemplate(), "PERSON", "PERSON_ID=123");

		assertEquals(1, rows);

		Person person =repository.findById(123l).orElseThrow(()-> new RuntimeException("no person"));
		
       
		assertEquals("John1",person.firstName);
		
		
	}
}
