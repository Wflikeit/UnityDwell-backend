package UnityDwell.com.UnityDwell.dto.mapper;

import UnityDwell.com.UnityDwell.dto.response.OwnerOfFlatResponse;
import UnityDwell.com.UnityDwell.model.users.OwnerOfFlat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class OwnerOfFlatDTOMapper {
    public List<OwnerOfFlatResponse> mapToOwnersOfFlatsList(List<OwnerOfFlat> owners){
        return owners == null ? new ArrayList<>() : owners.stream().filter(Objects::nonNull)
                .map(ownerOfFlat -> OwnerOfFlatResponse.builder()
                        .id(ownerOfFlat.getId())
                        .pesel(ownerOfFlat.getPesel())
                        .nip(ownerOfFlat.getNip())
                        .phoneNumber(ownerOfFlat.getPhoneNumber())
                        .resident(ownerOfFlat.getResident())
                        .build())
                .toList();
    }
}
