package com.odc.suiviapprenants.dataFixture;

import com.odc.suiviapprenants.model.Referentiel;
import com.odc.suiviapprenants.repository.FormateurRepository;
import com.odc.suiviapprenants.repository.GroupeCompetenceRepository;
import com.odc.suiviapprenants.repository.ReferentielRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
@Order(9)
public class ReferentielFixture implements CommandLineRunner {
    private ReferentielRepository referentielRepository;
    private GroupeCompetenceRepository groupeCompetenceRepository;
    private FormateurRepository formateurRepository;

    @Override
    public void run(String... args) throws Exception {
        List<String> referentielLibelle = new ArrayList<>();
        referentielLibelle.add("DEVELOPPEUR WEB & MOBILE");
        referentielLibelle.add("DEVELOPPEUR DATA");
        referentielLibelle.add("REFERENTIEL DIGITAL");
        referentielLibelle.add("COMPETENCES NUMERIQUS FONDAMENTALE");
        referentielLibelle.add("DEVELOPPEUR DATA & INTELLIGENCE ARTIFICIELLE");
        referentielLibelle.add("OBJETS CONNECTES");
        for(int i=0;i < referentielLibelle.toArray().length;i++ ){
            referentielRepository.save(new Referentiel(referentielLibelle.get(i),"description"+i,"crictereDev"+i,"crictereAdmin"+i,groupeCompetenceRepository.findAll(), formateurRepository.findAll()));
        }
    }
}
