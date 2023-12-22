package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.AddressResponse;
import UnityDwell.com.UnityDwell.dto.mapper.AddressDTOMapper;
import UnityDwell.com.UnityDwell.model.Address;
import UnityDwell.com.UnityDwell.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {
    @Mock
    AddressRepository addressRepository;
    @Mock
    AddressDTOMapper addressDTOMapper;
    @InjectMocks
    AddressService addressService;

    @Test
    public void getAddressById_WhenOneExists() {

        // Arrange
        UUID id = UUID.randomUUID();
        Address address = Address.builder().build();
        AddressResponse addressResponse = AddressResponse.builder().build();
        when(addressRepository.findAddressById(id)).thenReturn(Optional.of(address));
        when(addressDTOMapper.mapTo(address)).thenReturn(addressResponse);

        // Act & Assert
        assertEquals(addressResponse, addressService.getAddressByHousingAssociationId(id));
    }
}
