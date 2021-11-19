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

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
@Order(11)
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
        List<Apprenant> apprenantList1 = new ArrayList<>();
        List<Apprenant> apprenantList2 = new ArrayList<>();
        for(int i = 0; i < 25; i++){
            apprenantList1.add(apprenants.get(i));
        }
        for(int i = 25; i < 50; i++){
            apprenantList2.add(apprenants.get(i));
        }

        for (int i = 0 ; i < promoList.toArray().length; i++){
            groupeRepository.save(new Groupe("GROUPE PRINCIPALE", "principale", "ouvert", promoList.get(i), formateurs, apprenants));
            groupeRepository.save(  new Groupe("groupe "+1, "plusieurs", "ouvert", promoList.get(i), formateurs, apprenantList1));
            groupeRepository.save( new Groupe("groupe "+2, "plusieurs", "ouvert", promoList.get(i), formateurs, apprenantList2));
        }
    }
}
