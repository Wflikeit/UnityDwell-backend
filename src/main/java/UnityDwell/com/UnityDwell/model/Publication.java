package UnityDwell.com.UnityDwell.model;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Publication {
    private UUID id;
    private String title;
    private String content;
    private LocalDate dateOfPublishing;
    private HousingAssociation housingAssociation;
}
