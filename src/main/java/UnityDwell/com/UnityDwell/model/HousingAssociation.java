package UnityDwell.com.UnityDwell.model;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class HousingAssociation {
    private UUID id;
    private String name;
    private LocalDate dateOfEstablishment;
    private String nip;
    private Address address;
}
