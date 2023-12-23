package UnityDwell.com.UnityDwell.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
public class PublicationResponse {
    private UUID id;
    private String content;
    private String title;
    private LocalDate dateOfPublishing;
    private UUID idOfHousingAssociation;

}
