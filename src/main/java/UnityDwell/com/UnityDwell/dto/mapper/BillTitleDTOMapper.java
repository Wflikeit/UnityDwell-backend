package UnityDwell.com.UnityDwell.dto.mapper;

import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdateAddressRequest;
import UnityDwell.com.UnityDwell.dto.response.AddressResponse;
import UnityDwell.com.UnityDwell.dto.response.BillTitleResponse;
import UnityDwell.com.UnityDwell.model.Address;
import UnityDwell.com.UnityDwell.model.BillTitle;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class BillTitleDTOMapper {

    public List<BillTitleResponse> mapToBillTitleList(List<BillTitle> billTitles) {
        return billTitles == null ? new ArrayList<>() : billTitles.stream().filter(Objects::nonNull)
                .map(billTitle -> BillTitleResponse.builder()
                        .id(billTitle.getId())
                        .title(billTitle.getTitle())
                        .build())
                .toList();
    }
}
