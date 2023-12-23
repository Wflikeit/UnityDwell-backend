package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.mapper.PublicationDTOMapper;
import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdatePublicationRequest;
import UnityDwell.com.UnityDwell.dto.response.PublicationResponse;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.HousingAssociation;
import UnityDwell.com.UnityDwell.model.Publication;
import UnityDwell.com.UnityDwell.repository.HousingAssociationRepository;
import UnityDwell.com.UnityDwell.repository.PublicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PublicationService {
    private final PublicationRepository publicationRepository;
    private final HousingAssociationRepository housingAssociationRepository;
    private final PublicationDTOMapper publicationDTOMapper;

    @Transactional
    public PublicationResponse addNewPublication(CreateOrUpdatePublicationRequest request,
                                                 UUID housingAssociationId) {

        HousingAssociation housingAssociation = housingAssociationRepository
                .findHousingAssociationById(housingAssociationId)
                .orElseThrow(() -> new ResourceNotFoundException(String
                        .format("HousingAssociation with id %s not found", housingAssociationId)));

        Publication publication = publicationDTOMapper.map(request, housingAssociation);

        publicationRepository.save(publication);

        return publicationDTOMapper.mapTo(publication);
    }
}
