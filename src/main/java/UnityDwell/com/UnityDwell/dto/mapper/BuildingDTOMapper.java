package UnityDwell.com.UnityDwell.dto.mapper;

import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdateBuildingRequest;
import UnityDwell.com.UnityDwell.dto.response.BuildingResponse;
import UnityDwell.com.UnityDwell.model.Address;
import UnityDwell.com.UnityDwell.model.Building;
import UnityDwell.com.UnityDwell.model.HousingAssociation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class BuildingDTOMapper {

    public List<BuildingResponse> mapToBuildingList(List<Building> buildings) {
        return buildings == null ? new ArrayList<>() : buildings.stream().filter(Objects::nonNull)
                .map(building -> BuildingResponse.builder()
                        .id(building.getId())
                        .address(building.getAddress())
                        .dateOfThermalModernization(building.getDateOfThermalModernization())
                        .dateOfCommissioning(building.getDateOfCommissioning())
                        .dateOfMajorRenovation(building.getDateOfMajorRenovation())
                        .numberOfFloors(building.getNumberOfFloors())
                        .intendedForLiving(building.isIntendedForLiving())
                        .idOfHousingAssociation(building.getHousingAssociation().getId())
                        .build())
                .toList();
    }

    public Building map(CreateOrUpdateBuildingRequest request, HousingAssociation housingAssociation,
                        Address address) {
        return Building.builder()
                .id(UUID.randomUUID())
                .address(address)
                .dateOfThermalModernization(request.getDateOfThermalModernization())
                .dateOfCommissioning(request.getDateOfCommissioning())
                .dateOfMajorRenovation(request.getDateOfMajorRenovation())
                .numberOfFloors(request.getNumberOfFloors())
                .intendedForLiving(request.isIntendedForLiving())
                .housingAssociation(housingAssociation)
                .build();
    }

    public BuildingResponse mapTo(Building building) {
        return BuildingResponse.builder()
                .id(building.getId())
                .dateOfThermalModernization(building.getDateOfThermalModernization())
                .dateOfCommissioning(building.getDateOfCommissioning())
                .dateOfMajorRenovation(building.getDateOfMajorRenovation())
                .numberOfFloors(building.getNumberOfFloors())
                .intendedForLiving(building.isIntendedForLiving())
                .address(building.getAddress())
                .idOfHousingAssociation(building.getHousingAssociation().getId())
                .build();
    }
}
