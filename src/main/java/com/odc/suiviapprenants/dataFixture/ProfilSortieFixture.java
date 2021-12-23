package com.odc.suiviapprenants.dataFixture;

import com.odc.suiviapprenants.model.ProfilSortie;
import com.odc.suiviapprenants.model.Promo;
import com.odc.suiviapprenants.repository.ProfilSortieRepository;
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
@Order(13)
public class ProfilSortieFixture implements CommandLineRunner {
    private ProfilSortieRepository profilSortieRepository;
    private PromoRepository promoRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Promo> promoList = promoRepository.findAll();
        profilSortieRepository.saveAll(Arrays.asList(
                new ProfilSortie("Développeur back", promoList.get(1)),
                new ProfilSortie("Développeur front", promoList.get(1)),
                new ProfilSortie("Développeur fullstack", promoList.get(1)),
                new ProfilSortie("Intégrateur web", promoList.get(1)),
                new ProfilSortie("Designer web", promoList.get(1)),
                new ProfilSortie("Référent Digital", promoList.get(1)),
                new ProfilSortie("Community Manager", promoList.get(1)),
                new ProfilSortie("Data Scientist", promoList.get(1)),
                new ProfilSortie("IOT", promoList.get(1))
        ));
    }
}
