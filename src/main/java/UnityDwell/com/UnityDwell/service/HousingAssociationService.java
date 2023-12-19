package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.model.HousingAssociation;
import UnityDwell.com.UnityDwell.repository.HousingAssociationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HousingAssociationService {
    private final HousingAssociationRepository housingAssociationRepository;

    public List<HousingAssociation> getHousingAssociation(UUID housingAssociationId) {
        return housingAssociationRepository.getHousingAssociation(housingAssociationId);
    }
}
