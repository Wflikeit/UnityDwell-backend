package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.listResponses.BillsResponse;
import UnityDwell.com.UnityDwell.dto.mapper.BillDTOMapper;
import UnityDwell.com.UnityDwell.error.ResourceNotFoundException;
import UnityDwell.com.UnityDwell.model.Bill;
import UnityDwell.com.UnityDwell.repository.BillRepository;
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

    @Transactional(readOnly = true)
    public BillsResponse getAllBills() {
        List<Bill> bills = billRepository.getAllBills();
        return BillsResponse.builder().bills(billDTOMapper.mapToBillList(bills)).build();
    }

    @Transactional(readOnly = true)
    public BillsResponse getAllBillsOfOwner(UUID ownerId){
        ownerOfFlatRepository.findOwnerById(ownerId)
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
}
