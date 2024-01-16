package UnityDwell.com.UnityDwell.controller;

import UnityDwell.com.UnityDwell.dto.listResponses.FlatsResponse;
import UnityDwell.com.UnityDwell.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/buildings")
public class BuildingController {
    private final BuildingService buildingService;
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    @GetMapping(value = "/{buildingId}/flats")
    public FlatsResponse getFlatsInBuilding(@PathVariable("buildingId") UUID buildingId) {
        return buildingService.getFlatsInBuilding(buildingId);
    }
}