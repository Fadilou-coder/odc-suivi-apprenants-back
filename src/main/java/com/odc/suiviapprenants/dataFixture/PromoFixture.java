package com.odc.suiviapprenants.dataFixture;

import com.odc.suiviapprenants.model.Promo;
import com.odc.suiviapprenants.model.Referentiel;
import com.odc.suiviapprenants.repository.FormateurRepository;
import com.odc.suiviapprenants.repository.PromoRepository;
import com.odc.suiviapprenants.repository.ReferentielRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
@Order(10)
public class PromoFixture implements CommandLineRunner {
    PromoRepository promoRepository;
    ReferentielRepository referentielRepository;
    FormateurRepository formateurRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Referentiel> referentiels = referentielRepository.findAll();
        for(int i=0;i < referentiels.toArray().length;i++ ){
            if (i == 0) {
                promoRepository.save(new Promo("francais", "dev_" + i, "description", "dakar", "enCours", referentiels.get(i), true, formateurRepository.findAll(), LocalDate.parse("2020-02-21"),LocalDate.parse("2021-02-21")));
            }else {
                promoRepository.save(new Promo("francais", "dev_" + i, "description", "dakar", "cloturer", referentiels.get(i), false, formateurRepository.findAll(),LocalDate.parse("2022-02-21"),LocalDate.parse("2023-02-21")));
            }
        }
    }
}
