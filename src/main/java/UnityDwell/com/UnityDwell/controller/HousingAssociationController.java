package UnityDwell.com.UnityDwell.controller;

import UnityDwell.com.UnityDwell.dto.HousingAssociationResponse;
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
    public HousingAssociationResponse getHousingAssociations(@PathVariable("id") UUID housingAssociationId) {
        return housingAssociationService.getHousingAssociation(housingAssociationId);
    }
}