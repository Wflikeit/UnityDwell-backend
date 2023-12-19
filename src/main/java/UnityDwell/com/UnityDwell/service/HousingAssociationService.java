package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.HousingAssociationResponse;
import UnityDwell.com.UnityDwell.dto.mapper.HousingAssociationDTOMapper;
import UnityDwell.com.UnityDwell.model.Address;
import UnityDwell.com.UnityDwell.model.HousingAssociation;
import UnityDwell.com.UnityDwell.repository.AddressRepository;
import UnityDwell.com.UnityDwell.repository.HousingAssociationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HousingAssociationService {
    private final HousingAssociationRepository housingAssociationRepository;
    private final AddressRepository addressRepository;
    private final HousingAssociationDTOMapper housingAssociationDTOMapper;

    public HousingAssociationResponse getHousingAssociation(UUID housingAssociationId) {

//        Optional<Address> address = Optional.of(addressRepository.findAddressById(UUID.fromString("1bce57d4-9a68-4f0d-a39b-ff3fec8fed44")).orElseThrow());
        Optional<HousingAssociation> list = Optional.of(housingAssociationRepository.getHousingAssociation(housingAssociationId).orElseThrow());
        return housingAssociationDTOMapper.mapTo(list.get());
    }
}
