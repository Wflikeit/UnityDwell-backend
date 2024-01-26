package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.listResponses.AddressesResponse;
import UnityDwell.com.UnityDwell.dto.mapper.AddressDTOMapper;
import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdateAddressRequest;
import UnityDwell.com.UnityDwell.dto.response.AddressResponse;
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

    @Transactional
    public AddressResponse addNewAddress(CreateOrUpdateAddressRequest request) {
        Address address = addressDTOMapper.map(request);
        addressRepository.save(address);
        return addressDTOMapper.mapTo(address);
    }

    @Transactional
    public void deleteAddress(UUID addressId) {
        addressRepository.findAddressById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException(String
                        .format("Address with id %s not found", addressId)));
        addressRepository.delete(addressId);
    }

    @Transactional(readOnly = true)
    public AddressesResponse getAddresses() {
        return AddressesResponse.builder().addresses(addressRepository.getAllAddresses().stream().map(address ->
                AddressResponse.builder()
                        .id(address.getId())
                        .city(address.getCity())
                        .street(address.getStreet())
                        .postalCode(address.getPostalCode())
                        .numberOfBuilding(address.getNumberOfBuilding())
                        .build()).toList()).build();
    }
}
