package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.mapper.ResidentDTOMapper;
import UnityDwell.com.UnityDwell.dto.response.ResidentResponse;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.Resident;
import UnityDwell.com.UnityDwell.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResidentService {
    private final ResidentRepository residentRepository;
    private final ResidentDTOMapper residentDTOMapper;

    @Transactional(readOnly = true)
    public ResidentResponse getResidentById(UUID id){
        Resident resident = residentRepository.findResidentById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Resident with id %s not found", id)));

        return residentDTOMapper.mapTo(resident);
    }
}
