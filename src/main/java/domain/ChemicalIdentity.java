package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChemicalIdentity {
    private String casRn;
    private String inchiKey;
    private Long pubchemCid;
    private String displayName;
}