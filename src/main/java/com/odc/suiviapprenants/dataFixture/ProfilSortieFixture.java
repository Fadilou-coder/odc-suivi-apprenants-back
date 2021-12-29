package com.odc.suiviapprenants.dataFixture;

import com.odc.suiviapprenants.model.Apprenant;
import com.odc.suiviapprenants.model.ProfilSortie;
import com.odc.suiviapprenants.model.Promo;
import com.odc.suiviapprenants.repository.ApprenantRepository;
import com.odc.suiviapprenants.repository.ProfilSortieRepository;
import com.odc.suiviapprenants.repository.PromoRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
@Order(13)
public class ProfilSortieFixture implements CommandLineRunner {
    private ProfilSortieRepository profilSortieRepository;
    private PromoRepository promoRepository;
    private ApprenantRepository apprenantRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Promo> promo = promoRepository.findAll();
        List<Apprenant> apprenants = apprenantRepository.findAll();

        List<Apprenant> apprenantList1 = new ArrayList<>();
        List<Promo> promosList1 = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            apprenantList1.add(apprenants.get(i));
        }
        for(int i = 0; i < 5; i++){
            promosList1.add(promo.get(i));
        }
        profilSortieRepository.saveAll(Arrays.asList(
                new ProfilSortie("Développeur back", promosList1, apprenantList1),
                new ProfilSortie("Développeur front", promosList1, apprenantList1),
                new ProfilSortie("Développeur fullstack",  promosList1, apprenantList1),
                new ProfilSortie("Intégrateur web",  promosList1, apprenantList1),
                new ProfilSortie("Designer web",  promosList1, apprenantList1),
                new ProfilSortie("Référent Digital",  promosList1, apprenantList1),
                new ProfilSortie("Community Manager",  promosList1, apprenantList1),
                new ProfilSortie("Data Scientist",  promosList1, apprenantList1),
                new ProfilSortie("IOT",  promosList1, apprenantList1)
        ));
    }
}
