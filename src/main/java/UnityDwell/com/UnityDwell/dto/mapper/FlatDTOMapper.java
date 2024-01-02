package UnityDwell.com.UnityDwell.dto.mapper;

import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdateFlatRequest;
import UnityDwell.com.UnityDwell.dto.response.FlatResponse;
import UnityDwell.com.UnityDwell.model.Address;
import UnityDwell.com.UnityDwell.model.Building;
import UnityDwell.com.UnityDwell.model.Flat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class FlatDTOMapper {

    public List<FlatResponse> mapToFlatsList(List<Flat> flats) {
        return flats == null ? new ArrayList<>() : flats.stream().filter(Objects::nonNull)
                .map(flat -> FlatResponse.builder()
                        .id(flat.getId())
                        .numberOfFlat(flat.getNumberOfFlat())
                        .space(flat.getSpace())
                        .numberOfRooms(flat.getNumberOfRooms())
                        .dateOfLastGasControl(flat.getDateOfLastGasControl())
                        .buildingId(flat.getBuilding().getId())
                        .build())
                .toList();
    }

    public Flat map(CreateOrUpdateFlatRequest request, Building building, Address address) {
        return Flat.builder()
                .id(UUID.randomUUID())
                .numberOfFlat(request.getNumberOfFlat())
                .space(request.getSpace())
                .numberOfRooms(request.getNumberOfRooms())
                .dateOfLastGasControl(request.getDateOfLastGasControl())
                .building(building)
                .address(address)
                .build();
    }

    public FlatResponse mapTo(Flat flat) {
        return FlatResponse.builder()
                .id(flat.getId())
                .numberOfFlat(flat.getNumberOfFlat())
                .space(flat.getSpace())
                .numberOfRooms(flat.getNumberOfRooms())
                .dateOfLastGasControl(flat.getDateOfLastGasControl())
                .buildingId(flat.getBuilding().getId())
                .address(flat.getAddress())
                .build();
    }
}
