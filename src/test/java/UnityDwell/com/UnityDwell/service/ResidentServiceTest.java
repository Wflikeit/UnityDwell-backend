package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.mapper.ResidentDTOMapper;
import UnityDwell.com.UnityDwell.dto.response.ResidentResponse;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.Resident;
import UnityDwell.com.UnityDwell.repository.ResidentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ResidentServiceTest {
    @Mock
    ResidentRepository residentRepository;
    @Mock
    ResidentDTOMapper residentDTOMapper;
    @InjectMocks
    ResidentService residentService;

    @Test
    public void getResidentById_WhenOneExists() {
        //Arrange
        UUID id = UUID.randomUUID();
        Resident resident = Resident.builder().build();
        ResidentResponse residentResponse = ResidentResponse.builder().build();
        when(residentRepository.findResidentById(id)).thenReturn(Optional.of(resident));
        when(residentDTOMapper.mapTo(resident)).thenReturn(residentResponse);
        //Act & Assert
        assertEquals(residentService.getResidentById(id), residentResponse);
    }

    @Test
    public void getResidentById_WhenOneNotExists() {
        //Arrange
        UUID id = UUID.randomUUID();
        when(residentRepository.findResidentById(id)).thenReturn(Optional.empty());

        //Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> residentService.getResidentById(id));
    }
}
