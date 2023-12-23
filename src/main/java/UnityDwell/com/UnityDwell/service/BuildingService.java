package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.AddressResponse;
import UnityDwell.com.UnityDwell.dto.listResponses.FlatsResponse;
import UnityDwell.com.UnityDwell.repository.BuildingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BuildingService {
    private final BuildingsRepository buildingsRepository;

    public FlatsResponse getFlatsInBuilding(UUID buildingId) {
        buildingsRepository.
    }
}
