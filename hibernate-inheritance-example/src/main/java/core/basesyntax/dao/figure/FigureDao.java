package core.basesyntax.dao.figure;

import core.basesyntax.model.figure.Figure;
import java.util.List;

public interface FigureDao<T extends Figure> {
    T save(T figure);

    List<T> findByColor(String color, Class<T> clazz);
}
