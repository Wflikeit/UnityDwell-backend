package UnityDwell.com.UnityDwell.controller;

import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdatePublicationRequest;
import UnityDwell.com.UnityDwell.dto.response.PublicationResponse;
import UnityDwell.com.UnityDwell.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/publications")
@CrossOrigin(origins = "*")
public class PublicationController {
    private final PublicationService publicationService;
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    @DeleteMapping("/{publicationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublication(@PathVariable("publicationId") UUID publicationId) {
        publicationService.deletePublication(publicationId);
    }
    @PutMapping("/{publicationId}")
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    public PublicationResponse updatePublication(@Validated @RequestBody CreateOrUpdatePublicationRequest request,
                                                 @PathVariable("publicationId") UUID publicationId) {
        return publicationService.updatePublication(request, publicationId);
    }

}