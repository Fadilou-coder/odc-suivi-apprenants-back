package com.odc.suiviapprenants.dataFixture;

import com.odc.suiviapprenants.model.Apprenant;
import com.odc.suiviapprenants.model.Groupe;
import com.odc.suiviapprenants.repository.ApprenantRepository;
import com.odc.suiviapprenants.repository.GroupeRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
/*@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
@Order(15)*/
public class GroupeApprenantsFixture implements CommandLineRunner {
    GroupeRepository groupeRepository;
    ApprenantRepository apprenantRepository;
    @Override
    public void run(String... args) throws Exception {
        /*List<Groupe> groupeList = groupeRepository.findAll();
        List<Apprenant> apprenantList = apprenantRepository.findAll();
        List<Apprenant> apprenants = new ArrayList<>();
        for (int i=0; i< 10;i++){
            apprenants.add(apprenantList.get(i));
        }
        groupeList.get(0).setApprenants(apprenants);
        apprenants = new ArrayList<>();
        for (int i=10;i<20;i++){
            apprenants.add(apprenantList.get(i));
        }
        groupeList.get(1).setApprenants(apprenants);*/
    }
}
