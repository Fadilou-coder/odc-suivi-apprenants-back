package com.odc.suiviapprenants.dataFixture;

import com.odc.suiviapprenants.model.Competence;
import com.odc.suiviapprenants.model.GroupeCompetence;
import com.odc.suiviapprenants.repository.CompetenceRepository;
import com.odc.suiviapprenants.repository.GroupeCompetenceRepository;
import com.odc.suiviapprenants.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<Competence> competences = competenceRepository.findAll();
        List<Competence> competenceList1 = new ArrayList<>();
        List<Competence> competenceList2 = new ArrayList<>();
        List<Competence> competenceList3 = new ArrayList<>();
        List<Competence> competenceList4 = new ArrayList<>();
        List<Competence> competenceList5 = new ArrayList<>();
        for (int i=0; i <5 ; i++){
            competenceList1.add(competences.get(i));
        }
        for (int i=5; i <10 ; i++){
            competenceList2.add(competences.get(i));
        }
        for (int i=10; i <15 ; i++){
            competenceList3.add(competences.get(i));
        }
        for (int i=15; i <20 ; i++){
            competenceList4.add(competences.get(i));
        }
        for (int i=20; i <25 ; i++){
            competenceList5.add(competences.get(i));
        }
            groupeCompetenceRepository.save(new GroupeCompetence("Développer le front d'une application","description1",competenceList1,Arrays.asList(tagRepository.findByLibelleAndArchiveFalse("Laravel").get(),tagRepository.findByLibelleAndArchiveFalse("Ionic").get())));
            groupeCompetenceRepository.save(new GroupeCompetence("Développer le back-end d’une application web","description2",competenceList2,Arrays.asList(tagRepository.findByLibelleAndArchiveFalse("Laravel").get(),tagRepository.findByLibelleAndArchiveFalse("Ionic").get())));
            groupeCompetenceRepository.save(new GroupeCompetence("Développer une application angular","description3",competenceList3,Arrays.asList(tagRepository.findByLibelleAndArchiveFalse("Laravel").get(),tagRepository.findByLibelleAndArchiveFalse("Ionic").get())));
            groupeCompetenceRepository.save(new GroupeCompetence("Développer une application java","description4",competenceList4,Arrays.asList(tagRepository.findByLibelleAndArchiveFalse("Laravel").get(),tagRepository.findByLibelleAndArchiveFalse("Ionic").get())));
            groupeCompetenceRepository.save(new GroupeCompetence("Développer une application symfony","description5",competenceList5,Arrays.asList(tagRepository.findByLibelleAndArchiveFalse("Laravel").get(),tagRepository.findByLibelleAndArchiveFalse("Ionic").get())));
    }
}
