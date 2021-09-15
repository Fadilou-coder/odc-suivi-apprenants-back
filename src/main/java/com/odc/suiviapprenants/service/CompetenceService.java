package com.odc.suiviapprenants.service;
import com.odc.suiviapprenants.dto.CompetenceDto;
import java.util.List;

public interface CompetenceService {
    CompetenceDto save(CompetenceDto competenceDto);

    List<CompetenceDto> findAll();

    CompetenceDto findById(Long id);

    void delete(Long id);

    CompetenceDto put(CompetenceDto competenceDto,Long id);
}
