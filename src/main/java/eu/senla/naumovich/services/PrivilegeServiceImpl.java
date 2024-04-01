package eu.senla.naumovich.services;

import eu.senla.naumovich.dao.repository.PrivilegeRepository;
import eu.senla.naumovich.dto.PrivilegeDto;
import eu.senla.naumovich.entities.Privilege;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrivilegeServiceImpl implements PrivilegeService {
    private final PrivilegeRepository privilegeRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PrivilegeDto> getAll() {
        List<PrivilegeDto> privilegesDto = new ArrayList<>();
        List<Privilege> privileges = privilegeRepository.getAll();
        for(Privilege privilege : privileges){
            privilegesDto.add(modelMapper.map(privilege, PrivilegeDto.class));
        }
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
