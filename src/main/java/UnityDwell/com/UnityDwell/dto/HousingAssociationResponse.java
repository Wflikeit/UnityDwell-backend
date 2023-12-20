package UnityDwell.com.UnityDwell.dto;

import UnityDwell.com.UnityDwell.model.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class HousingAssociationResponse {
    private String name;
    private LocalDate dateOfEstablishment;
    private String nip;
    private Address address;
}
