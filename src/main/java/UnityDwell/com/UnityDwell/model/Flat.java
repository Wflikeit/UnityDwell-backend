package UnityDwell.com.UnityDwell.model;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Flat {
    private UUID id;
    private int numberOfFlat;
    private double space;
    private int amountOfRooms;
    private LocalDate dateOfLastGasControl;
    private Building building;
    private Address address;
}
