package eu.senla.naumovich.services.api;

import eu.senla.naumovich.dao.repository.PrivilegeRepository;
import eu.senla.naumovich.dto.PrivilegeDto;
import eu.senla.naumovich.entities.Privilege;
import eu.senla.naumovich.services.mapper.PrivilegeMapper;
import eu.senla.naumovich.services.service.PrivilegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrivilegeServiceImpl implements PrivilegeService {
    private final PrivilegeRepository privilegeRepository;
    private final PrivilegeMapper privilegeMapper;

    @Override
    public List<PrivilegeDto> getAll() {
        List<Privilege> privileges = privilegeRepository.getAll();
        List<PrivilegeDto> privilegesDto = privileges.stream()
                .map(privilegeMapper::toDto)
                .collect(Collectors.toList());
        return privilegesDto;
    }

    @Override
    public PrivilegeDto getById(PrivilegeDto privilege) {
        return privilegeMapper.toDto(privilegeRepository.getById(privilegeMapper.toEntity(privilege)));
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
    public void delete(PrivilegeDto privilege) {
        privilegeRepository.delete(privilegeMapper.toEntity(privilege));
    }
}
