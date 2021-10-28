package com.odc.suiviapprenants.dataFixture;


import com.odc.suiviapprenants.model.Apprenant;
import com.odc.suiviapprenants.model.Formateur;
import com.odc.suiviapprenants.model.Groupe;
import com.odc.suiviapprenants.model.Promo;
import com.odc.suiviapprenants.repository.ApprenantRepository;
import com.odc.suiviapprenants.repository.FormateurRepository;
import com.odc.suiviapprenants.repository.GroupeRepository;
import com.odc.suiviapprenants.repository.PromoRepository;
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
@Order(15)
class GroupeFixture implements CommandLineRunner {
    GroupeRepository groupeRepository;
    FormateurRepository formateurRepository;
    PromoRepository promoRepository;
    ApprenantRepository apprenantRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Formateur> formateurs = formateurRepository.findAll();
        List<Apprenant> apprenants = apprenantRepository.findAll();
        List<Promo> promoList = promoRepository.findAll();

        for(int i = 0; i < promoList.toArray().length; i++){
            if(i == 0 || i == 1 ) {
                groupeRepository.save(new Groupe("groupe_" + i,"plusieurs","ouvert", promoList.get(i), formateurs, apprenants));
            }
            else {
                groupeRepository.save(new Groupe("groupe_" + i,"binome","ouvert", promoList.get(i), formateurs, Arrays.asList(apprenants.get(0), apprenants.get(1))));
            }
        };

    }
}
