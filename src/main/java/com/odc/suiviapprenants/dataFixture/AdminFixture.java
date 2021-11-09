package com.odc.suiviapprenants.dataFixture;

import com.odc.suiviapprenants.model.Admin;
import com.odc.suiviapprenants.repository.AdminRepository;
import com.odc.suiviapprenants.repository.RoleRepository;
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
@Order(2)
public class AdminFixture implements CommandLineRunner {
    private AdminRepository adminRepository;
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        String password = "password";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);

        adminRepository.saveAll(Arrays.asList(
                new Admin("leadFormateur", encodedPassword, "leadFormateur", "leadFormateur", "leadFormateur@gmail.com", "1 234 5678 90124", "adresse", "770000001", roleRepository.findByLibelle("LEAD_FORMATEUR")),
                new Admin("cm", encodedPassword, "cm", "cm", "cm@gmail.com", "1 234 5678 90125", "adresse", "770000003", roleRepository.findByLibelle("CM"))
        ));
    }
}
