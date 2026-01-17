package chemistry;

import domain.ChemicalIdentity;
import ports.IdentityProvider;

import java.util.Optional;

public class CasIdentityProvider implements IdentityProvider {
    @Override
    public Optional<ChemicalIdentity> resolveByName(String name) {
        return Optional.empty();
    }
    
    @Override
    public Optional<ChemicalIdentity> resolveByCasRn(String casRn) {
        return Optional.empty();
    }
}
