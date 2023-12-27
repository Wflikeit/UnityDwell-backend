package UnityDwell.com.UnityDwell.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class ResidentResponse {
    private UUID id;
    private String name;
    private String surname;
    private UUID flatId;

}
