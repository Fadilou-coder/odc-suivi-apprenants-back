package com.odc.suiviapprenants.fixture;

import com.odc.suiviapprenants.model.Formateur;
import com.odc.suiviapprenants.repository.FormateurRepository;
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
@Order(6)
class FormateurFixture implements CommandLineRunner {

    FormateurRepository formateurRepository;
    @Override
    public void run(String... args) throws Exception {
        String password = "password";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        Formateur formateur = new Formateur();

        formateurRepository.saveAll(Arrays.asList(
              new Formateur("formatuer1",encodedPassword,"formateur1","nom1","email1@email.com","771234455","Formtaur"),
              new Formateur("formatuer2",encodedPassword,"formateur2","nom2","email2@email.com","77234455","Formtaur"),
              new Formateur("formatuer3",encodedPassword,"formateur3","nom2","email3@email.com","77334455","Formtaur"),
              new Formateur("formatuer4",encodedPassword,"formateur4","nom2","email4@email.com","77434455","Formtaur"),
              new Formateur("formatuer5",encodedPassword,"formateur5","nom2","email5@email.com","77534455","Formtaur")
        ));
    }
}
