package com.odc.suiviapprenants.dataFixture;

import com.odc.suiviapprenants.model.Referentiel;
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
@Order(5)
public class ReferentielFixture implements CommandLineRunner {
    private ReferentielRepository referentielRepository;

    @Override
    public void run(String... args) throws Exception {
        referentielRepository.saveAll(Arrays.asList(
                new Referentiel(
                        "Développement Web et mobile",
                        "De l’analyse du besoin à la mise en ligne, en passant par l’interface et la base\n" + "de données, le·la développeur·se web conçoit et programme des applications\n" + "web.",
                        "Un portfolio comprenant la réalisation d'au moins 9 projets tout au long de la formation",
                        "Une soutenance devant un jury de professionnels"
                ),
                new Referentiel("ref2","description2","crictereDev2","crictereAdmin2"),
                new Referentiel("ref3","description3","crictereDev3","crictereAdmin3")
        ));
    }
}
