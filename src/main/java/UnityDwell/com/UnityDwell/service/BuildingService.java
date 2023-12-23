package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.listResponses.FlatsResponse;
import UnityDwell.com.UnityDwell.dto.mapper.FlatDTOMapper;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.Flat;
import UnityDwell.com.UnityDwell.repository.BuildingsRepository;
import UnityDwell.com.UnityDwell.repository.FlatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BuildingService {
    private final FlatRepository flatRepository;
    private final BuildingsRepository buildingsRepository;
    private final FlatDTOMapper flatDTOMapper;

    @Transactional(readOnly = true)
    public FlatsResponse getFlatsInBuilding(UUID buildingId) {

        buildingsRepository.getBuildingById(buildingId)
                .orElseThrow(() -> new ResourceNotFoundException(String
                        .format("Building with id %s not found", buildingId)));


        List<Flat> flatList = flatRepository.getAllFlatsInBuilding(buildingId);
        return FlatsResponse.builder()
                .flats(flatDTOMapper.mapToFlatsList(flatList))
                .build();
    }
}
