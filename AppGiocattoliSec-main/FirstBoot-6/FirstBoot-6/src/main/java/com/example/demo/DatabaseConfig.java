package com.example.demo;




import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/*
 * Annotazione configuration significa che questo è un file di configurazione
 * qui andiamo a definire quali sono i dati di configuraziona al nostra database
 */
@Configuration
public class DatabaseConfig {
	/*
	 * Bean è un annotazione che va a definire un oggetto in springboot e lo rende valido e visibile in
	 * in tutta l'applicazione
	 * 
	 * il dataSouce bean vuol dire che tutta la applicazione è in grado di vedere quello che c'è questa classe quindi in jdbctemplate
	 * gia è in grado di interagire con il database
	 */
	@Bean
	public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mydb");
        dataSource.setUsername("root");
        dataSource.setPassword("Ilfoggia1");
        return dataSource;
    }

}