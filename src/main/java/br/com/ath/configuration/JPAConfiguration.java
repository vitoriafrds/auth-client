package br.com.ath.configuration;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JPAConfiguration {

    private static String USERNAME = "vitoria";
    private static String PASSWORD = "thomaz0806";

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/auth_client?useTimezone=true&serverTimezone=UTC");
        dataSourceBuilder.username(USERNAME);
        dataSourceBuilder.password(PASSWORD);
        return dataSourceBuilder.build();
    }

}
