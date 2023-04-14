package pl.kurs.shape_api.shapeFactory;

import org.springframework.stereotype.Service;
import pl.kurs.shape_api.models.Shape;
import pl.kurs.shape_api.models.Square;

import java.util.Map;

@Service
public class SquareCreator implements ShapeCreator{
    @Override
    public String getType() {
        return "SQUARE";
    }

    @Override
    public Shape create(Map<String, Object> parameters) {
        if (!parameters.containsKey("sideLength"))
            throw new IllegalArgumentException("Figura square posiada tylko jeden parametr: sideLength");
        if (parameters.containsValue(0))
            throw new IllegalArgumentException("Parametry nie moga miec wartosci 0");
        return new Square(getDoubleParameters("sideLength", parameters));
    }
}
