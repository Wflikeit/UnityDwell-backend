package UnityDwell.com.UnityDwell.controller;

import UnityDwell.com.UnityDwell.dto.listResponses.BillsResponse;
import UnityDwell.com.UnityDwell.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/bills")
public class BillController {
    private final BillService billService;

    @GetMapping(value = "")
    public BillsResponse getAllBills() {
        return BillsResponse.builder()
                .bills(billService.getAllBills())
                .build();
    }
}
