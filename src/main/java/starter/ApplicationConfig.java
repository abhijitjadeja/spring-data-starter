package starter;

import javax.sql.DataSource;

import org.hsqldb.jdbc.JDBCDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
@Configuration
@EnableJdbcRepositories
public class ApplicationConfig  extends AbstractJdbcConfiguration{

    @Bean("applicationDataSource")
    public DataSource dataSource() {
        JDBCDataSource jdbcDataSource = new JDBCDataSource();
        jdbcDataSource.setUrl("jdbc:hsqldb:mem:qa;sql.syntax_ora=true");
        return jdbcDataSource;
    }

    @Bean("applicationNamedParameterJdbcTemplate")
    @Autowired
    @Qualifier("applicationDataSource")
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource pdrDataSource) {
        return new NamedParameterJdbcTemplate(pdrDataSource);
    }

    @Bean("transactionManager")
    @Autowired
    public PlatformTransactionManager txnManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }


}