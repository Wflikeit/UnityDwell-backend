package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.PublicationResponse;
import UnityDwell.com.UnityDwell.dto.listResponses.PublicationsResponse;
import UnityDwell.com.UnityDwell.dto.mapper.PublicationDTOMapper;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.repository.HousingAssociationRepository;
import UnityDwell.com.UnityDwell.repository.PublicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PublicationsService {
    private final PublicationRepository publicationRepository;
    private final HousingAssociationRepository housingAssociationRepository;
    private final PublicationDTOMapper publicationDTOMapper;

    @Transactional(readOnly = true)
    public PublicationsResponse getPublicationsByHousingAssociationId(UUID housingAssociationId) {

        housingAssociationRepository.findByIdHousingAssociation(housingAssociationId)
                .orElseThrow(() -> new ResourceNotFoundException(String
                        .format("HousingAssociation with id %s not found", housingAssociationId)));

        List<PublicationResponse> publications = publicationRepository
                .getAllPublicationsFromHousingAssociation(housingAssociationId).stream()
                .map(publicationDTOMapper::mapTo)
                .toList();

        return PublicationsResponse.builder().publications(publications).build();
    }
}
