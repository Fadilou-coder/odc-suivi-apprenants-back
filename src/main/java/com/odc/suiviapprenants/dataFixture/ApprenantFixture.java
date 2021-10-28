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
                new Apprenant("Omar faye", encodedPassword, "Omar", "Faye", "apprenant1@gmail.com", "1 234 1678 90150", "adresse1", "771000007", "EN COURS", "DEVWEB2020-12920011", "APPRENANT"),
                new Apprenant("Fadilou sy", encodedPassword, "Fadilou", "SY", "apprenant3@gmail.com", "1 354 3678 90150", "adresse3", "773000007", "EN COURS", "DEVWEB2020-12920211", "APPRENANT"),
                new Apprenant("Amina ba", encodedPassword, "Amina", "ba", "apprenant4@gmail.com", "1 253 4678 90150", "adresse4", "774000007", "EN COURS", "DEVWEB2020-12922111", "APPRENANT"),
                new Apprenant("abdou sidibe", encodedPassword, "Abdou", "Sidibe", "apprenant5@gmail.com", "1 334 5678 90150", "adresse5", "775000007", "EN COURS", "DEVWEB2020-12921221", "APPRENANT"),
                new Apprenant("Maimouna", encodedPassword, "Maimouna", "ba", "apprenant6@gmail.com", "1 224 6678 90150", "adresse6", "7706000007", "EN COURS", "DEVWEB2020-12921331", "APPRENANT"),
                new Apprenant("Salif Abdoulah", encodedPassword, "Salif", "Sow", "apprenant8@gmail.com", "1 255 8678 90150", "adresse8", "777000007", "EN COURS", "DEVWEB2020-129224412", "APPRENANT"),
                new Apprenant("Mocktar Diallo", encodedPassword, "Mocktar", "Diallo", "apprenant9@gmail.com", "1 256 9678 90150", "adresse9", "778000007", "EN COURS", "DEVWEB2020-129245014", "APPRENANT"),
                new Apprenant("apprenant1", encodedPassword, "Ibou", "Ndiaye", "apprenant10@gmail.com", "1 256 9678 90151", "adresse9", "778000001", "EN COURS", "DEVWEB2020-129245014", "APPRENANT"),
                new Apprenant("apprenant2", encodedPassword, "Ndeye", "Sene", "apprenant11@gmail.com", "1 256 9678 90152", "adresse9", "778000002", "EN COURS", "DEVWEB2020-129245014", "APPRENANT"),
                new Apprenant("apprenant3", encodedPassword, "Maimouna", "Wone", "apprenant12@gmail.com", "1 256 9678 90153", "adresse9", "778000003", "EN COURS", "DEVWEB2020-129245014", "APPRENANT"),
                new Apprenant("apprenant4", encodedPassword, "Rassoul", "Mbodj", "apprenant13@gmail.com", "1 256 9678 90154", "adresse9", "778000004", "EN COURS", "DEVWEB2020-129245014", "APPRENANT"),
                new Apprenant("apprenant5", encodedPassword, "Fatou", "Dieng", "apprenant14@gmail.com", "1 256 9678 90155", "adresse9", "778000005", "EN COURS", "DEVWEB2020-129245014", "APPRENANT"),
                new Apprenant("apprenant6", encodedPassword, "Ablaye", "Sall", "apprenant15@gmail.com", "1 256 9678 90156", "adresse9", "778000006", "EN COURS", "DEVWEB2020-129245014", "APPRENANT"),
                new Apprenant("apprenant7", encodedPassword, "Ramatoulaye", "Diallo", "apprenant16@gmail.com", "1 256 9678 90157", "adresse9", "778000008", "EN COURS", "DEVWEB2020-129245014", "APPRENANT"),
                new Apprenant("apprenant8", encodedPassword, "Aicha", "Kante", "apprenant17@gmail.com", "1 256 9678 90180", "adresse9", "778000009", "EN COURS", "DEVWEB2020-129245014", "APPRENANT"),
                new Apprenant("apprenant9", encodedPassword, "Modou", "Diouf", "apprenant18@gmail.com", "1 256 9678 90190", "adresse9", "778000010", "EN COURS", "DEVWEB2020-129245014", "APPRENANT"),
                new Apprenant("apprenant10", encodedPassword, "Salif", "Sow", "apprenant19@gmail.com", "1 256 9678 90159", "adresse9", "778000012", "EN COURS", "DEVWEB2020-129245014", "APPRENANT"),
                new Apprenant("apprenant11", encodedPassword, "Babacar", "Sy", "apprenant20@gmail.com", "1 256 9678 90158", "adresse9", "778000013", "EN COURS", "DEVWEB2020-129245014", "APPRENANT"),
                new Apprenant("apprenant12", encodedPassword, "Oumar", "Diallo", "apprenant21@gmail.com", "1 256 9678 90650", "adresse9", "778000014", "EN COURS", "DEVWEB2020-129245014", "APPRENANT"),
                new Apprenant("apprenant13", encodedPassword, "Maguette", "Seck", "apprenant22@gmail.com", "1 256 9678 90170", "adresse9", "778000017", "EN COURS", "DEVWEB2020-129245014", "APPRENANT")
        ));
    }
}
