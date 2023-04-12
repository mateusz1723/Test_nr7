package pl.kurs.shape_api.shapeFactory;

import org.springframework.stereotype.Service;
import pl.kurs.shape_api.models.Rectangle;
import pl.kurs.shape_api.models.Shape;
import pl.kurs.shape_api.models.ShapeType;

import java.util.Map;

@Service
public class RectangleCreator implements ShapeCreator{

    @Override
    public String getType() {
        return ShapeType.RECTANGLE.name();
    }

    @Override
    public Shape create(Map<String, Object> parameters) {
        if (!parameters.containsKey("width") || !parameters.containsKey("height"))
            throw new IllegalArgumentException("Figura rectangle ma dwa parametry ktore muszą byc podane: width i height");
        if (parameters.containsValue(0))
            throw new IllegalArgumentException("Parametry nie moga miec wartosci 0");
        return new Rectangle(getDoubleParameters("width", parameters), getDoubleParameters("height", parameters));
    }
}
