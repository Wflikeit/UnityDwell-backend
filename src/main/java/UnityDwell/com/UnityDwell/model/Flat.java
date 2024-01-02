package UnityDwell.com.UnityDwell.model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
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
    private int numberOfRooms;
    private LocalDate dateOfLastGasControl;
    private Building building;
    private Address address;
    private List<OwnerOfFlat> flatOwners;
}
