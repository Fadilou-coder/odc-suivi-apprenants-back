package com.odc.suiviapprenants.dataFixture;

import com.odc.suiviapprenants.model.GroupeCompetence;
import com.odc.suiviapprenants.repository.CompetenceRepository;
import com.odc.suiviapprenants.repository.GroupeCompetenceRepository;
import com.odc.suiviapprenants.repository.ReferentielRepository;
import com.odc.suiviapprenants.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@AllArgsConstructor
@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
@Order(17)
public class GroupeCompetenceFixture implements CommandLineRunner {
    private GroupeCompetenceRepository groupeCompetenceRepository;
    private CompetenceRepository competenceRepository;
    private ReferentielRepository referentielRepository;
    private TagRepository tagRepository;

    @Override
    public void run(String... args) throws Exception {
        groupeCompetenceRepository.saveAll(Arrays.asList(
                new GroupeCompetence("Développer le back-end d’une application web", "Description",
                        Arrays.asList(competenceRepository.findByLibelleAndArchiveFalse("Créer une base de données").get(), competenceRepository.findByLibelleAndArchiveFalse("Développer les composants d’accès aux données").get()),
                        Arrays.asList(referentielRepository.findByLibelle("ref1").get(), referentielRepository.findByLibelle("ref2").get()),
                        Arrays.asList(tagRepository.findByLibelleAndArchiveFalse("MySQL").get(), tagRepository.findByLibelleAndArchiveFalse("PHP").get())),
                new GroupeCompetence("Développer le front-end d’une application web", "Description",
                        Arrays.asList(competenceRepository.findByLibelleAndArchiveFalse("BD MYSQL").get(), competenceRepository.findByLibelleAndArchiveFalse("Créer une base de données").get()),
                        Arrays.asList(referentielRepository.findByLibelle("ref1").get(), referentielRepository.findByLibelle("ref2").get()),
                        Arrays.asList(tagRepository.findByLibelleAndArchiveFalse("MySQL").get(), tagRepository.findByLibelleAndArchiveFalse("PHP").get())
                )));
    }
}
