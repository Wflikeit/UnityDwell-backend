package UnityDwell.com.UnityDwell.dto;

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
    private LocalDate DateOfLastGasControl;
    private UUID idOfBuilding;
    private String city;
    private String Street;
    private int numberOfBuilding;
    private String PostalCode;
}
