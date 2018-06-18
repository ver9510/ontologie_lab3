package ontologie_lab3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import java.util.Properties;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@Configuration
@EnableJpaRepositories(basePackages = {"ontologie_lab3.repositories"})
public class DBConfig {
//    @Bean
    public DataSource dataSourceH2() {
        return new EmbeddedDatabaseBuilder().setType(H2)
                .setName("ontologie_lab3")
                .setScriptEncoding("UTF-8")
                .build();
    }

//    @Bean
    public DataSource dataSourceMySQL() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3305/ontologie_lab3?useSSL=false");
        driverManagerDataSource.setUsername("username");
        driverManagerDataSource.setPassword("1234");
        return driverManagerDataSource;
    }

    @Bean
    public DataSource dataSource(){
        return dataSourceH2();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSource);
        lef.setJpaVendorAdapter(jpaVendorAdapter);
        lef.setJpaProperties(additionalPropertiesH2());
        lef.setPackagesToScan("ontologie_lab3");
        return lef;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }


    Properties additionalPropertiesMySQL()
    {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        properties.setProperty("javax.persistence.sql-load-script-source", "data-mysql.sql");
//        properties.setProperty("hibernate.show_sql", "true");
//        properties.setProperty("entitymanager.packagesToScan", "ontologie_lab3");
        return properties;
    }

    Properties additionalPropertiesH2()
    {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.setProperty("javax.persistence.sql-load-script-source", "data-h2.sql");
//        properties.setProperty("hibernate.connection.characterEncoding", "UTF-8");
//        properties.setProperty("hibernate.connection.useUnicode", "true");

//        properties.setProperty("hibernate.show_sql", "true");
//        properties.setProperty("entitymanager.packagesToScan", "ontologie_lab3");
        return properties;
    }

}