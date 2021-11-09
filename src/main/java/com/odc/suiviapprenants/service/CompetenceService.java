package com.odc.suiviapprenants.service;
import com.odc.suiviapprenants.dto.CompetenceDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CompetenceService {
    CompetenceDto save(CompetenceDto competenceDto);

    List<CompetenceDto> findAll();

    CompetenceDto findById(Long id);

    void delete(Long id);

    CompetenceDto put(CompetenceDto competenceDto,Long id);

    CompetenceDto affectedNiveau(CompetenceDto competenceDto);

    List<CompetenceDto> competencesByRef(Long id);
}
