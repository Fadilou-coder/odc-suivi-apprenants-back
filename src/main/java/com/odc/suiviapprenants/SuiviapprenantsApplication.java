package com.odc.suiviapprenants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SuiviapprenantsApplication{
	public static void main(String[] args) {
		SpringApplication.run(SuiviapprenantsApplication.class, args);
	}
}
