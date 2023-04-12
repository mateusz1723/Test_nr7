package pl.kurs.shape_api.shapeFactory;

import org.springframework.stereotype.Service;
import pl.kurs.shape_api.models.Circle;
import pl.kurs.shape_api.models.Shape;
import pl.kurs.shape_api.models.ShapeType;

import java.util.Map;

@Service
public class CircleCreator implements ShapeCreator{

    @Override
    public String getType() {
        return ShapeType.CIRCLE.name();
    }

    @Override
    public Shape create(Map<String, Object> parameters) {
        if (!parameters.containsKey("radius"))
            throw new IllegalArgumentException("Figura circle ma ma jeden parametr: radius");
        if (parameters.containsValue(0))
            throw new IllegalArgumentException("Parametry nie moga miec wartosci 0");
        return new Circle(getDoubleParameters("radius", parameters));
    }
}
