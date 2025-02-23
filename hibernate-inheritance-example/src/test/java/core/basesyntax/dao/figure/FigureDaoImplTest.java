package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Figure;
import core.basesyntax.model.figure.Triangle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class FigureDaoImplTest extends AbstractTest {
    public static final String PINK_COLOR = "Pink";
    public static final String RED_COLOR = "Red";
    private FigureDao<Figure> figureDao;
    private FigureDao<Triangle> triangleDao;
    private FigureDao<Circle> circleDao;

    @Override
    protected Class<?>[] entities() {
        return new Class[] {
                Circle.class,
                Triangle.class
        };
    }

    @Before
    public void setUp() {
        figureDao = new FigureDaoImpl<>(getSessionFactory());
        triangleDao = new FigureDaoImpl<>(getSessionFactory());
        circleDao = new FigureDaoImpl<>(getSessionFactory());
    }

    @Test
    public void createCircle_Ok() {
        Circle circle = new Circle();
        circle.setColor(PINK_COLOR);
        circle.setRadius(5);
        Figure actualCircle = figureDao.save(circle);
        Assert.assertNotNull(actualCircle);
        Assert.assertEquals(1L, actualCircle.getId().longValue());
    }

    @Test
    public void createTriangle_Ok() {
        Triangle triangle = new Triangle();
        triangle.setColor(RED_COLOR);
        triangle.setArea(10);
        Figure actualTriangle = figureDao.save(triangle);
        Assert.assertNotNull(actualTriangle);
        Assert.assertEquals(1L, actualTriangle.getId().longValue());
    }

    @Test
    public void findByColor_Triangle_Ok() {
        Triangle redTriangle = new Triangle();
        redTriangle.setColor(RED_COLOR);
        Triangle pinkTriangle = new Triangle();
        pinkTriangle.setColor(PINK_COLOR);
        Triangle secondPinkTriangle = new Triangle();
        secondPinkTriangle.setColor(PINK_COLOR);

        figureDao.save(redTriangle);
        figureDao.save(pinkTriangle);
        figureDao.save(secondPinkTriangle);

        List<Triangle> pinkTriangles = triangleDao.findByColor(PINK_COLOR, Triangle.class);
        Assert.assertNotNull(redTriangle.getId());
        Assert.assertNotNull(pinkTriangle.getId());
        Assert.assertNotNull(secondPinkTriangle.getId());
        Assert.assertEquals(2, pinkTriangles.size());
    }

    @Test
    public void findByColor_Circle_Ok() {
        Circle redCircle = new Circle();
        redCircle.setColor(RED_COLOR);
        Circle pinkCircle = new Circle();
        pinkCircle.setColor(PINK_COLOR);
        Circle secondPinkCircle = new Circle();
        secondPinkCircle.setColor(PINK_COLOR);

        figureDao.save(redCircle);
        figureDao.save(pinkCircle);
        figureDao.save(secondPinkCircle);
        List<Circle> pinkCircles = circleDao.findByColor(PINK_COLOR, Circle.class);
        Assert.assertNotNull(redCircle.getId());
        Assert.assertNotNull(pinkCircle.getId());
        Assert.assertNotNull(secondPinkCircle.getId());
        Assert.assertEquals(2, pinkCircles.size());
    }
}
