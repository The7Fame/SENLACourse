package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dto.role.RoleDto;
import eu.senla.naumovich.dto.role.RoleShortDto;
import eu.senla.naumovich.entities.Role;
import eu.senla.naumovich.exceptions.NoRecordException;
import eu.senla.naumovich.exceptions.RecordExistsException;
import eu.senla.naumovich.mapper.RoleMapper;
import eu.senla.naumovich.repositories.RoleRepository;
import eu.senla.naumovich.services.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
        private final RoleRepository roleRepository;
        private final RoleMapper roleMapper;

        @Override
        public List<RoleShortDto> getAll(int page, int size, String sort) {
                Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort));
                Page<Role> rolePage = roleRepository.findAll(pageable);
                return roleMapper.toDtoList(rolePage.getContent());
        }

        @Override
        public RoleDto getById(Long id) {
                return roleMapper.toDto(roleRepository.findById(id)
                                .orElseThrow(() -> new NoRecordException("No record with such ID " + id)));
        }

        @Override
        public RoleDto update(RoleDto role) {
                return roleMapper.toDto(roleRepository.save(roleMapper.toEntity(role)));
        }

        @Override
        public RoleDto create(RoleDto role) {
                try {
                        return roleMapper.toDto(roleRepository.save(roleMapper.toEntity(role)));
                }catch (Exception e){
                        throw new RecordExistsException("Record is exists");
                }
        }

        @Override
        public void delete(Long id) {
                roleRepository.deleteById(id);
        }
}
