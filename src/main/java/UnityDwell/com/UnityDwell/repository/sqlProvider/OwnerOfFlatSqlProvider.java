package UnityDwell.com.UnityDwell.repository.sqlProvider;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.UUID;

public class OwnerOfFlatSqlProvider implements ProviderMethodResolver {
    public static String findOwnerOfFlatById(UUID flatOwnerId) {
        return new SQL()
                .SELECT("w.NR_MIESZKANCA", "w.PESEL", "w.NIP_FIRMY", "w.NR_TELEFONU")
                .FROM("C##MACIEK.WLASCICIELE_MIESZKAN w")
                .WHERE("w.NR_MIESZKANCA = #{flatOwnerId}")
                .toString();
    }


    public static String findAllOwnersOfFlat(UUID flatId) {
        return new SQL()
                .SELECT("wlasc.NR_MIESZKANCA", "wlasc.PESEL", "wlasc.NIP_FIRMY", "wlasc.NR_TELEFONU")
                .FROM("C##MACIEK.MIESZKANIA mieszkania")
                .INNER_JOIN("C##MACIEK.MIESZKANIA_WLASCICIELI mieszkaniaw ON mieszkaniaw.ID_MIESZKANIA = #{flatId}")
                .INNER_JOIN("C##MACIEK.WLASCICIELE_MIESZKAN wlasc ON wlasc.NR_MIESZKANCA = mieszkaniaw.NR_MIESZKANCA")
                .INNER_JOIN("C##MACIEK.MIESZKANCY m ON m.NR_MIESZKANCA = wlasc.NR_MIESZKANCA")
                .toString();

    }
}
