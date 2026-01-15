package chemistry;

public interface ChemistryProvider {
    /**
     * Find CAS RN(s) by a free-text chemical name/identifier.
     */
    String search(String query);
    
    /**
     * Get chemical details/properties by CAS RN.
     * Returns raw provider response for now (next steps: DTO).
     */
    String detail(String casRn);
}
