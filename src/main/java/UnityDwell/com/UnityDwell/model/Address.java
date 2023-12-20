package UnityDwell.com.UnityDwell.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Address {
    private String city;
    private String Street;
    private int numberOfBuilding;
    private String PostalCode;
}
