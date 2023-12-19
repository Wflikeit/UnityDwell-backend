package UnityDwell.com.UnityDwell.service;

import UnityDwell.com.UnityDwell.dto.BillResponse;
import UnityDwell.com.UnityDwell.dto.mapper.BillDTOMapper;
import UnityDwell.com.UnityDwell.model.Bill;
import UnityDwell.com.UnityDwell.repository.BillRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BillService {
    private final BillRepository billRepository;
    private final BillDTOMapper billDTOMapper;

    @Transactional(readOnly = true)
    public List<BillResponse> getAllBills() {
        List<Bill> bills = billRepository.getAllBills();
        return billDTOMapper.mapToBillList(bills);
    }
}
