package UnityDwell.com.UnityDwell.dto.listResponses;

import UnityDwell.com.UnityDwell.dto.response.OwnerOfFlatResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OwnersOfFlatsResponse {
    List<OwnerOfFlatResponse> flats;

}
