package UnityDwell.com.UnityDwell.dto.listResponses;

import UnityDwell.com.UnityDwell.dto.BillResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BillsResponse {
    List<BillResponse> bills;
}
