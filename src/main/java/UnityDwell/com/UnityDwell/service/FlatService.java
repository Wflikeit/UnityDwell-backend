package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.listResponses.OwnersOfFlatsResponse;
import UnityDwell.com.UnityDwell.dto.mapper.FlatDTOMapper;
import UnityDwell.com.UnityDwell.dto.mapper.OwnerOfFlatDTOMapper;
import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdateFlatRequest;
import UnityDwell.com.UnityDwell.dto.response.FlatResponse;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.Address;
import UnityDwell.com.UnityDwell.model.Building;
import UnityDwell.com.UnityDwell.model.Flat;
import UnityDwell.com.UnityDwell.model.OwnerOfFlat;
import UnityDwell.com.UnityDwell.repository.AddressRepository;
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
    private final AddressRepository addressRepository;

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
    public FlatResponse addNewFlat(CreateOrUpdateFlatRequest request, UUID buildingId, UUID addressId) {
        Building building = buildingsRepository.getBuildingById(buildingId).orElseThrow(() -> new ResourceNotFoundException(String
                .format("Building with id %s not found", buildingId)));
        Address address = addressRepository.findAddressById(addressId).orElseThrow(() -> new ResourceNotFoundException(String
                .format("Address with id %s not found", addressId)));
        Flat flat = flatDTOMapper.map(request, building, address);
        flatRepository.save(flat);
        return flatDTOMapper.mapTo(flat);
    }

    @Transactional
    public void deleteFlat(UUID flatId) {
        flatRepository.findFlatById(flatId).orElseThrow(() -> new ResourceNotFoundException(String
                .format("Flat with id %s not found", flatId)));
        flatRepository.delete(flatId);
    }
}
