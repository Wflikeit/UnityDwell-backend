package UnityDwell.com.UnityDwell.dto.listResponses;

import UnityDwell.com.UnityDwell.model.HousingAssociation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Builder
public class HousingAssociationsResponse {
    private List<HousingAssociation> housingAssociations;
}
