package UnityDwell.com.UnityDwell.dto.listResponses;

import UnityDwell.com.UnityDwell.dto.FlatResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class FlatsResponse {
    List<FlatResponse> flats;

}
