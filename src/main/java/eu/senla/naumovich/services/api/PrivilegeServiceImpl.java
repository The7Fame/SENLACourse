package eu.senla.naumovich.services.api;

import eu.senla.naumovich.dao.repository.PrivilegeRepository;
import eu.senla.naumovich.dto.PrivilegeDto;
import eu.senla.naumovich.entities.Privilege;
import eu.senla.naumovich.services.service.PrivilegeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrivilegeServiceImpl implements PrivilegeService {
    private final PrivilegeRepository privilegeRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PrivilegeDto> getAll() {
        List<Privilege> privileges = privilegeRepository.getAll();
        List<PrivilegeDto> privilegesDto = privileges.stream()
                .map(privilege -> modelMapper.map(privilege, PrivilegeDto.class))
                .collect(Collectors.toList());
        return privilegesDto;
    }

    @Override
    public PrivilegeDto getById(PrivilegeDto privilege) {
        return modelMapper.map(privilegeRepository.getById(modelMapper.map(privilege, Privilege.class)), PrivilegeDto.class);
    }

    @Override
    public PrivilegeDto update(PrivilegeDto privilege) {
        return modelMapper.map(privilegeRepository.update(modelMapper.map(privilege, Privilege.class)), PrivilegeDto.class);
    }

    @Override
    public PrivilegeDto create(PrivilegeDto privilege) {
        return modelMapper.map(privilegeRepository.create(modelMapper.map(privilege, Privilege.class)), PrivilegeDto.class);
    }

    @Override
    public void delete(PrivilegeDto privilege) {
        privilegeRepository.delete(modelMapper.map(privilege, Privilege.class));
    }
}
