package com.odc.suiviapprenants;

import com.odc.suiviapprenants.model.Admin;
import com.odc.suiviapprenants.model.Competence;
import com.odc.suiviapprenants.model.Role;
import com.odc.suiviapprenants.service.ApplicationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SuiviapprenantsApplication {

	@Bean
	public ModelMapper modelMapper (){
		return new ModelMapper();
	}
//	@Autowired
//	private ApplicationService applicationService;
//
//	@Autowired
//	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//
//	@PostConstruct
//	public void initUsers(){
//		List<Admin> adminList = Stream.of(
//				new Admin("admin", passwordEncoder.encode("passer"), "prenom", "nom", "email@email.com", "12345678", "adresse", "777777777", new Role("ADMIN",null )),
//		new Admin("formateur1", passwordEncoder.encode("passer"), "formateur1", "formateur1", "formateur1@email.com", "5555555555555555", "adresse", "1111111111", new Role("FORMATEUR",null )),
//		new Admin("formateur2", passwordEncoder.encode("passer"), "formateur2", "formateur2", "formateur2@email.com", "333333333", "adresse", "333333333", new Role("FORMATEUR",null )),
//		new Admin("formateur3", passwordEncoder.encode("passer"), "formateur3", "formateur3", "formateur3@email.com", "22222222222", "adresse", "2222222", new Role("FORMATEUR",null )),
//		new Admin("cm", passwordEncoder.encode("passer"), "cm", "cm", "cm@email.com", "66666666666", "adresse", "666666666666", new Role("CM",null )),
//		new Admin("leadFormateur", passwordEncoder.encode("passer"), "leadFormateur", "leadFormateur", "leadFormateur@email.com", "88888888888", "adresse", "8888888", new Role("LEADFORMATEUR",null ))
//		).collect(Collectors.toList());
//		applicationService.saveAllAdmin(adminList);
//		List<Competence> competenceList = Stream.of(
//				new Competence("BD POSTGRESS"),
//				new Competence("BD MYSQL"),
//				new Competence("JAVA"),
//				new Competence("PHP"),
//				new Competence("SCRUM")
//		).collect(Collectors.toList());
//		applicationService.saveAllCompetence(competenceList);
//
//
//
//	}
	public static void main(String[] args) {
		SpringApplication.run(SuiviapprenantsApplication.class, args);
	}
}
