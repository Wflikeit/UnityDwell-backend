package UnityDwell.com.UnityDwell.model;

import UnityDwell.com.UnityDwell.model.users.OwnerOfFlat;
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
    private LocalDate DateOfPublishing;
    private OwnerOfFlat ofFlatOwner;
    private Resident resident;
    private HousingAssociation housingAssociation;
}
