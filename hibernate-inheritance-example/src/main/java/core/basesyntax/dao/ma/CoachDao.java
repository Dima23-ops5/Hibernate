package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import java.util.List;

public interface CoachDao extends PersonDao {
    List<Coach> findByExperienceGreaterThan(int years);
}
