package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.PrivilegeRepository;
import eu.senla.naumovich.dto.privilege.PrivilegeDto;
import eu.senla.naumovich.entities.Privilege;
import eu.senla.naumovich.exceptions.NoRecords;
import eu.senla.naumovich.mapper.PrivilegeMapper;
import eu.senla.naumovich.services.service.PrivilegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrivilegeServiceImpl implements PrivilegeService {
        private final PrivilegeRepository privilegeRepository;
        private final PrivilegeMapper privilegeMapper;

        @Override
        public List<PrivilegeDto> getAll(int size, int page) {
                List<Privilege> privileges = privilegeRepository.getAll(size, page);
                return privilegeMapper.toDtoList(privileges);
        }

        @Override
        public PrivilegeDto getById(Long id) {

                return privilegeMapper.toDto(privilegeRepository.findById(id)
                                .orElseThrow(() -> new NoRecords("No record with such ID " + id)));

        }

        @Override
        public PrivilegeDto update(PrivilegeDto privilege) {
                return privilegeMapper.toDto(privilegeRepository.update(privilegeMapper.toEntity(privilege)));

        }

        @Override
        public PrivilegeDto create(PrivilegeDto privilege) {

                return privilegeMapper.toDto(privilegeRepository.create(privilegeMapper.toEntity(privilege)));

        }

        @Override
        public void delete(Long id) {
                privilegeRepository.deleteById(id);

        }
}
