package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.listResponses.BillsResponse;
import UnityDwell.com.UnityDwell.dto.mapper.BillDTOMapper;
import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdateBillRequest;
import UnityDwell.com.UnityDwell.dto.response.BillResponse;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.Bill;
import UnityDwell.com.UnityDwell.model.BillTitle;
import UnityDwell.com.UnityDwell.model.HousingAssociation;
import UnityDwell.com.UnityDwell.model.OwnerOfFlat;
import UnityDwell.com.UnityDwell.repository.BillRepository;
import UnityDwell.com.UnityDwell.repository.BillTitleRepository;
import UnityDwell.com.UnityDwell.repository.HousingAssociationRepository;
import UnityDwell.com.UnityDwell.repository.OwnerOfFlatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BillService {
    private final BillRepository billRepository;
    private final BillDTOMapper billDTOMapper;
    private final OwnerOfFlatRepository ownerOfFlatRepository;
    private final HousingAssociationRepository housingAssociationRepository;
    private final BillTitleRepository billTitleRepository;

    @Transactional(readOnly = true)
    public BillsResponse getAllBills() {
        List<Bill> bills = billRepository.getAllBills();
        return BillsResponse.builder().bills(billDTOMapper.mapToBillList(bills)).build();
    }

    @Transactional(readOnly = true)
    public BillsResponse getAllBillsOfOwner(UUID ownerId){
        ownerOfFlatRepository.findOwnerOfFlatById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException(String
                    .format("Owner with id %s not found", ownerId)));
        List<Bill> bills = billRepository.getAllOwnersBills(ownerId);
        return BillsResponse.builder().bills(billDTOMapper.mapToBillList(bills)).build();
    }

    @Transactional
    public void deleteBill(UUID billId){
        billRepository.findBillById(billId).orElseThrow(() -> new ResourceNotFoundException(String
                .format("Bill with id %s not found", billId)));
        billRepository.delete(billId);
    }

    @Transactional
    public BillResponse addBill(CreateOrUpdateBillRequest request, UUID billTitleId, UUID housingAssociationId,
                                UUID ownerId){

        OwnerOfFlat owner = ownerOfFlatRepository.findOwnerOfFlatById(ownerId).orElseThrow(() -> new ResourceNotFoundException(String
                .format("Owner with id %s not found", ownerId)));

        HousingAssociation housingAssociation = housingAssociationRepository
                .findHousingAssociationById(housingAssociationId).orElseThrow(() -> new ResourceNotFoundException(String
                        .format("Housing association with id %s not found", housingAssociationId)));

        BillTitle billTitle = billTitleRepository.findBillTitleById(billTitleId).orElseThrow(() -> new ResourceNotFoundException(String
                .format("Bill title with id %s not found", billTitleId)));


        Bill bill = billDTOMapper.map(request, billTitle, housingAssociation, owner);
        billRepository.save(bill);
        return billDTOMapper.mapTo(bill);
    }
}
