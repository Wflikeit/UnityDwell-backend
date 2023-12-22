package UnityDwell.com.UnityDwell.dto.mapper;

import UnityDwell.com.UnityDwell.dto.AddressResponse;
import UnityDwell.com.UnityDwell.model.Address;
import org.springframework.stereotype.Component;

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
}
