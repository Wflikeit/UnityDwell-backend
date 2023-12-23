package UnityDwell.com.UnityDwell.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
public class CreateOrUpdatePublicationRequest {
    @NotNull(message = "Title is required.")
    @Length(min = 2, max = 30, message = "Field must be between {min} and {max} characters long.")
    private String title;
    @NotNull(message = "Content is required.")
    @Length(min = 5, max = 100, message = "Field must be between {min} and {max} characters long.")
    private String content;
}
