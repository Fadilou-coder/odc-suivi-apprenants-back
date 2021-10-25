package com.odc.suiviapprenants.fixture;
import com.odc.suiviapprenants.model.Admin;
import com.odc.suiviapprenants.model.Competence;
import com.odc.suiviapprenants.model.Referentiel;
import com.odc.suiviapprenants.model.Role;
import com.odc.suiviapprenants.repository.ReferentielRepository;
import com.odc.suiviapprenants.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
@Order(4)
public class Fixture implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
            this.initUsers();
    }

private final ApplicationService applicationService;

	private final PasswordEncoder passwordEncoder;

    private final ReferentielRepository referentielRepository;

    public Fixture(ApplicationService applicationService, PasswordEncoder passwordEncoder, ReferentielRepository referentielRepository) {
        this.applicationService = applicationService;
        this.passwordEncoder = passwordEncoder;
        this.referentielRepository = referentielRepository;
    }


	public void initUsers(){
	/*	List<Admin> adminList = Stream.of(
				new Admin("admin", passwordEncoder.encode("passer"), "prenom", "nom", "email@email.com", "12345678", "adresse", "777777777", new Role("ADMIN",null )),
		new Admin("formateur1", passwordEncoder.encode("passer"), "formateur1", "formateur1", "formateur1@email.com", "5555555555555555", "adresse", "1111111111", new Role("FORMATEUR",null )),
		new Admin("formateur2", passwordEncoder.encode("passer"), "formateur2", "formateur2", "formateur2@email.com", "333333333", "adresse", "333333333", new Role("FORMATEUR",null )),
		new Admin("formateur3", passwordEncoder.encode("passer"), "formateur3", "formateur3", "formateur3@email.com", "22222222222", "adresse", "2222222", new Role("FORMATEUR",null )),
		new Admin("cm", passwordEncoder.encode("passer"), "cm", "cm", "cm@email.com", "66666666666", "adresse", "666666666666", new Role("CM",null )),
		new Admin("leadFormateur", passwordEncoder.encode("passer"), "leadFormateur", "leadFormateur", "leadFormateur@email.com", "88888888888", "adresse", "8888888", new Role("LEADFORMATEUR",null ))
		).collect(Collectors.toList());
		applicationService.saveAllAdmin(adminList);*/
		List<Competence> competenceList = Stream.of(
				new Competence("BD POSTGRESS"),
				new Competence("BD MYSQL"),
				new Competence("JAVA"),
				new Competence("PHP"),
				new Competence("SCRUM")
		).collect(Collectors.toList());
		applicationService.saveAllCompetence(competenceList);
        List<Referentiel> referentielList = Stream.of(
                new Referentiel("ref1","description1","crictereDev1","crictereAdmin1"),
                new Referentiel("ref2","description2","crictereDev2","crictereAdmin2"),
                new Referentiel("ref3","description3","crictereDev3","crictereAdmin3"),
                new Referentiel("ref4","description4","crictereDev4","crictereAdmin4")
        ).collect(Collectors.toList());
        referentielRepository.saveAll(referentielList);



	}

}
