package com.odc.suiviapprenants.service.impl;

import com.odc.suiviapprenants.dto.AdminDto;
import com.odc.suiviapprenants.dto.FormateurDto;
import com.odc.suiviapprenants.exception.EntityNotFoundException;
import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.exception.InvalidEntityException;
import com.odc.suiviapprenants.model.Document;
import com.odc.suiviapprenants.model.Formateur;
import com.odc.suiviapprenants.repository.DocumentRepository;
import com.odc.suiviapprenants.repository.FormateurRepository;
import com.odc.suiviapprenants.service.FormateurService;
import com.odc.suiviapprenants.validator.FormateurValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.odc.suiviapprenants.repository.AdminRepository;
import com.odc.suiviapprenants.repository.PromoRepository;

@Service
@Slf4j
@AllArgsConstructor
public class FormateurImpl implements FormateurService {
    FormateurRepository formateurRepository;
    DocumentRepository documentRepository;
    PromoRepository promoRepository;
    AdminRepository adminRepository;
    @Override
    public FormateurDto save(FormateurDto formateurDto) {
        List<String> errors = FormateurValidator.validate(formateurDto);
        if (!errors.isEmpty()) {
            log.error("formateur is not valid {}", formateurDto);
            throw new InvalidEntityException("Le Formateur n'est pas valide", ErrorCodes.FORMATEUR_NOT_VALID, errors);
        }

        return FormateurDto.fromEntity(
                formateurRepository.save(
                        FormateurDto.toEntity(formateurDto)
                )
        );
    }

    @Override
    public FormateurDto put(Long id, String username, String email,
                            String prenom, String nom, String telephone,
                            String adresse,String cni, MultipartFile avatar,
                            String dateNaissance, List<String> libelle, List<MultipartFile> justificatif) throws IOException {
        Formateur formateur = formateurRepository.findByIdAndArchiveFalse(id).orElseThrow(()->
            new EntityNotFoundException(
                    "Aucun formateur avec l'ID = " + id + " ne se trouve dans la BDD",
                    ErrorCodes.FORMATEUR_NOT_FOUND)
        );
        List<Document> documentList = new ArrayList<>();
        formateur.setUsername(username);
        formateur.setPrenom(prenom);
        formateur.setNom(nom);
        formateur.setEmail(email);
        formateur.setAdresse(adresse);
        formateur.setCni(cni);
        formateur.setNumeroTelephone(telephone);
        formateur.setDateNaissance(LocalDate.parse(dateNaissance));
        formateur.setAvatar(avatar.getBytes());
            for (int i=0;i < libelle.toArray().length;i++){
                Document document = new Document();
                document.setLibelle(libelle.get(i));
                document.setJustificatif(justificatif.get(i).getBytes());
                document.setFormateur(formateur);
                documentRepository.save(document);
              //  documentList.add(document);
            }
          //  formateur.setDocuments(documentList);

        formateurRepository.flush();
        return FormateurDto.fromEntity(formateur);
    }

    @Override
    public List<FormateurDto> getAll() {
        return formateurRepository.findAllByArchiveFalse().stream()
                .map(FormateurDto::fromEntity)
                .collect(Collectors.toList());
    }
}
