package com.odc.suiviapprenants;

import com.odc.suiviapprenants.model.Admin;
import com.odc.suiviapprenants.model.Role;
import com.odc.suiviapprenants.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SuiviapprenantsApplication {

	@Autowired
	private ApplicationService applicationService;

	/* @PostConstruct
	public void initUsers(){
		List<Admin> adminList = Stream.of(
				new Admin("admin", "passer", "prenom", "nom", "email@email.com", "12345678", "adresse", "777777777", new Role("ADMIN",null )),
		new Admin("formateur1", "passer", "formateur1", "formateur1", "formateur1@email.com", "12345678", "adresse", "1111111111", new Role("FORMATEUR",null )),
		new Admin("formateur2", "passer", "formateur2", "formateur2", "formateur2@email.com", "333333333", "adresse", "333333333", new Role("FORMATEUR",null )),
		new Admin("formateur3", "passer", "formateur3", "formateur3", "formateur3@email.com", "22222222222", "adresse", "2222222", new Role("FORMATEUR",null )),
		new Admin("cm", "passer", "cm", "cm", "cm@email.com", "66666666666", "adresse", "666666666666", new Role("CM",null )),
		new Admin("leadFormateur", "passer", "leadFormateur", "leadFormateur", "leadFormateur@email.com", "88888888888", "adresse", "8888888", new Role("LEADFORMATEUR",null ))
		).collect(Collectors.toList());
		applicationService.saveAllAdmin(adminList);
	}*/
	public static void main(String[] args) {
		SpringApplication.run(SuiviapprenantsApplication.class, args);
	}
}
