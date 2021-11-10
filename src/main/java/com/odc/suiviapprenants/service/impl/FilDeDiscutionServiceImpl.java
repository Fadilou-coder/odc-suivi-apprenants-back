package com.odc.suiviapprenants.service.impl;

import com.odc.suiviapprenants.dto.*;
import com.odc.suiviapprenants.model.BriefApprenant;
import com.odc.suiviapprenants.model.FilDiscussion;
import com.odc.suiviapprenants.repository.*;
import com.odc.suiviapprenants.service.FilDeDiscutionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    ReponseRepository reponseRepository;
    FormateurRepository formateurRepository;

    @Override
    public List<FilDeDiscutionDto> findAll() {
        return fildeDiscutionRepositoty.findAll().stream().map(FilDeDiscutionDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<FilDeDiscutionDto> findAllByBrief(Long id, Long idApp) {
        BriefApprenant briefApprenant = briefApprenantRepository.findByBriefIdAndApprenantId(id, idApp).get();
        return fildeDiscutionRepositoty.findAllByBriefApprenantId(briefApprenant.getId()).stream().map(FilDeDiscutionDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public MessageDto saveMessage(String libelle, MultipartFile pieceJointe, Long idApp, Long id) throws IOException {
        MessageDto messageDto = new MessageDto(null, libelle, pieceJointe.getBytes(), new ArrayList<>(), FilDeDiscutionDto.fromEntity(fildeDiscutionRepositoty.findById(id).get()));
        return MessageDto.fromEntity(messageRepository.save(MessageDto.toEntity(messageDto)));
    }

    @Override
    public ReponseDto repondre(String libelle, MultipartFile pieceJointe, Long id) throws IOException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        FormateurDto formateurDto = null;
        ApprenantDto apprenantDto = null;
        if (principal instanceof UserDetails){
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        if (formateurRepository.findByUsernameAndArchiveFalse(username).isPresent()) {
            formateurDto = FormateurDto.fromEntity(formateurRepository.findByUsernameAndArchiveFalse(username).get());
        }else if (apprenantRepository.findByUsernameAndArchiveFalse(username) != null){
            apprenantDto = ApprenantDto.fromEntity(apprenantRepository.findByUsernameAndArchiveFalse(username));
        }
        ReponseDto reponseDto = new ReponseDto(null, libelle, pieceJointe.getBytes(), 0, MessageDto.fromEntity(messageRepository.findById(id).get()), apprenantDto, formateurDto);
        return ReponseDto.fromEntity(
            reponseRepository.save(ReponseDto.toEntity(reponseDto))
        );
    }

    @Override
    public FilDeDiscutionDto saveFilDeDiscutionBrief(FilDeDiscutionDto filDeDiscutionDto, Long id, Long idApp) {
        BriefApprenant briefApprenant = briefApprenantRepository.findByBriefIdAndApprenantId(id, idApp).get();
        filDeDiscutionDto.setApprenant(ApprenantDto.fromEntity(apprenantRepository.findById(idApp).get()));
        filDeDiscutionDto.setBriefApprenant(BriefApprenantDto.fromEntity(briefApprenant));
        return FilDeDiscutionDto.fromEntity(
                fildeDiscutionRepositoty.save(FilDeDiscutionDto.toEntity(filDeDiscutionDto))
        );
    }
}
