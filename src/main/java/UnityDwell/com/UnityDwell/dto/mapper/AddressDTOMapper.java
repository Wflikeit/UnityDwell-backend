package UnityDwell.com.UnityDwell.dto.mapper;

import UnityDwell.com.UnityDwell.dto.AddressResponse;
import UnityDwell.com.UnityDwell.dto.requests.CreateOrUpdateAddressRequest;
import UnityDwell.com.UnityDwell.model.Address;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AddressDTOMapper {

    public AddressResponse mapTo(Address address) {

        return AddressResponse.builder()
                .id(address.getId())
                .city(address.getCity())
                .street(address.getStreet())
                .numberOfBuilding(address.getNumberOfBuilding())
                .postalCode(address.getPostalCode())
                .build();
    }
    public Address map(CreateOrUpdateAddressRequest addressRequest) {

        return Address.builder()
                .id(UUID.randomUUID())
                .city(addressRequest.getCity())
                .street(addressRequest.getStreet())
                .numberOfBuilding(addressRequest.getNumberOfBuilding())
                .postalCode(addressRequest.getPostalCode())
                .build();
    }
}
