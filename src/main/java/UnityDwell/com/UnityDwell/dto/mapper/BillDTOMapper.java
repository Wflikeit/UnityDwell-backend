package UnityDwell.com.UnityDwell.dto.mapper;

import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdateBillRequest;
import UnityDwell.com.UnityDwell.dto.response.BillResponse;
import UnityDwell.com.UnityDwell.model.Bill;
import UnityDwell.com.UnityDwell.model.BillTitle;
import UnityDwell.com.UnityDwell.model.HousingAssociation;
import UnityDwell.com.UnityDwell.model.OwnerOfFlat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class BillDTOMapper {

    public List<BillResponse> mapToBillList(List<Bill> bills) {

        return bills == null ? new ArrayList<>() : bills.stream().filter(Objects::nonNull)
                .map(bill -> BillResponse.builder()
                        .id(bill.getId())
                        .amount(bill.getAmount())
                        .DateOfPublishing(bill.getDateOfPublishing())
                        .owner(bill.getFlatOwner())
                        .housingAssociation(bill.getHousingAssociation())
                        .title(bill.getBillTitle().getTitle())
                        .build())
                .toList();
    }

    public Bill map(CreateOrUpdateBillRequest billRequest, BillTitle billTitle,
                    HousingAssociation housingAssociation, OwnerOfFlat owner){
        return Bill.builder()
                .id(UUID.randomUUID())
                .amount(billRequest.getAmount())
                .dateOfPublishing(billRequest.getDateOfPublishing())
                .flatOwner(owner)
                .housingAssociation(housingAssociation)
                .billTitle(billTitle)
                .build();
    }

    public BillResponse mapTo(Bill bill){
        return BillResponse.builder()
                .id(bill.getId())
                .amount(bill.getAmount())
                .DateOfPublishing(bill.getDateOfPublishing())
                .owner(bill.getFlatOwner())
                .housingAssociation(bill.getHousingAssociation())
                .title(bill.getBillTitle().getTitle())
                .build();
    }
}