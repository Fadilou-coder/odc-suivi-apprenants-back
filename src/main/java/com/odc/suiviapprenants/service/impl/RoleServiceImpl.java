package com.odc.suiviapprenants.service.impl;

import com.odc.suiviapprenants.dto.AdminDto;
import com.odc.suiviapprenants.dto.RoleDto;
import com.odc.suiviapprenants.exception.EntityNotFoundException;
import com.odc.suiviapprenants.exception.ErrorCodes;
import com.odc.suiviapprenants.exception.InvalidEntityException;
import com.odc.suiviapprenants.model.Admin;
import com.odc.suiviapprenants.model.Role;
import com.odc.suiviapprenants.repository.AdminRepository;
import com.odc.suiviapprenants.repository.RoleRepository;
import com.odc.suiviapprenants.service.RoleService;
import com.odc.suiviapprenants.validator.RoleValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    RoleRepository rolerepository;
    AdminRepository adminRepository;

    @Override
    public RoleDto save(RoleDto roleDto) {
        List<String> errors = RoleValidator.validate(roleDto);
        if (!errors.isEmpty()) {
            log.error("Role is not valid {}", roleDto);
            throw new InvalidEntityException("Le role n'est pas valide", ErrorCodes.ROLE_NOT_VALID, errors);
        }

        return RoleDto.fromEntity(
                rolerepository.save(
                        RoleDto.toEntity(roleDto)
                )
        );
    }

    @Override
    public List<RoleDto> findAll() {
        return rolerepository.findAllByArchiveFalse().stream()
                .map(RoleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDto findById(Long id) {
        if (id == null) {
            log.error("Role ID is null");
            return null;
        }

        return rolerepository.findById(id).map(RoleDto::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun role avec l'ID = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.ROLE_NOT_FOUND)
        );
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("role ID is null");
        }

        Role role = rolerepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun role avec l'ID = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.ROLE_NOT_FOUND));
        role.setArchive(true);
        List<Admin> admins = adminRepository.findAllByRoleId(id);
        if (!admins.isEmpty()){
            admins.forEach(admin -> {
                admin.setArchive(true);
            });
        }

        rolerepository.flush();
        adminRepository.flush();
    }

    @Override
    public List<AdminDto> findAdminsByRole(Long id) {
        if (id == null) {
            log.error("role ID is null");
        }


        rolerepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun role avec l'ID = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.ROLE_NOT_FOUND));
        return adminRepository.findAllByRoleId(id).stream()
                .map(AdminDto::fromEntity)
                .collect(Collectors.toList());
    }

}
