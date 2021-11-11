package com.odc.suiviapprenants.dataFixture;

import com.odc.suiviapprenants.model.Tag;
import com.odc.suiviapprenants.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@AllArgsConstructor
@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
@Order(6)
public class TagFixture implements CommandLineRunner {
    private TagRepository tagRepository;

    @Override
    public void run(String... args) throws Exception {
        tagRepository.saveAll(Arrays.asList(
                new Tag("Laravel"),
                new Tag("Ionic"),
                new Tag("MySQL"),
                new Tag("Firebase"),
                new Tag("PHP")
        ));
    }
}
