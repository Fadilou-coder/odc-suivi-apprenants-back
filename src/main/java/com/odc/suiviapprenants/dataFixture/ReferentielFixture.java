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
                new Referentiel("ref1","description1","crictereDev1","crictereAdmin1"),
                new Referentiel("ref2","description2","crictereDev2","crictereAdmin2"),
                new Referentiel("ref3","description3","crictereDev3","crictereAdmin3"),
                new Referentiel("ref4","description4","crictereDev4","crictereAdmin4")
        ));
    }
}
