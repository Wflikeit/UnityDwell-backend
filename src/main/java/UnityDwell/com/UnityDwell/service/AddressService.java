package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.AddressResponse;
import UnityDwell.com.UnityDwell.model.Address;
import UnityDwell.com.UnityDwell.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressResponse getAddressRepositoryById(UUID id) {
        Address address = addressRepository.findAddressById(id).orElseThrow();

        return addressRepository.findAddressById(id).orElseThrow();
    }
}
