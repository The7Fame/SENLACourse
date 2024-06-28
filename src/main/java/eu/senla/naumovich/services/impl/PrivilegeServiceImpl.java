package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dto.privilege.PrivilegeDto;
import eu.senla.naumovich.entities.Privilege;
import eu.senla.naumovich.exceptions.NoRecordException;
import eu.senla.naumovich.exceptions.RecordExistsException;
import eu.senla.naumovich.mapper.PrivilegeMapper;
import eu.senla.naumovich.repositories.PrivilegeRepository;
import eu.senla.naumovich.services.service.PrivilegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrivilegeServiceImpl implements PrivilegeService {
        private final PrivilegeRepository privilegeRepository;
        private final PrivilegeMapper privilegeMapper;

        @Override
        public List<PrivilegeDto> getAll(int page, int size, String sort) {
                Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort));
                Page<Privilege> privilegePage = privilegeRepository.findAll(pageable);
                return privilegeMapper.toDtoList(privilegePage.getContent());
        }

        @Override
        public PrivilegeDto getById(Long id) {
                return privilegeMapper.toDto(privilegeRepository.findById(id)
                                .orElseThrow(() -> new NoRecordException("No record with such ID " + id)));

        }

        @Override
        public PrivilegeDto update(PrivilegeDto privilege) {
                return privilegeMapper.toDto(privilegeRepository.save(privilegeMapper.toEntity(privilege)));

        }

        @Override
        public PrivilegeDto create(PrivilegeDto privilege) {
                try {
                        return privilegeMapper.toDto(privilegeRepository.save(privilegeMapper.toEntity(privilege)));
                }catch (Exception e){
                        throw new RecordExistsException("Record is exists");
                }

        }

        @Override
        public void delete(Long id) {
                privilegeRepository.deleteById(id);

        }
}
