package UnityDwell.com.UnityDwell.model;

import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class HousingAssociation {
    private String name;
    private LocalDate dateOfEstablishment;
    private String nip;
    private Address address;
}
