package UnityDwell.com.UnityDwell.model;

import lombok.*;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Resident {
    private UUID id;
    private String name;
    private String surname;
    private Flat flat;
}
