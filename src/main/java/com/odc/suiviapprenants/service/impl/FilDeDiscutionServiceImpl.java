package com.odc.suiviapprenants.service.impl;

import com.odc.suiviapprenants.dto.*;
import com.odc.suiviapprenants.model.BriefApprenant;
import com.odc.suiviapprenants.repository.*;
import com.odc.suiviapprenants.service.FilDeDiscutionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class FilDeDiscutionServiceImpl implements FilDeDiscutionService {

    FildeDiscutionRepositoty fildeDiscutionRepositoty;
    BriefApprenantRepository briefApprenantRepository;
    ApprenantRepository apprenantRepository;
    MessageRepository messageRepository;
    FormateurRepository formateurRepository;

    @Override
    public List<FilDeDiscutionDto> findAll() {
        return fildeDiscutionRepositoty.findAll().stream().map(FilDeDiscutionDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public FilDeDiscutionDto findAllByBrief(Long id) {
        List<BriefApprenant> briefApprenants = briefApprenantRepository.findAllByBriefId(id);
        List<FilDeDiscutionDto> filDeDiscutions = new ArrayList<>();
        briefApprenants.forEach(briefApprenant -> {
            if (fildeDiscutionRepositoty.findByBriefApprenantId(briefApprenant.getId()).isPresent()){
                filDeDiscutions.add(FilDeDiscutionDto.fromEntity(fildeDiscutionRepositoty.findByBriefApprenantId(briefApprenant.getId()).get()));

            }
        });
        if (filDeDiscutions.size() > 0)
            return filDeDiscutions.get(0);
        return null;
    }

    @Override
    public MessageDto saveMessage(String libelle, MultipartFile pieceJointe, Long idUser, Long id) throws IOException {
        if (fildeDiscutionRepositoty.findById(id).isPresent() && apprenantRepository.findById(idUser).isPresent()) {
            MessageDto messageDto = new MessageDto(null, libelle, pieceJointe.getBytes(), FilDeDiscutionDto.fromEntity(fildeDiscutionRepositoty.findById(id).get()), ApprenantDto.fromEntity(apprenantRepository.findById(idUser).get()), null);
            return MessageDto.fromEntity(messageRepository.save(MessageDto.toEntity(messageDto)));
        }else if (fildeDiscutionRepositoty.findById(id).isPresent() && formateurRepository.findById(idUser).isPresent()){
            MessageDto messageDto = new MessageDto(null, libelle, pieceJointe.getBytes(), FilDeDiscutionDto.fromEntity(fildeDiscutionRepositoty.findById(id).get()), null, FormateurDto.fromEntity(formateurRepository.findById(idUser).get()));
            return MessageDto.fromEntity(messageRepository.save(MessageDto.toEntity(messageDto)));
        }
        return null;
    }


    @Override
    public FilDeDiscutionDto saveFilDeDiscutionBrief(FilDeDiscutionDto filDeDiscutionDto, Long id, Long idApp) {
        if (briefApprenantRepository.findByBriefIdAndApprenantId(id, idApp).isPresent()) {
            BriefApprenant briefApprenant = briefApprenantRepository.findByBriefIdAndApprenantId(id, idApp).get();
            if (apprenantRepository.findById(idApp).isPresent())
                filDeDiscutionDto.setApprenant(ApprenantDto.fromEntity(apprenantRepository.findById(idApp).get()));
            filDeDiscutionDto.setBriefApprenant(BriefApprenantDto.fromEntity(briefApprenant));
            return FilDeDiscutionDto.fromEntity(
                    fildeDiscutionRepositoty.save(FilDeDiscutionDto.toEntity(filDeDiscutionDto))
            );
        }
        return null;
    }
}
