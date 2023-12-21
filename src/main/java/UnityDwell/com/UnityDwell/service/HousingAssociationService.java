package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.HousingAssociationResponse;
import UnityDwell.com.UnityDwell.dto.mapper.HousingAssociationDTOMapper;
import UnityDwell.com.UnityDwell.model.HousingAssociation;
import UnityDwell.com.UnityDwell.repository.HousingAssociationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HousingAssociationService {
    private final HousingAssociationRepository housingAssociationRepository;
    private final HousingAssociationDTOMapper housingAssociationDTOMapper;

    @Transactional(readOnly = true)
    public HousingAssociationResponse getHousingAssociationById(UUID housingAssociationId) {

        Optional<HousingAssociation> list = Optional
                .of(housingAssociationRepository.findByIdHousingAssociation(housingAssociationId).orElseThrow());
        return housingAssociationDTOMapper.mapTo(list.get());
    }
}
