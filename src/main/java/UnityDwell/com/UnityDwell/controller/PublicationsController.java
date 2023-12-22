package UnityDwell.com.UnityDwell.controller;

import UnityDwell.com.UnityDwell.dto.listResponses.PublicationsResponse;
import UnityDwell.com.UnityDwell.service.PublicationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/publications")
public class PublicationsController {
    private final PublicationsService publicationsService;

    //    temporary solution later we need to decode the publicationId in JWT Token
    @GetMapping("/{publicationId}")
    public PublicationsResponse getPublicationsFromHousingAssociation(@PathVariable("publicationId") UUID publicationId) {
        return publicationsService.getPublicationsByHousingAssociationId(publicationId);
    }

}