package com.odc.suiviapprenants.service.impl;

import com.odc.suiviapprenants.dto.AdminDto;
import com.odc.suiviapprenants.dto.RoleDto;
import com.odc.suiviapprenants.exception.EntityNotFoundException;
import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.exception.InvalidEntityException;
import com.odc.suiviapprenants.model.Admin;
import com.odc.suiviapprenants.model.Role;
import com.odc.suiviapprenants.model.AppUser;
import com.odc.suiviapprenants.repository.AdminRepository;
import com.odc.suiviapprenants.repository.RoleRepository;
import com.odc.suiviapprenants.repository.UserRepository;
import com.odc.suiviapprenants.service.AdminService;
import com.odc.suiviapprenants.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.Deflater;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository repository;

    @Override
    public AdminDto save(
            String username ,
            String email,
            String prenom,
            String nom,
            String telephone,
            String adresse,
            String role,
            String cni,
            MultipartFile avatar,
            String dateNaissance
    ) throws IOException {
        AdminDto adminDto = new AdminDto(
                null,
                username ,
                null,
                prenom,
                nom,
                email,
                cni,
                adresse,
                telephone,
                LocalDate.parse(dateNaissance),
                compressBytes(avatar.getBytes()),
                null
        );
        Role role1 = repository.findByLibelle(role);
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        adminDto.setPassword(encoder.encode("password"));
        adminDto.setRole(RoleDto.fromEntity(role1));
        validation(adminDto);
        return AdminDto.fromEntity(
                adminRepository.save(
                        AdminDto.toEntity(adminDto)
                )
        );
    }


    @Override
    public List<AdminDto> findAll() {
        return adminRepository.findAllByArchiveFalse().stream()
                .map(AdminDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AdminDto findById(Long id) {
        if (id == null) {
            log.error("User ID is null");
            return null;
        }

        return adminRepository.findByIdAndArchiveFalse(id).map(AdminDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun utilisateur avec l'ID = " + id + " ne se trouve dans la BDD",
                        ErrorCodes.ADMIN_NOT_FOUND)
        );
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("User ID is null");
        }

        Admin admin = adminRepository.findByIdAndArchiveFalse(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun admin avec l'ID = " + id + " ne se trouve dans la BDD",
                        ErrorCodes.ADMIN_NOT_FOUND));
        admin.setArchive(true);
        adminRepository.flush();
    }

    @Override
    public AdminDto put(Long id,
                        String username ,
                        String email,
                        String prenom,
                        String nom,
                        String telephone,
                        String adresse,
                        String role,
                        String cni,
                        MultipartFile avatar,
                        String dateNaissance) throws IOException {

        Admin admin = adminRepository.findByIdAndArchiveFalse(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun admin avec l'ID = " + id + " ne se trouve dans la BDD",
                        ErrorCodes.ADMIN_NOT_FOUND));

        admin.setUsername(username);
        admin.setEmail(email);
        admin.setPrenom(nom);
        admin.setNom(nom);
        admin.setNumeroTelephone(telephone);
        admin.setAdresse(adresse);
        admin.setRole(repository.findByLibelle(role));
        admin.setCni(cni);
        admin.setDateNaissance(LocalDate.parse(dateNaissance));
        admin.setAvatar(compressBytes(avatar.getBytes()));

        AdminDto adminDto = AdminDto.fromEntity(admin);
        validation(adminDto);

        adminRepository.flush();
        return adminDto;
    }

    private void validation(AdminDto adminDto) {
        List<String> errors = UserValidator.validate(adminDto);

        if(userAlreadyExistsUsername(adminDto.getUsername(), adminDto.getId())) {
            throw new InvalidEntityException("Un autre utilisateur avec le meme nom d'utilisateur existe deja", ErrorCodes.ADMIN_ALREADY_IN_USE,
                    Collections.singletonList("Un autre utilisateur avec le meme nom d'utilisateur existe deja dans la BDD"));
        }
        if(userAlreadyExists(adminDto.getEmail(), adminDto.getId())) {
            throw new InvalidEntityException("Un autre utilisateur avec le meme email existe deja", ErrorCodes.ADMIN_ALREADY_IN_USE,
                    Collections.singletonList("Un autre utilisateur avec le meme email existe deja dans la BDD"));
        }

        if(userAlreadyExistsPhone(adminDto.getNumeroTelephone(), adminDto.getId())) {
            throw new InvalidEntityException("Un autre utilisateur avec le meme numero de telephone existe deja", ErrorCodes.ADMIN_ALREADY_IN_USE,
                    Collections.singletonList("Un autre utilisateur avec le meme numero de telephone existe deja dans la BDD"));
        }

        if(userAlreadyExistsCni(adminDto.getCni(), adminDto.getId())) {
            throw new InvalidEntityException("Un autre utilisateur avec le meme cni existe deja", ErrorCodes.ADMIN_ALREADY_IN_USE,
                    Collections.singletonList("Un autre utilisateur avec le meme cni existe deja dans la BDD"));
        }

        if (!errors.isEmpty()) {
            log.error("Admin is not valid {}", adminDto);
            throw new InvalidEntityException("L'admin n'est pas valide", ErrorCodes.ADMIN_NOT_VALID, errors);
        }
    }


    private boolean userAlreadyExists(String email, Long id) {
        Optional<AppUser> user;
        if (id == null){
            user = userRepository.findByEmail(email);
        }else {
            user = userRepository.findByEmailAndIdNot(email, id);
        }
        return user.isPresent();
    }
    private boolean userAlreadyExistsUsername(String username, Long id) {
        Optional<AppUser> user;
        if (id == null) {
            user = userRepository.findByUsername(username);
        }else {
            user = userRepository.findByUsernameAndIdNot(username, id);
        }
        return user.isPresent();
    }
    private boolean userAlreadyExistsPhone(String phone, Long id) {
        Optional<AppUser> user;
        if (id == null) {
            user = userRepository.findByNumeroTelephone(phone);
        }else {
            user = userRepository.findByNumeroTelephoneAndIdNot(phone, id);
        }
        return user.isPresent();
    }

    private boolean userAlreadyExistsCni(String cni, Long id) {
        Optional<AppUser> user;
        if (id == null) {
            user = userRepository.findByCni(cni);
        }else {
            user = userRepository.findByCniAndIdNot(cni, id);
        }
        return user.isPresent();
    }
    public static byte[] compressBytes(byte[] data) {

        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException ignor) {
            ignor.printStackTrace();
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        if (outputStream.toByteArray().length == 8) return null;

        return outputStream.toByteArray();
    }
}
