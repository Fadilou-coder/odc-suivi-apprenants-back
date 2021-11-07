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
@Order(8)
public class GroupeCompetenceFixture implements CommandLineRunner {
    private GroupeCompetenceRepository groupeCompetenceRepository;
    private CompetenceRepository competenceRepository;
    private TagRepository tagRepository;

    @Override
    public void run(String... args) throws Exception {
        for (int i=0;i<15;i++){
            groupeCompetenceRepository.save(
                    new GroupeCompetence("libelle"+i,"description"+i,
                    Arrays.asList(competenceRepository.findByLibelleAndArchiveFalse("Créer une base de données").get(),
                            competenceRepository.findByLibelleAndArchiveFalse("Développer les composants d’accès aux données").get()),
                            Arrays.asList(tagRepository.findByLibelleAndArchiveFalse("Laravel").get(),tagRepository.findByLibelleAndArchiveFalse("Ionic").get())

                    ));
        }
    }
}
