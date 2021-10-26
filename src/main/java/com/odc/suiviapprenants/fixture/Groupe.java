package com.odc.suiviapprenants.fixture;


import com.odc.suiviapprenants.model.Formateur;
import com.odc.suiviapprenants.model.Groupe;
import com.odc.suiviapprenants.model.Promo;
import com.odc.suiviapprenants.repository.FormateurRepository;
import com.odc.suiviapprenants.repository.GroupeRepository;
import com.odc.suiviapprenants.repository.PromoRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.List;

@AllArgsConstructor
@Component
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
@Order(8)
class GroupeFixture implements CommandLineRunner {
    GroupeRepository groupeRepository;
    FormateurRepository formateurRepository;
    PromoRepository promoRepository;
    @Override
    public void run(String... args) throws Exception {
        List<Formateur> formateurList = formateurRepository.findAll();
        List<Promo> promoList = promoRepository.findAll();
       for(int i=0; i <promoList.toArray().length; i++){
           groupeRepository.save(new Groupe("groupe_"+i,"binome","ouvert",promoList.get(i),formateurList));
        };

    }
}
