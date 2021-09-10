package com.odc.suiviapprenants.service.impl;

import com.odc.suiviapprenants.dto.AdminDto;
import com.odc.suiviapprenants.dto.RoleDto;
import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.exception.InvalidEntityException;
import com.odc.suiviapprenants.model.Role;
import com.odc.suiviapprenants.model.UserOwner;
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
        List<String> errors = UserValidator.validate(adminDto);

        if(userAlreadyExists(adminDto.getEmail())) {
            throw new InvalidEntityException("Un autre utilisateur avec le meme email existe deja", ErrorCodes.ADMIN_ALREADY_IN_USE,
                    Collections.singletonList("Un autre utilisateur avec le meme email existe deja dans la BDD"));
        }
        if(userAlreadyExistsUsername(adminDto.getUsername())) {
            throw new InvalidEntityException("Un autre utilisateur avec le meme nom d'utilisateur existe deja", ErrorCodes.ADMIN_ALREADY_IN_USE,
                    Collections.singletonList("Un autre utilisateur avec le meme nom d'utilisateur existe deja dans la BDD"));
        }

        if(userAlreadyExistsPhone(adminDto.getNumeroTelephone())) {
            throw new InvalidEntityException("Un autre utilisateur avec le meme numero de telephone existe deja", ErrorCodes.ADMIN_ALREADY_IN_USE,
                    Collections.singletonList("Un autre utilisateur avec le meme numero de telephone existe deja dans la BDD"));
        }

        if (!errors.isEmpty()) {
            log.error("Admin is not valid {}", adminDto);
            throw new InvalidEntityException("L'admin n'est pas valide", ErrorCodes.ADMIN_NOT_VALID, errors);
        }

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
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public AdminDto put(Long id) {
        return null;
    }
    private boolean userAlreadyExists(String email) {
        Optional<UserOwner> user = userRepository.findByEmail(email);
        return user.isPresent();
    }
    private boolean userAlreadyExistsUsername(String username) {
        Optional<UserOwner> user = userRepository.findByUsername(username);
        return user.isPresent();
    }
    private boolean userAlreadyExistsPhone(String phone) {
        Optional<UserOwner> user = userRepository.findByNumeroTelephone(phone);
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
        } catch (IOException ignore) {
            ignore.printStackTrace();
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }
}
