package UnityDwell.com.UnityDwell.repository.sqlProvider;

import UnityDwell.com.UnityDwell.model.Bill;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.UUID;

public class BillSqlProvider implements  ProviderMethodResolver{
    public static String getAllBills() {
        return new SQL()
                .SELECT("r.ID_RACHUNKU", "r.DATA_WYSTAWIENIA", "r.KWOTA", "r.ID_TYTULU_RACHUNKU", "r.ID_SPOLDZIELNI", "r.NR_MIESZKANCA")
                .INNER_JOIN("C##MACIEK.TYTULY_RACHUNKU t ON r.ID_TYTULU_RACHUNKU = t.ID_TYTULU_RACHUNKU")
                .FROM("C##MACIEK.RACHUNKI r")
                .toString();
    }
    public String getAllOwnersBills(UUID ownerId) {
        return new SQL()
                .SELECT("r.ID_RACHUNKU, r.DATA_WYSTAWIENIA, r.KWOTA, r.ID_TYTULU_RACHUNKU, r.ID_SPOLDZIELNI, R.NR_MIESZKANCA")
                .INNER_JOIN("C##MACIEK.TYTULY_RACHUNKU t ON r.ID_TYTULU_RACHUNKU = t.ID_TYTULU_RACHUNKU")
                .FROM("C##MACIEK.RACHUNKI r")
                .WHERE("r.NR_MIESZKANCA = #{ownerId}")
                .toString();
    }
    public String getAllHousingAssociationBills(UUID housingAssociationId) {
        return new SQL()
                .SELECT("r.ID_RACHUNKU, r.DATA_WYSTAWIENIA, r.KWOTA, r.ID_TYTULU_RACHUNKU, r.ID_SPOLDZIELNI, R.NR_MIESZKANCA")
                .INNER_JOIN("C##MACIEK.TYTULY_RACHUNKU t ON r.ID_TYTULU_RACHUNKU = t.ID_TYTULU_RACHUNKU")
                .FROM("C##MACIEK.RACHUNKI r")
                .WHERE("r.ID_SPOLDZIELNI = #{housingAssociationId}")
                .toString();
    }
    public String delete(UUID billId){
        return new SQL()
                .DELETE_FROM("C##MACIEK.RACHUNKI")
                .WHERE("ID_RACHUNKU = #{billId}")
                .toString();
    }
    public String findBillById(UUID billId){
        return new SQL()
                .SELECT("r.ID_RACHUNKU, r.DATA_WYSTAWIENIA, r.KWOTA, r.ID_TYTULU_RACHUNKU, r.ID_SPOLDZIELNI, R.NR_MIESZKANCA")
                .INNER_JOIN("C##MACIEK.TYTULY_RACHUNKU t ON r.ID_TYTULU_RACHUNKU = t.ID_TYTULU_RACHUNKU")
                .FROM("C##MACIEK.RACHUNKI r")
                .WHERE("r.ID_RACHUNKU = #{billId}")
                .toString();
    }
    public String save(Bill bill) {
        return new SQL()
                .INSERT_INTO("C##MACIEK.RACHUNKI")
                .VALUES("ID_RACHUNKU", "#{id}")
                .VALUES("DATA_WYSTAWIENIA", "#{dateOfPublishing}")
                .VALUES("KWOTA", "#{amount}")
                .VALUES("ID_TYTULU_RACHUNKU", "#{billTitle.id}")
                .VALUES("ID_SPOLDZIELNI", "#{housingAssociation.id}")
                .VALUES("NR_MIESZKANCA", "#{flatOwner.id}")
                .toString();
    }
    public String update(Bill bill) {
        return new SQL()
                .UPDATE("C##MACIEK.RACHUNKI")
                .SET("DATA_WYSTAWIENIA = #{dateOfPublishing}")
                .SET("KWOTA = #{amount}")
                .SET("ID_TYTULU_RACHUNKU = #{billTitle.id}")
                .SET("ID_SPOLDZIELNI = #{housingAssociation.id}")
                .SET("NR_MIESZKANCA = #{flatOwner.id}")
                .WHERE("ID_RACHUNKU = #{id}")
                .toString();
    }
}
