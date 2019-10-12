package starter;

import org.springframework.jdbc.datasource.embedded.*;
import org.springframework.context.annotation.*;
import org.springframework.data.jdbc.repository.config.*;
import javax.sql.DataSource;

@Configuration
@EnableJdbcRepositories
class ApplicationConfig extends AbstractJdbcConfiguration {

  @Bean
  public DataSource dataSource() {

    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
    return builder.setType(EmbeddedDatabaseType.HSQL).build();
  }

}