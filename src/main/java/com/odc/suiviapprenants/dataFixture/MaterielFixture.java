package com.odc.suiviapprenants.dataFixture;

import com.odc.suiviapprenants.model.Materiel;
import com.odc.suiviapprenants.repository.MaterielRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@AllArgsConstructor
@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
@Order(11)
public class MaterielFixture implements CommandLineRunner {
    private MaterielRepository materielRepository;

    @Override
    public void run(String... args) throws Exception {
        materielRepository.saveAll(Arrays.asList(
                new Materiel("Machine", "Dell Core i5")
        ));
    }
}
