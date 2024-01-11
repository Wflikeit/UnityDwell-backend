package UnityDwell.com.UnityDwell.model.users;

import UnityDwell.com.UnityDwell.model.Flat;
import UnityDwell.com.UnityDwell.model.Resident;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OwnerOfFlat extends User {
    private String pesel;
    private String nip;
    private String phoneNumber;
    private List<Flat> flats;
    private Resident resident;
}
