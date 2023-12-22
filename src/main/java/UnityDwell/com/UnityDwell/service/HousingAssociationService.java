package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.BuildingResponse;
import UnityDwell.com.UnityDwell.dto.HousingAssociationResponse;
import UnityDwell.com.UnityDwell.dto.listResponses.BuildingsResponse;
import UnityDwell.com.UnityDwell.dto.mapper.BuildingDTOMapper;
import UnityDwell.com.UnityDwell.dto.mapper.HousingAssociationDTOMapper;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.Building;
import UnityDwell.com.UnityDwell.model.HousingAssociation;
import UnityDwell.com.UnityDwell.repository.BuildingsRepository;
import UnityDwell.com.UnityDwell.repository.HousingAssociationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HousingAssociationService {
    private final HousingAssociationRepository housingAssociationRepository;
    private final BuildingsRepository buildingsRepository;

    private final HousingAssociationDTOMapper housingAssociationDTOMapper;
    private final BuildingDTOMapper buildingDTOMapper;

    @Transactional(readOnly = true)
    public HousingAssociationResponse getHousingAssociationById(UUID housingAssociationId) {

        HousingAssociation housingAssociation = housingAssociationRepository
                .findByIdHousingAssociation(housingAssociationId)
                .orElseThrow(() -> new ResourceNotFoundException(String
                        .format("HousingAssociation with id %s not found", housingAssociationId)));

        return housingAssociationDTOMapper.mapTo(housingAssociation);
    }

    @Transactional(readOnly = true)
    public BuildingsResponse getBuildings(UUID housingAssociationId) {

        housingAssociationRepository.findByIdHousingAssociation(housingAssociationId)
                .orElseThrow(() -> new ResourceNotFoundException(String
                        .format("HousingAssociation with id %s not found", housingAssociationId)));

        List<Building> buildingList = buildingsRepository
                .getBuildingsInHousingAssociation(housingAssociationId);

        List<BuildingResponse> buildingResponseList = buildingList
                .stream()
                .map(buildingDTOMapper::mapTo)
                .toList();

        return BuildingsResponse.builder().buildings(buildingResponseList).build();
    }
}
