package UnityDwell.com.UnityDwell.controller;

import UnityDwell.com.UnityDwell.dto.response.HousingAssociationResponse;
import UnityDwell.com.UnityDwell.dto.listResponses.BuildingsResponse;
import UnityDwell.com.UnityDwell.dto.listResponses.PublicationsResponse;
import UnityDwell.com.UnityDwell.service.HousingAssociationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/HousingAssociation")
public class HousingAssociationController {
    private final HousingAssociationService housingAssociationService;

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
}