package starter;

import javax.sql.DataSource;
import org.hsqldb.jdbc.JDBCDataSource;
import org.springframework.context.annotation.*;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import freemarker.cache.ClassTemplateLoader;
import org.springframework.beans.factory.annotation.*;
@Configuration
@EnableJdbcRepositories
public class TestConfig  extends AbstractJdbcConfiguration{

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

    @Bean("freemarkerTemplateConfiguraton")
    public freemarker.template.Configuration getFreemarkerTemplateConfiguration() {
        // Create your Configuration instance, and specify if up to what FreeMarker
        // version (here 2.3.29) do you want to apply the fixes that are not 100%
        // backward-compatible. See the Configuration JavaDoc for details.
        freemarker.template.Configuration cfg = new freemarker.template.Configuration();

        // From here we will set the settings recommended for new projects. These
        // aren't the defaults for backward compatibilty.

        // Set the preferred charset template files are stored in. UTF-8 is
        // a good choice in most applications:
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateLoader(new ClassTemplateLoader(this.getClass(), "/templates"));
        return cfg;
    }
}