package UnityDwell.com.UnityDwell.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddressResponse {
    private String city;
    private String Street;
    private int numberOfBuilding;
    private String PostalCode;
}
