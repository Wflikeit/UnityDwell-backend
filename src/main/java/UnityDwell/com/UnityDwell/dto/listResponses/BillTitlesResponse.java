package UnityDwell.com.UnityDwell.dto.listResponses;

import UnityDwell.com.UnityDwell.dto.response.BillTitleResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BillTitlesResponse {
    List<BillTitleResponse> titles;
}
