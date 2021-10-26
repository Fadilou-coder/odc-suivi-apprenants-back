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

import java.util.Arrays;

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

        apprenantRepository.saveAll(Arrays.asList(
                new Apprenant("Ommar faye", encodedPassword, "Omar", "Faye", "apprenant1@gmail.com", "1 234 1678 90150", "adresse1", "771000007", "EN COURS", "DEVWEB2020-12920011", "APPRENANT1"),
                new Apprenant("Fadilou sy", encodedPassword, "Fadilou", "SY", "apprenant3@gmail.com", "1 354 3678 90150", "adresse3", "773000007", "EN COURS", "DEVWEB2020-12920211", "APPRENANT2"),
                new Apprenant("Amina ba", encodedPassword, "Amina", "ba", "apprenant4@gmail.com", "1 253 4678 90150", "adresse4", "774000007", "EN COURS", "DEVWEB2020-12922111", "APPRENANT3"),
                new Apprenant("abdou sidibe", encodedPassword, "Abdou", "Sidibe", "apprenant5@gmail.com", "1 334 5678 90150", "adresse5", "775000007", "EN COURS", "DEVWEB2020-12921221", "APPRENANT4"),
                new Apprenant("Maimouna", encodedPassword, "Maimouna", "ba", "apprenant6@gmail.com", "1 224 6678 90150", "adresse6", "7706000007", "EN COURS", "DEVWEB2020-12921331", "APPRENANT5"),
                new Apprenant("Salif Abdoulah", encodedPassword, "Salif", "Sow", "apprenant8@gmail.com", "1 255 8678 90150", "adresse8", "777000007", "EN COURS", "DEVWEB2020-129224412", "APPRENANT6"),
                new Apprenant("Mocktar Diallo", encodedPassword, "Mocktar", "Diallo", "apprenant9@gmail.com", "1 256 9678 90150", "adresse9", "778000007", "EN COURS", "DEVWEB2020-129245014", "APPRENANT7")
        ));
    }
}
