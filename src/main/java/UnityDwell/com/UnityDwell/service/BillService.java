package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.listResponses.BillTitlesResponse;
import UnityDwell.com.UnityDwell.dto.listResponses.BillsResponse;
import UnityDwell.com.UnityDwell.dto.mapper.BillDTOMapper;
import UnityDwell.com.UnityDwell.dto.mapper.BillTitleDTOMapper;
import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdateBillRequest;
import UnityDwell.com.UnityDwell.dto.response.BillResponse;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.Bill;
import UnityDwell.com.UnityDwell.model.BillTitle;
import UnityDwell.com.UnityDwell.model.HousingAssociation;
import UnityDwell.com.UnityDwell.model.users.OwnerOfFlat;
import UnityDwell.com.UnityDwell.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BillService {
    private final BillRepository billRepository;
    private final BillTitlesRepository billTitlesRepository;
    private final BillDTOMapper billDTOMapper;
    private final BillTitleDTOMapper billTitleDTOMapper;
    private final OwnerOfFlatRepository ownerOfFlatRepository;
    private final HousingAssociationRepository housingAssociationRepository;
    private final BillTitleRepository billTitleRepository;

    @Transactional(readOnly = true)
    public BillsResponse getAllBillsOfOwner(UUID ownerId) {
        ownerOfFlatRepository.findOwnerOfFlatById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException(String
                        .format("Owner with id %s not found", ownerId)));
        List<Bill> bills = billRepository.getAllOwnersBills(ownerId);
        return BillsResponse.builder().bills(billDTOMapper.mapToBillList(bills)).build();
    }

    @Transactional(readOnly = true)
    public BillsResponse getAllBillsOfHousingAssociation(UUID housingAssociationId) {
        housingAssociationRepository.findHousingAssociationById(housingAssociationId)
                .orElseThrow(() -> new ResourceNotFoundException(String
                        .format("Housing association with id %s not found", housingAssociationId)));
        List<Bill> bills = billRepository.getAllHousingAssociationBills(housingAssociationId);
        return BillsResponse.builder().bills(billDTOMapper.mapToBillList(bills)).build();
    }

    @Transactional
    public void deleteBill(UUID billId) {
        billRepository.findBillById(billId).orElseThrow(() -> new ResourceNotFoundException(String
                .format("Bill with id %s not found", billId)));
        billRepository.delete(billId);
    }

    @Transactional
    public BillResponse addBill(CreateOrUpdateBillRequest request) {
        OwnerOfFlat owner = ownerOfFlatRepository.findOwnerOfFlatByPhoneNumber(request.getFlatOwnerPhoneNumber())
                .orElseThrow(() -> new ResourceNotFoundException(String
                        .format("Owner with id %s not found", request.getFlatOwnerPhoneNumber())));
        HousingAssociation housingAssociation = housingAssociationRepository
                .findHousingAssociationById(request.getHousingAssociationId()).orElseThrow(() -> new ResourceNotFoundException(String
                        .format("Housing association with id %s not found", request.getHousingAssociationId())));
        BillTitle billTitle = billTitleRepository.findBillTitleByTitle(request.getTitle())
                .orElseThrow(() -> new ResourceNotFoundException("Bill title not found"));

        Bill bill = billDTOMapper.map(request, billTitle, housingAssociation, owner);
        billRepository.save(bill);
        return billDTOMapper.mapTo(bill);
    }

    @Transactional
    public BillResponse updateBill(CreateOrUpdateBillRequest request, UUID billId) {
        Bill bill = billRepository.findBillById(billId).orElseThrow(() -> new ResourceNotFoundException(String
                .format("Bill with id %s not found", billId)));
        OwnerOfFlat owner = ownerOfFlatRepository.findOwnerOfFlatByPhoneNumber(request.getFlatOwnerPhoneNumber())
                .orElseThrow(() -> new ResourceNotFoundException(String
                        .format("Owner with id %s not found", request.getFlatOwnerPhoneNumber())));
        HousingAssociation housingAssociation = housingAssociationRepository
                .findHousingAssociationById(request.getHousingAssociationId()).orElseThrow(() -> new ResourceNotFoundException(String
                        .format("Housing association with id %s not found", request.getHousingAssociationId())));
        BillTitle billTitle = billTitleRepository.findBillTitleByTitle(request.getTitle()).orElseThrow(() -> new ResourceNotFoundException(
                "Bill title with not found"));
        bill.setAmount(request.getAmount());
        bill.setDateOfPublishing(request.getDateOfPublishing());
        bill.setFlatOwner(owner);
        bill.setHousingAssociation(housingAssociation);
        bill.setBillTitle(billTitle);
        billRepository.update(bill);
        return billDTOMapper.mapTo(bill);
    }

    @Transactional
    public BillTitlesResponse getAllBillTitles() {

        List<BillTitle> billsTitles = billTitlesRepository.getAllBillTitles();
        return BillTitlesResponse.builder()
                .titles(billTitleDTOMapper.mapToBillTitleList(billsTitles))
                .build();

    }
}
