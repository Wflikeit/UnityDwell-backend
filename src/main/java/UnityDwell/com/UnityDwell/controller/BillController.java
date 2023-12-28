package UnityDwell.com.UnityDwell.controller;

import UnityDwell.com.UnityDwell.dto.listResponses.BillsResponse;
import UnityDwell.com.UnityDwell.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/bills")
public class BillController {
    private final BillService billService;

    @GetMapping(value = "")
    public BillsResponse getAllBills() {
        return billService.getAllBills();
    }

    @GetMapping(value = "/{ownerId}")
    public BillsResponse getAllBillsOfOwner(@PathVariable("ownerId") UUID ownerId){
        return billService.getAllBillsOfOwner(ownerId);
    }

    @DeleteMapping(value = "/{billId}")
    public void deleteBill(@PathVariable("billId") UUID billId){
        billService.deleteBill(billId);
    }
}
