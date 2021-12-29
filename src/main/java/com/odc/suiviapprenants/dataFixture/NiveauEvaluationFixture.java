package com.odc.suiviapprenants.dataFixture;

import com.odc.suiviapprenants.model.Competence;
import com.odc.suiviapprenants.model.NiveauEvaluation;
import com.odc.suiviapprenants.model.Referentiel;
import com.odc.suiviapprenants.repository.CompetenceRepository;
import com.odc.suiviapprenants.repository.NiveauEvaluationRepository;
import com.odc.suiviapprenants.repository.ReferentielRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

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
        List<Competence> competenceList = competenceRepository.findAll();
        List<Referentiel> referentiels = referentielRepository.findAllByArchiveFalse();
        competenceList.forEach(competence -> {
            niveauEvaluationRepository.saveAll(Arrays.asList(
                    new NiveauEvaluation("Niveau 1", "D’autres attentes sont exprimées dans les offres d’emploi par les recruteurs. Ainsi, parmi les qualités ", "Concevoir et développer un site : conception, modélisation et architecture d’applications, méthodes, normes, langages et outils de développement", competence, referentiels.get(0)),
                    new NiveauEvaluation("Niveau 2", "Maîtriser l’anglais un minimum (pour comprendre les différents langages de code comme le html par exemple", "Analyse, programmation et publication sont les trois grandes missions du développeur informatique.", competence, referentiels.get(0)),
                    new NiveauEvaluation("Niveau 3", "D’autres attentes sont exprimées dans les offres d’emploi par les recruteurs. Ainsi, parmi les qualités ", "Maîtriser l’anglais un minimum (pour comprendre les différents langages de code comme le html par exemple", competence, referentiels.get(0))
            ));
        });

    }
}
