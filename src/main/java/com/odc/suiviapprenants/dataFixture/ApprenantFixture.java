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

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

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

        for (int i = 0; i < 50; i++) {
            apprenantRepository.save(
                    new Apprenant("apprenant"+i, encodedPassword, "apprenant_prenom"+i,
                            "apprenant_nom"+i, "apprenant@gmail.com"+i,
                            "1 254 5678 901"+i, "adresse"+i, "7700000"+i, "EN COURS",
                            "DEVWEB2020-12920001", "APPRENANT",LocalDate.parse("1990-02-21"))
            );
        }
    }
}
