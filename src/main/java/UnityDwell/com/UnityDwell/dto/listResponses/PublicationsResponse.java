package UnityDwell.com.UnityDwell.dto.listResponses;

import UnityDwell.com.UnityDwell.dto.PublicationResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PublicationsResponse {
    List<PublicationResponse> publications;

}
