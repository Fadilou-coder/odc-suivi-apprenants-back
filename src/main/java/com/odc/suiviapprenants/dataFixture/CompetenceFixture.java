package com.odc.suiviapprenants.dataFixture;
import com.odc.suiviapprenants.model.Competence;
import com.odc.suiviapprenants.repository.CompetenceRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@AllArgsConstructor
@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
@Order(5)
public class CompetenceFixture implements CommandLineRunner {
    private CompetenceRepository competenceRepository;

    @Override
    public void run(String... args) throws Exception {
        competenceRepository.saveAll(Arrays.asList(
                new Competence("BD POSTGRESS"),
                new Competence("GESTION DE PROJET"),
                new Competence("RIGUEUR ET METHODE"),
                new Competence("REALISATION DE REPORTING"),
                new Competence("Utiliser fichier json comme BD"),
                new Competence("ESPRIT D'ANALYSE"),
                new Competence("INFOGERANCE"),
                new Competence("REDACTION DU CAHIER DE CHARDE"),
                new Competence("BD MYSQL"),
                new Competence("METHODE AGILES"),
                new Competence("UML"),
                new Competence("SENS DU RELATIONNEL"),
                new Competence("Utiliser fichier xml comme BD"),
                new Competence("DEVELOPPEMENT LOGICIEL"),
                new Competence("JAVA"),
                new Competence("CONDUITE DE REUNION"),
                new Competence("SCRUM"),
                new Competence("ANALYSE FONCYIONNELLE"),
                new Competence("GESTION DE BASE DE DONNEES"),
                new Competence("MODEL MVC"),
                new Competence("REDACTION DES SPECIFICATIONS"),
                new Competence("CONNAISSANCE DES DESIGN PATTERNS"),
                new Competence("Développer les composants d’accès aux données"),
                new Competence("Créer une base de données"),
                new Competence("BD postgresql"),
                new Competence("Configuer security yml dans symfony")
        ));
    }
}
