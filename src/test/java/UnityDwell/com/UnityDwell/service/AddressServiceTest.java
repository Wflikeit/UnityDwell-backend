package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.response.AddressResponse;
import UnityDwell.com.UnityDwell.dto.mapper.AddressDTOMapper;
import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdateAddressRequest;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.Address;
import UnityDwell.com.UnityDwell.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {
    @Mock
    AddressRepository addressRepository;
    @Mock
    AddressDTOMapper addressDTOMapper;
    @InjectMocks
    AddressService addressService;

    @Test
    public void testGetAddressById_WhenOneExists() {

        // Arrange
        UUID id = UUID.randomUUID();
        Address address = Address.builder().build();
        AddressResponse addressResponse = AddressResponse.builder().build();
        when(addressRepository.findAddressById(id)).thenReturn(Optional.of(address));
        when(addressDTOMapper.mapTo(address)).thenReturn(addressResponse);

        // Act & Assert
        assertEquals(addressResponse, addressService.getAddressByHousingAssociationId(id));
    }

    @Test
    public void testGetAddressById_WhenOneDoesNotExist() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(addressRepository.findAddressById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class,
                () -> addressService.getAddressByHousingAssociationId(id));
    }

    @Test
    public void testAddNewAddress_WhenOneDoesNotExist() {
        // Arrange
        CreateOrUpdateAddressRequest request = CreateOrUpdateAddressRequest.builder().build();
        Address address = Address.builder().build();
        AddressResponse expectedResponse = AddressResponse.builder().build();

        when(addressDTOMapper.map(request)).thenReturn(address);
        doNothing().when(addressRepository).save(address);
        when(addressDTOMapper.mapTo(address)).thenReturn(expectedResponse);

        // Act
        AddressResponse actualResponse = addressService.addNewAddress(request);

        // Assert
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);

        verify(addressRepository, times(1)).save(address);
    }

    @Test
    public void testDeleteAddress_WhenOneDoesNotExist() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(addressRepository.findAddressById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class,
                () -> addressService.deleteAddress(id));
    }

    @Test
    public void testDeleteAddress_WhenOneExist() {
        // Arrange
        UUID id = UUID.randomUUID();
        Address address = Address.builder().build();

        when(addressRepository.findAddressById(id)).thenReturn(Optional.of(address));
        doNothing().when(addressRepository).delete(id);

        // Act
        addressService.deleteAddress(id);

        // Assert
        verify(addressRepository, times(1)).findAddressById(id);
        verify(addressRepository, times(1)).delete(id);
    }
}
