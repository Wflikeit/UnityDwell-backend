package UnityDwell.com.UnityDwell.dto.mapper;

import UnityDwell.com.UnityDwell.dto.BillResponse;
import UnityDwell.com.UnityDwell.model.Bill;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class BillDTOMapper {

    public List<BillResponse> mapToBillList(List<Bill> bills) {

        return bills == null ? new ArrayList<>() : bills.stream().filter(Objects::nonNull)
                .map(bill -> BillResponse.builder()
                        .id(bill.getId())
                        .amount(bill.getAmount())
                        .DateOfPublishing(bill.getDateOfPublishing())
                        .idOfFlatOwner(bill.getFlatOwner().getId())
                        .idOfHousingAssociation(bill.getHousingAssociation().getId())
                        .build())
                .toList();
    }
}