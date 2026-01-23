package domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChemicalIdentity {
    private String casRn;
    private String inchiKey;
    private Long pubchemCid;
    private String displayName;
}