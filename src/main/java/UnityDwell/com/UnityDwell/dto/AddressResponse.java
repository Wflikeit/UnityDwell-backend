package UnityDwell.com.UnityDwell.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class AddressResponse {
    private UUID id;
    private String city;
    private String street;
    private String numberOfBuilding;
    private String postalCode;
}
