package UnityDwell.com.UnityDwell.controller;

import UnityDwell.com.UnityDwell.dto.response.ResidentResponse;
import UnityDwell.com.UnityDwell.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/resident")
public class ResidentController {
    private final ResidentService residentService;

    @GetMapping(value = "/{id}")
    public ResidentResponse getResident(@PathVariable("id") UUID residentId){
        return residentService.getResidentById(residentId);
    }
}
