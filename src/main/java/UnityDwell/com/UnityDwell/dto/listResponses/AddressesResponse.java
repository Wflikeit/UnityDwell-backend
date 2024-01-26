package UnityDwell.com.UnityDwell.dto.listResponses;

import UnityDwell.com.UnityDwell.dto.response.AddressResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class AddressesResponse {
    List<AddressResponse> addresses;
}
