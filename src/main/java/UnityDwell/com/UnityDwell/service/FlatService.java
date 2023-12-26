package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.listResponses.OwnersOfFlatsResponse;
import UnityDwell.com.UnityDwell.dto.mapper.OwnerOfFlatDTOMapper;
import UnityDwell.com.UnityDwell.model.OwnerOfFlat;
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

    @Transactional(readOnly = true)
    public OwnersOfFlatsResponse getAllOwnersOfAFlat(UUID flatId){
        List<OwnerOfFlat> owners = ownerOfFlatRepository.findAllOwnersOfFlat(flatId);
        return OwnersOfFlatsResponse.builder()
                .owners(ownerOfFlatDTOMapper.mapToOwnersOfFlatsList(owners))
                .build();
    }
}
