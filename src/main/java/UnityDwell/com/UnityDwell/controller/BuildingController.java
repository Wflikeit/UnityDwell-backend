package UnityDwell.com.UnityDwell.controller;

import UnityDwell.com.UnityDwell.dto.listResponses.FlatsResponse;
import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdateBuildingRequest;
import UnityDwell.com.UnityDwell.dto.response.BuildingResponse;
import UnityDwell.com.UnityDwell.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/buildings")
public class BuildingController {
    private final BuildingService buildingService;

    @GetMapping(value = "/{buildingId}/flats")
    public FlatsResponse getFlatsInBuilding(@PathVariable("buildingId") UUID buildingId) {
        return buildingService.getFlatsInBuilding(buildingId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public BuildingResponse addBuilding(@Validated @RequestBody CreateOrUpdateBuildingRequest request) {
        return buildingService.addNewBuilding(request);
    }

    @PutMapping(value = "/{buildingId}")
    public BuildingResponse updateBuilding(@Validated @RequestBody CreateOrUpdateBuildingRequest request,
                                           @PathVariable("buildingId") UUID buildingId) {
        return buildingService.updateBuilding(request, buildingId);
    }

    @DeleteMapping(value = "/{buildingId}")
    public void delete(@PathVariable("buildingId") UUID buildingId) {
        buildingService.deleteBuilding(buildingId);
    }

}