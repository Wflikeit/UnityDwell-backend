package UnityDwell.com.UnityDwell.dto.response;

import UnityDwell.com.UnityDwell.model.Address;
import UnityDwell.com.UnityDwell.model.Building;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
public class FlatResponse {
    private UUID id;
    private int numberOfFlat;
    private double space;
    private int amountOfRooms;
    private LocalDate dateOfLastGasControl;
    private Building building;
}
