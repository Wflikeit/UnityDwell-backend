package UnityDwell.com.UnityDwell.dto.response;

import UnityDwell.com.UnityDwell.model.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
public class HousingAssociationResponse {
    private UUID id;
    private String name;
    private LocalDate dateOfEstablishment;
    private String nip;
    private Address address;
}
