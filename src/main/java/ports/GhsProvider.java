package ports;

import domain.HazardStatement;

import java.util.List;

public interface GhsProvider {
    List<HazardStatement> ghsByCid(long cid);
}
