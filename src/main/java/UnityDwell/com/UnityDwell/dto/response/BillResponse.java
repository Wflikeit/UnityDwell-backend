package UnityDwell.com.UnityDwell.dto.response;

import UnityDwell.com.UnityDwell.model.HousingAssociation;
import UnityDwell.com.UnityDwell.model.OwnerOfFlat;
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
    private OwnerOfFlat owner;
    private HousingAssociation housingAssociation;
    private String title;
}
