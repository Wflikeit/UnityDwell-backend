package UnityDwell.com.UnityDwell.dto.mapper;

import UnityDwell.com.UnityDwell.dto.FlatResponse;
import UnityDwell.com.UnityDwell.model.Flat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class FlatDTOMapper {

    public List<FlatResponse> mapToFlatsList(List<Flat> flats) {

        return flats == null ? new ArrayList<>() : flats.stream().filter(Objects::nonNull)
                .map(flat -> FlatResponse.builder()
                        .id(flat.getId())
                        .numberOfFlat(flat.getNumberOfFlat())
                        .space(flat.getSpace())
                        .amountOfRooms(flat.getAmountOfRooms())
                        .dateOfLastGasControl(flat.getDateOfLastGasControl())
                        .building(flat.getBuilding())
                        .build())
                .toList();
    }
}
