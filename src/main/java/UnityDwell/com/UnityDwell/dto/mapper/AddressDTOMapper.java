package UnityDwell.com.UnityDwell.dto.mapper;

import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdateAddressRequest;
import UnityDwell.com.UnityDwell.dto.response.AddressResponse;
import UnityDwell.com.UnityDwell.model.Address;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    public List<AddressResponse> mapToAddressesList(List<Address> addressList) {
        return addressList == null ? new ArrayList<>() : addressList.stream().filter(Objects::nonNull)
                .map(address -> AddressResponse.builder()
                        .id(address.getId())
                        .city(address.getCity())
                        .street(address.getStreet())
                        .numberOfBuilding(address.getNumberOfBuilding())
                        .postalCode(address.getPostalCode())
                        .build())
                .toList();
    }
}
