package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.listResponses.FlatsResponse;
import UnityDwell.com.UnityDwell.dto.mapper.BuildingDTOMapper;
import UnityDwell.com.UnityDwell.dto.mapper.FlatDTOMapper;
import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdateBuildingRequest;
import UnityDwell.com.UnityDwell.dto.response.BuildingResponse;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.*;
import UnityDwell.com.UnityDwell.repository.AddressRepository;
import UnityDwell.com.UnityDwell.repository.BuildingsRepository;
import UnityDwell.com.UnityDwell.repository.FlatRepository;
import UnityDwell.com.UnityDwell.repository.HousingAssociationRepository;
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
    private final BuildingDTOMapper buildingDTOMapper;
    private final FlatDTOMapper flatDTOMapper;
    private final AddressRepository addressRepository;
    private final HousingAssociationRepository housingAssociationRepository;

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

    @Transactional
    public BuildingResponse addNewBuilding(CreateOrUpdateBuildingRequest request) {
        HousingAssociation housingAssociation = housingAssociationRepository
                .findHousingAssociationById(request.getHousingAssociationId())
                .orElseThrow(() -> new ResourceNotFoundException(String
                        .format("Housing association with id %s not found", request.getHousingAssociationId())));
        Address address = addressRepository
                .findAddressById(request.getAddressId())
                .orElseThrow(() -> new ResourceNotFoundException(String
                        .format("Address with id %s not found", request.getAddressId())));
        Building building = buildingDTOMapper.map(request, housingAssociation, address);
        buildingsRepository.save(building);
        return buildingDTOMapper.mapTo(building);
    }

    @Transactional
    public void deleteBuilding(UUID buildingId) {
        buildingsRepository.getBuildingById(buildingId)
                .orElseThrow(() -> new ResourceNotFoundException(String
                        .format("Building with id %s not found", buildingId)));
        buildingsRepository.delete(buildingId);
    }

    @Transactional
    public BuildingResponse updateBuilding(CreateOrUpdateBuildingRequest request, UUID buildingId) {
        Building building = buildingsRepository.getBuildingById(buildingId)
                .orElseThrow(() -> new ResourceNotFoundException(String
                        .format("Building with id %s not found", buildingId)));
        HousingAssociation housingAssociation = housingAssociationRepository
                .findHousingAssociationById(request.getHousingAssociationId())
                .orElseThrow(() -> new ResourceNotFoundException(String
                        .format("Housing association with id %s not found", request.getHousingAssociationId())));
        Address address = addressRepository
                .findAddressById(request.getAddressId())
                .orElseThrow(() -> new ResourceNotFoundException(String
                        .format("Address with id %s not found", request.getAddressId())));
        building.setAddress(address);
        building.setDateOfCommissioning(request.getDateOfCommissioning());
        building.setDateOfMajorRenovation(request.getDateOfMajorRenovation());
        building.setDateOfThermalModernization(request.getDateOfThermalModernization());
        building.setHousingAssociation(housingAssociation);
        building.setNumberOfFloors(request.getNumberOfFloors());
        building.setIntendedForLiving(request.getIntendedForLiving());
        buildingsRepository.update(building);
        return buildingDTOMapper.mapTo(building);
    }
}
