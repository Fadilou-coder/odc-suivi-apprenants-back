package com.odc.suiviapprenants.dataFixture;

import com.odc.suiviapprenants.model.GroupeCompetence;
import com.odc.suiviapprenants.model.GroupeTag;
import com.odc.suiviapprenants.repository.GroupeTagRepository;
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
@Order(18)
public class GroupeTagFixture implements CommandLineRunner {
    private GroupeTagRepository groupeTagRepository;
    private TagRepository tagRepository;

    @Override
    public void run(String... args) throws Exception {
        groupeTagRepository.saveAll(Arrays.asList(
                new GroupeTag("Développement Mobile", Arrays.asList(tagRepository.findByLibelleAndArchiveFalse("Firebase").get(), tagRepository.findByLibelleAndArchiveFalse("Ionic").get())),
                new GroupeTag("Développement Web", Arrays.asList(tagRepository.findByLibelleAndArchiveFalse("PHP").get()))
        ));
    }
}
