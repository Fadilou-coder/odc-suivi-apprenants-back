package com.odc.suiviapprenants.dataFixture;

import com.odc.suiviapprenants.model.Promo;
import com.odc.suiviapprenants.model.Referentiel;
import com.odc.suiviapprenants.repository.PromoRepository;
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
public class PromoFixture implements CommandLineRunner {
    PromoRepository promoRepository;
    ReferentielRepository referentielRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Referentiel> referentiels = referentielRepository.findAll();
        for(int i=0;i < referentiels.toArray().length;i++ ){
            promoRepository.save(new Promo("francais","dev_"+i,"description","dakar","enCours", referentiels.get(i)));
        }
    }
}
