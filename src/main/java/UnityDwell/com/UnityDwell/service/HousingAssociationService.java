package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.listResponses.EmployeesResponse;
import UnityDwell.com.UnityDwell.dto.mapper.EmployeeDTOMapper;
import UnityDwell.com.UnityDwell.dto.response.HousingAssociationResponse;
import UnityDwell.com.UnityDwell.dto.response.PublicationResponse;
import UnityDwell.com.UnityDwell.dto.listResponses.BuildingsResponse;
import UnityDwell.com.UnityDwell.dto.listResponses.PublicationsResponse;
import UnityDwell.com.UnityDwell.dto.mapper.BuildingDTOMapper;
import UnityDwell.com.UnityDwell.dto.mapper.HousingAssociationDTOMapper;
import UnityDwell.com.UnityDwell.dto.mapper.PublicationDTOMapper;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.Building;
import UnityDwell.com.UnityDwell.model.HousingAssociation;
import UnityDwell.com.UnityDwell.model.users.Employee;
import UnityDwell.com.UnityDwell.repository.BuildingsRepository;
import UnityDwell.com.UnityDwell.repository.EmployeeRepository;
import UnityDwell.com.UnityDwell.repository.HousingAssociationRepository;
import UnityDwell.com.UnityDwell.repository.PublicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HousingAssociationService {
    private final HousingAssociationRepository housingAssociationRepository;
    private final BuildingsRepository buildingsRepository;

    private final HousingAssociationDTOMapper housingAssociationDTOMapper;
    private final PublicationRepository publicationRepository;
    private final PublicationDTOMapper publicationDTOMapper;
    private final BuildingDTOMapper buildingDTOMapper;
    private final EmployeeDTOMapper employeeDTOMapper;
    private final EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public HousingAssociationResponse getHousingAssociationById(UUID housingAssociationId) {

        HousingAssociation housingAssociation = housingAssociationRepository
                .findHousingAssociationById(housingAssociationId)
                .orElseThrow(() -> new ResourceNotFoundException(String
                        .format("HousingAssociation with id %s not found", housingAssociationId)));

        return housingAssociationDTOMapper.mapTo(housingAssociation);
    }

    @Transactional(readOnly = true)
    public PublicationsResponse getPublicationsByHousingAssociationId(UUID housingAssociationId) {

        housingAssociationRepository.findHousingAssociationById(housingAssociationId)
                .orElseThrow(() -> new ResourceNotFoundException(String
                        .format("HousingAssociation with id %s not found", housingAssociationId)));

        List<PublicationResponse> publications = publicationRepository
                .getAllPublicationsFromHousingAssociation(housingAssociationId).stream()
                .map(publicationDTOMapper::mapTo)
                .toList();

        return PublicationsResponse.builder().publications(publications).build();
    }

    @Transactional(readOnly = true)
    public BuildingsResponse getBuildings(UUID housingAssociationId) {

        housingAssociationRepository.findHousingAssociationById(housingAssociationId)
                .orElseThrow(() -> new ResourceNotFoundException(String
                        .format("HousingAssociation with id %s not found", housingAssociationId)));

        List<Building> buildingList = buildingsRepository
                .getBuildingsInHousingAssociation(housingAssociationId);

        return BuildingsResponse.builder()
                .buildings(buildingDTOMapper.mapToBuildingList(buildingList))
                .build();
    }

    @Transactional(readOnly = true)
    public EmployeesResponse getEmployeesOfHA(UUID housingAssociationId) {
        housingAssociationRepository.findHousingAssociationById(housingAssociationId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Housing association with id %s not found", housingAssociationId)));
        List<Employee> employees = employeeRepository.getEmployeesOfHA(housingAssociationId);
        return EmployeesResponse.builder()
                .employees(employeeDTOMapper.mapToEmployeeList(employees))
                .build();
    }
}
