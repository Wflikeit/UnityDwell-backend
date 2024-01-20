package UnityDwell.com.UnityDwell.dto.mapper;

import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdateResidentRequest;
import UnityDwell.com.UnityDwell.dto.response.ResidentResponse;
import UnityDwell.com.UnityDwell.model.Flat;
import UnityDwell.com.UnityDwell.model.Resident;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ResidentDTOMapper {
    public ResidentResponse mapTo(Resident resident){
        return ResidentResponse.builder().id(resident.getId()).name(resident.getName())
                    .surname(resident.getSurname()).flatId(resident.getFlat().getId()).build();
    }

    public Resident map(CreateOrUpdateResidentRequest request, Flat flat) {
        return Resident.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .surname(request.getSurname())
                .flat(flat)
                .build();
    }
}
