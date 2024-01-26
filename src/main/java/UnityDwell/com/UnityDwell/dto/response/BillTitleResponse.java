package UnityDwell.com.UnityDwell.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class BillTitleResponse {
    private UUID id;
    private String title;

}
