package UnityDwell.com.UnityDwell.dto.mapper;

import UnityDwell.com.UnityDwell.dto.response.BuildingResponse;
import UnityDwell.com.UnityDwell.model.Building;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
}
