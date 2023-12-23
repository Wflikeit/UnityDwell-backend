package UnityDwell.com.UnityDwell.controller;

import UnityDwell.com.UnityDwell.dto.listResponses.BuildingsResponse;
import UnityDwell.com.UnityDwell.dto.listResponses.PublicationsResponse;
import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdatePublicationRequest;
import UnityDwell.com.UnityDwell.dto.response.HousingAssociationResponse;
import UnityDwell.com.UnityDwell.dto.response.PublicationResponse;
import UnityDwell.com.UnityDwell.service.HousingAssociationService;
import UnityDwell.com.UnityDwell.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/HousingAssociation")
public class HousingAssociationController {
    private final HousingAssociationService housingAssociationService;
    private final PublicationService publicationService;

    @GetMapping(value = "/{id}")
    public HousingAssociationResponse getHousingAssociation(@PathVariable("id") UUID housingAssociationId) {
        return housingAssociationService.getHousingAssociationById(housingAssociationId);
    }

    @GetMapping("/{id}/buildings")
    public BuildingsResponse getBuildingsFromHousingAssociation(@PathVariable("id") UUID housingAssociationId) {
        return housingAssociationService.getBuildings(housingAssociationId);
    }

    @GetMapping("/{id}/publications")
    public PublicationsResponse getPublicationsFromHousingAssociation(@PathVariable("id") UUID id) {
        return housingAssociationService.getPublicationsByHousingAssociationId(id);
    }

    @PostMapping("/{housingAssociationId}/publications")
    @ResponseStatus(HttpStatus.CREATED)
    public PublicationResponse addAddress(@Validated @RequestBody CreateOrUpdatePublicationRequest request,
                                          @PathVariable UUID housingAssociationId) {
        return publicationService.addNewPublication(request, housingAssociationId);
    }
}