package UnityDwell.com.UnityDwell.model;

import lombok.*;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Address {
    private UUID id;
    private String city;
    private String street;
    private String numberOfBuilding;
    private String postalCode;
}
