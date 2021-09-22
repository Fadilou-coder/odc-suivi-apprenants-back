package com.odc.suiviapprenants.dataFixture;

import com.odc.suiviapprenants.model.Apprenant;
import com.odc.suiviapprenants.repository.ApprenantRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
@Order(3)
public class ApprenantFixture implements CommandLineRunner {
    private ApprenantRepository apprenantRepository;

    @Override
    public void run(String... args) throws Exception {
        String password = "password";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);

        apprenantRepository.save(new Apprenant("apprenant", encodedPassword, "apprenant", "apprenant", "apprenant@gmail.com", "1 254 5678 90150", "adresse", "770000007", "EN COURS", "DEVWEB2020-12920001", "APPRENANT"));
    }
}
