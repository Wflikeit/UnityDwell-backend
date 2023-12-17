package UnityDwell.com.UnityDwell.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
public class BillResponse {
    private UUID id;
    private double amount;
    private LocalDate DateOfPublishing;
    private UUID idOfFlatOwner;
    private UUID idOfResident;
    private UUID idOfHousingAssociation;
}
