package UnityDwell.com.UnityDwell.model.users;

import UnityDwell.com.UnityDwell.model.Flat;
import UnityDwell.com.UnityDwell.model.Resident;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OwnerOfFlat {
    private UUID id;
    private String email;
    private String pesel;
    private String nip;
    private String phoneNumber;
    private List<Flat> flats;
    private Resident resident;
}
