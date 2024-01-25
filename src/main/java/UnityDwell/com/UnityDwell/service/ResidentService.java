package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.mapper.ResidentDTOMapper;
import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdateResidentRequest;
import UnityDwell.com.UnityDwell.dto.response.ResidentResponse;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.Flat;
import UnityDwell.com.UnityDwell.model.Resident;
import UnityDwell.com.UnityDwell.repository.FlatRepository;
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
    private final FlatRepository flatRepository;

    @Transactional(readOnly = true)
    public ResidentResponse getResidentById(UUID id) {
        Resident resident = residentRepository.findResidentById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Resident with id %s not found", id)));

        return residentDTOMapper.mapTo(resident);
    }

    @Transactional
    public ResidentResponse addResident(CreateOrUpdateResidentRequest request, UUID flatId) {
        Flat flat = flatRepository.findFlatById(flatId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Flat with id %s not found", flatId)));
        Resident resident = residentDTOMapper.map(request, flat);
        residentRepository.save(resident);
        return residentDTOMapper.mapTo(resident);
    }
}
