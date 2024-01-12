package UnityDwell.com.UnityDwell.controller;

import UnityDwell.com.UnityDwell.dto.listResponses.OwnersOfFlatsResponse;
import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdateFlatRequest;
import UnityDwell.com.UnityDwell.dto.response.FlatResponse;
import UnityDwell.com.UnityDwell.service.FlatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/flat")
public class FlatController {
    private final FlatService flatService;

    @GetMapping(value = "/{flatId}/owners")
    public OwnersOfFlatsResponse getAllOwnersOfAFlat(@PathVariable("flatId") UUID flatId) {
        return flatService.getAllOwnersOfAFlat(flatId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public FlatResponse addFlat(@Validated @RequestBody CreateOrUpdateFlatRequest request) {
        return flatService.addNewFlat(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFlat(@PathVariable("id") UUID flatId) {
        flatService.deleteFlat(flatId);
    }

    @PutMapping("/{id}")
    public FlatResponse updateFlat(@Validated @RequestBody CreateOrUpdateFlatRequest request
            , @PathVariable("id") UUID id) {
        return flatService.updateFlat(request, id);
    }
}
