package UnityDwell.com.UnityDwell.model;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Bill {
    private UUID id;
    private double amount;
    private LocalDate dateOfPublishing;
    private OwnerOfFlat flatOwner;
    private Resident resident;
    private HousingAssociation housingAssociation;
}
