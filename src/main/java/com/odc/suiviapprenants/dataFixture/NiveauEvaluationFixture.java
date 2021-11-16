package com.odc.suiviapprenants.dataFixture;

import com.odc.suiviapprenants.model.NiveauEvaluation;
import com.odc.suiviapprenants.repository.CompetenceRepository;
import com.odc.suiviapprenants.repository.NiveauEvaluationRepository;
import com.odc.suiviapprenants.repository.ReferentielRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@AllArgsConstructor
@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
@Order(14)
public class NiveauEvaluationFixture implements CommandLineRunner {
    private NiveauEvaluationRepository niveauEvaluationRepository;
    private CompetenceRepository competenceRepository;
    private ReferentielRepository referentielRepository;

    @Override
    public void run(String... args) throws Exception {
        niveauEvaluationRepository.saveAll(Arrays.asList(
                new NiveauEvaluation("Niveau 1", "Groupe d'action 1", "Critère d'évaluation 1", competenceRepository.findByLibelleAndArchiveFalse("Créer une base de données").get(), referentielRepository.findByLibelle("ref1").get()),
                new NiveauEvaluation("Niveau 2", "Groupe d'action 2", "Critère d'évaluation 2", competenceRepository.findByLibelleAndArchiveFalse("Créer une base de données").get(), referentielRepository.findByLibelle("ref1").get()),
                new NiveauEvaluation("Niveau 3", "Groupe d'action 3", "Critère d'évaluation 3", competenceRepository.findByLibelleAndArchiveFalse("Créer une base de données").get(), referentielRepository.findByLibelle("ref1").get())
        ));
    }
}
