package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.model.Address;
import UnityDwell.com.UnityDwell.repository.AddressRepository;
import UnityDwell.com.UnityDwell.repository.HousingAssociationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {
    @Mock
    HousingAssociationRepository housingAssociationRepository;
    @Mock
    AddressRepository addressRepository;
    @Mock
    AddressService housingAssociationDTOMapper;
    @InjectMocks
    HousingAssociationService housingAssociationService;
    @Test
    public void getAddressById_WhenOneExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        Address address = Address.builder().build();
        when(addressRepository.findAddressById(id)).thenReturn(Optional.of(address));
        // Act & Assert
        assertTrue(addressRepository.findAddressById(id).isPresent());
        assertSame(address, addressRepository.findAddressById(id).get());
    }
}
