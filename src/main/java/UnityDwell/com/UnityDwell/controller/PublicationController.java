package UnityDwell.com.UnityDwell.controller;

import UnityDwell.com.UnityDwell.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/publications")
public class PublicationController {
    private final PublicationService publicationService;

    @DeleteMapping("/{publicationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublication(@PathVariable("publicationId") UUID publicationId) {
        publicationService.deletePublication(publicationId);
    }
}