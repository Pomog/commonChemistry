package ports;

import domain.ChemicalIdentity;

import java.util.Optional;

public interface IdentityProvider {
    Optional<ChemicalIdentity> resolveByName(String name);
    
    Optional<ChemicalIdentity> resolveByCasRn(String casRn);
}
