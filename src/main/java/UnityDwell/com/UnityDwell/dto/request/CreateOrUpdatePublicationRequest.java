package UnityDwell.com.UnityDwell.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CreateOrUpdatePublicationRequest {
    private String title;
    private String content;
    private LocalDate dateOfPublishing;
}
