package UnityDwell.com.UnityDwell.dto.mapper;

import UnityDwell.com.UnityDwell.dto.response.ResidentResponse;
import UnityDwell.com.UnityDwell.model.Resident;
import org.springframework.stereotype.Component;

@Component
public class ResidentDTOMapper {
    public ResidentResponse mapTo(Resident resident){
        return ResidentResponse.builder().id(resident.getId()).name(resident.getName())
                    .surname(resident.getSurname()).flatId(resident.getFlat().getId()).build();
    }
}
