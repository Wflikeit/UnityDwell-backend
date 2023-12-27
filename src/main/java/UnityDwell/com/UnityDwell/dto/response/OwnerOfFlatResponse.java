package UnityDwell.com.UnityDwell.dto.response;

import UnityDwell.com.UnityDwell.model.Resident;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class OwnerOfFlatResponse {
    private UUID id;
    private String pesel;
    private String nip;
    private String phoneNumber;
    private Resident resident;
}
