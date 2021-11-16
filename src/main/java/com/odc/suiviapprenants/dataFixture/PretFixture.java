package com.odc.suiviapprenants.dataFixture;

import com.odc.suiviapprenants.model.Pret;
import com.odc.suiviapprenants.repository.ApprenantRepository;
import com.odc.suiviapprenants.repository.MaterielRepository;
import com.odc.suiviapprenants.repository.PretRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@AllArgsConstructor
@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
@Order(16)
public class PretFixture implements CommandLineRunner {
    private PretRepository pretRepository;
    private ApprenantRepository apprenantRepository;
    private MaterielRepository materielRepository;

    @Override
    public void run(String... args) throws Exception {
        pretRepository.saveAll(Arrays.asList(
                new Pret("Reference", "En cours", apprenantRepository.findByEmail("apprenant@gmail.com0").get(), materielRepository.findByLibelleAndAndDescriptionAndArchiveFalse("Machine", "Dell Core i5").get())
        ));
    }
}
