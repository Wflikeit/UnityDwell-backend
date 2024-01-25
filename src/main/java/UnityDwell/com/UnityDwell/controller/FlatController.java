package UnityDwell.com.UnityDwell.controller;

import UnityDwell.com.UnityDwell.dto.listResponses.OwnersOfFlatsResponse;
import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdateFlatRequest;
import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdateResidentRequest;
import UnityDwell.com.UnityDwell.dto.response.FlatResponse;
import UnityDwell.com.UnityDwell.dto.response.ResidentResponse;
import UnityDwell.com.UnityDwell.service.FlatService;
import UnityDwell.com.UnityDwell.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/flat")
public class FlatController {
    private final FlatService flatService;
    private final ResidentService residentService;

    @GetMapping(value = "/{flatId}/owners")
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    public OwnersOfFlatsResponse getAllOwnersOfAFlat(@PathVariable("flatId") UUID flatId) {
        return flatService.getAllOwnersOfAFlat(flatId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Secured({"ROLE_ADMIN"})
    public FlatResponse addFlat(@Validated @RequestBody CreateOrUpdateFlatRequest request) {
        return flatService.addNewFlat(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Secured({"ROLE_ADMIN"})
    public void deleteFlat(@PathVariable("id") UUID flatId) {
        flatService.deleteFlat(flatId);
    }

    @PutMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public FlatResponse updateFlat(@Validated @RequestBody CreateOrUpdateFlatRequest request,
                                   @PathVariable("id") UUID id) {
        return flatService.updateFlat(request, id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE", "ROLE_FLAT_OWNER"})
    public ResidentResponse addResidentToFlat(@Validated @RequestBody CreateOrUpdateResidentRequest request,
                                              @PathVariable("id") UUID id) {
        return residentService.addResident(request, id);
    }
}
