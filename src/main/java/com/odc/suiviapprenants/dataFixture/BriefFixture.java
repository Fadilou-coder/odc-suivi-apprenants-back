package com.odc.suiviapprenants.dataFixture;

import com.odc.suiviapprenants.dto.LivrablesAttendusDto;
import com.odc.suiviapprenants.model.Brief;
import com.odc.suiviapprenants.repository.BriefRepository;
import com.odc.suiviapprenants.repository.FormateurRepository;
import com.odc.suiviapprenants.repository.PromoRepository;
import com.odc.suiviapprenants.service.BriefService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@AllArgsConstructor
@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
@Order(17)
public class BriefFixture implements CommandLineRunner {
    private BriefRepository briefRepository;
    private FormateurRepository formateurRepository;
    private PromoRepository promoRepository;

    @Override
    public void run(String... args) throws Exception {
        briefRepository.saveAll(Arrays.asList(
                new Brief("Creer une Maquette Web", "Conception maquette responsive en se basant sur les standard du W3C et découpage de la maquette en vue de l’intégration.", "contexte maquette", LocalDate.now(), "modalite pedagogique maquette", "critère de performances maquette", "modalite d'evaluation maquette", "Clôturé", true, formateurRepository.findAll().get(0), promoRepository.findAll().get(0)),
                new Brief("Integration d'une Maquette responsive", "Intégration responsive d'une maquette en se basant sur les standard du W3C et les checklists d'OPQUAST pour la qualité.", "contexte responsivité", LocalDate.now(), "modalite pedagogique responsivité", "critère de performances responsivité", "modalite d'evaluation responsivité", "En cours", false, formateurRepository.findAll().get(0), promoRepository.findAll().get(0)),
                new Brief("Comment choisir son CMS sur son site Web", "Créer, structurer et/ou modifier un document textuel, pour communiquer des idées, rendre compte et valoriser ses travaux (éditeur de texte, logiciel de présentation, site web sans coder).", "contexte site Web", LocalDate.now(), "modalite pedagogique site Web", "critère de performances site Web", "modalite d'evaluation site Web", "Non assigné", false, formateurRepository.findAll().get(0), promoRepository.findAll().get(0)),
                new Brief("Sql pour créer et exploiter une base de données", "Le langage SQL pour créer et exploiter une base de données. Ce brief a pour ambition de présenter de façon simple et complète le langage SQL et ses applications pour le développement.", "contexte Sql", LocalDate.now(), "modalite pedagogique Sql", "critère de performances Sql", "modalite d'evaluation Sql", "Clôturé", false, formateurRepository.findAll().get(0), promoRepository.findAll().get(0))
        ));
    }
}
