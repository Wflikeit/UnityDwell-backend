package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.listResponses.OwnersOfFlatsResponse;
import UnityDwell.com.UnityDwell.dto.mapper.FlatDTOMapper;
import UnityDwell.com.UnityDwell.dto.mapper.OwnerOfFlatDTOMapper;
import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdateFlatRequest;
import UnityDwell.com.UnityDwell.dto.response.FlatResponse;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.Building;
import UnityDwell.com.UnityDwell.model.Flat;
import UnityDwell.com.UnityDwell.model.OwnerOfFlat;
import UnityDwell.com.UnityDwell.repository.BuildingsRepository;
import UnityDwell.com.UnityDwell.repository.FlatRepository;
import UnityDwell.com.UnityDwell.repository.OwnerOfFlatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlatService {
    private final OwnerOfFlatRepository ownerOfFlatRepository;
    private final OwnerOfFlatDTOMapper ownerOfFlatDTOMapper;
    private final FlatRepository flatRepository;
    private final FlatDTOMapper flatDTOMapper;
    private final BuildingsRepository buildingsRepository;

    @Transactional(readOnly = true)
    public OwnersOfFlatsResponse getAllOwnersOfAFlat(UUID flatId) {
        flatRepository.findFlatById(flatId).orElseThrow(() -> new ResourceNotFoundException(String
                .format("Flat with id %s not found", flatId)));
        List<OwnerOfFlat> owners = ownerOfFlatRepository.findAllOwnersOfFlat(flatId);
        return OwnersOfFlatsResponse.builder()
                .owners(ownerOfFlatDTOMapper.mapToOwnersOfFlatsList(owners))
                .build();
    }

    @Transactional
    public FlatResponse addNewFlat(CreateOrUpdateFlatRequest request) {
        Building building = buildingsRepository.getBuildingById(request.getBuildingId()).orElseThrow(() -> new ResourceNotFoundException(String
                .format("Building with id %s not found", request.getBuildingId())));
        Flat flat = flatDTOMapper.map(request, building);
        flatRepository.save(flat);
        return flatDTOMapper.mapTo(flat);
    }

    @Transactional
    public void deleteFlat(UUID flatId) {
        flatRepository.findFlatById(flatId).orElseThrow(() -> new ResourceNotFoundException(String
                .format("Flat with id %s not found", flatId)));
        flatRepository.delete(flatId);
    }

    @Transactional
    public FlatResponse updateFlat(CreateOrUpdateFlatRequest request, UUID flatId) {
        Flat flat = flatRepository.findFlatById(flatId).orElseThrow(() -> new ResourceNotFoundException(String
                .format("Flat with id %s not found", flatId)));
        flat.setNumberOfFlat(request.getNumberOfFlat());
        flat.setSpace(request.getSpace());
        flat.setNumberOfRooms(request.getNumberOfRooms());
        flat.setDateOfLastGasControl(request.getDateOfLastGasControl());
        Building building = buildingsRepository
                .getBuildingById(request.getBuildingId()).orElseThrow(() -> new ResourceNotFoundException(String
                .format("Building with id %s not found", request.getBuildingId())));
        flat.setBuilding(building);

        flatRepository.update(flat);
        return flatDTOMapper.mapTo(flat);
    }
}
