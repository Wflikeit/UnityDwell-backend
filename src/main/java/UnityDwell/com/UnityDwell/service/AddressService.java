package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.AddressResponse;
import UnityDwell.com.UnityDwell.dto.mapper.AddressDTOMapper;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.Address;
import UnityDwell.com.UnityDwell.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressDTOMapper addressDTOMapper;

    @Transactional(readOnly = true)
    public AddressResponse getAddressByHousingAssociationId(UUID id) {

        Address address = addressRepository.findAddressById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String
                        .format("Address with id %s not found", id)));

        return addressDTOMapper.mapTo(address);
    }
}
