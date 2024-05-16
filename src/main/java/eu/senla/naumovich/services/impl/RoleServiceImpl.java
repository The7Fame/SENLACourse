package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.RoleRepository;
import eu.senla.naumovich.dto.role.RoleDto;
import eu.senla.naumovich.entities.Role;
import eu.senla.naumovich.exceptions.NoRecords;
import eu.senla.naumovich.mapper.RoleMapper;
import eu.senla.naumovich.services.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
        private final RoleRepository roleRepository;
        private final RoleMapper roleMapper;

        @Override
        public List<RoleDto> getAll(int size, int page) {
                List<Role> roles = roleRepository.getAll(size, page);
                return roleMapper.toDtoList(roles);
        }

        @Override
        public RoleDto getById(Long id) {
                return roleMapper.toDto(roleRepository.findById(id)
                                .orElseThrow(() -> new NoRecords("No record with such ID " + id)));

        }

        @Override
        public RoleDto update(RoleDto role) {
                return roleMapper.toDto(roleRepository.update(roleMapper.toEntity(role)));

        }

        @Override
        public RoleDto create(RoleDto role) {
                return roleMapper.toDto(roleRepository.create(roleMapper.toEntity(role)));

        }

        @Override
        public void delete(Long id) {
                roleRepository.deleteById(id);
        }
}
