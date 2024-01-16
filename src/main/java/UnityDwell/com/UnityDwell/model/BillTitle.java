package UnityDwell.com.UnityDwell.model;

import lombok.*;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BillTitle {
    private UUID id;
    private String title;
}
