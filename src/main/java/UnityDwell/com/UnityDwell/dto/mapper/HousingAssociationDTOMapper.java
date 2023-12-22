package UnityDwell.com.UnityDwell.dto.mapper;

import UnityDwell.com.UnityDwell.dto.HousingAssociationResponse;
import UnityDwell.com.UnityDwell.model.HousingAssociation;
import org.springframework.stereotype.Component;

@Component
public class HousingAssociationDTOMapper {

    public HousingAssociationResponse mapTo(HousingAssociation housingAssociation) {

        return HousingAssociationResponse.builder()
                .id(housingAssociation.getId())
                .name(housingAssociation.getName())
                .dateOfEstablishment(housingAssociation.getDateOfEstablishment())
                .nip(housingAssociation.getNip())
                .address(housingAssociation.getAddress())
                .build();
    }
}
