package UnityDwell.com.UnityDwell.model;

import lombok.*;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OwnerOfFlat {
    private UUID id;
    private String pesel;
    private String nip;
    private String phoneNumber;
    private Flat flat;
}
