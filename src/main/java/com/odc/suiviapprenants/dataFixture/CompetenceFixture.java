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
                new Competence("BD MYSQL"),
                new Competence("JAVA"),
                new Competence("Développer les composants d’accès aux données"),
                new Competence("Créer une base de données")
        ));
    }
}
