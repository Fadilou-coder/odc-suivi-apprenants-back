package com.odc.suiviapprenants.dataFixture;

import com.odc.suiviapprenants.model.Role;
import com.odc.suiviapprenants.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
@Order(1)
public class RoleFixture implements CommandLineRunner {
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        String[] roles = {"LEAD_FORMATEUR", "CM"};
        for (String i : roles) {
            roleRepository.save(new Role(i));
        }
    }
}
