package com.odc.suiviapprenants.dataFixture;

import com.odc.suiviapprenants.model.ProfilSortie;
import com.odc.suiviapprenants.repository.ProfilSortieRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@AllArgsConstructor
@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
@Order(13)
public class ProfilSortieFixture implements CommandLineRunner {
    private ProfilSortieRepository profilSortieRepository;

    @Override
    public void run(String... args) throws Exception {
        profilSortieRepository.saveAll(Arrays.asList(
                new ProfilSortie("Développeur back"),
                new ProfilSortie("Développeur front"),
                new ProfilSortie("Développeur fullstack"),
                new ProfilSortie("Intégrateur web"),
                new ProfilSortie("Designer web"),
                new ProfilSortie("Référent Digital"),
                new ProfilSortie("Community Manager"),
                new ProfilSortie("Data Scientist"),
                new ProfilSortie("IOTs")
        ));
    }
}
