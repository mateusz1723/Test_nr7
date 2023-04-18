package pl.kurs.shape_api.shapeAttribute;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.kurs.shape_api.models.Shape;
import pl.kurs.shape_api.models.Square;
import pl.kurs.shape_api.services.ShapeService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SquareAttribute implements Attribute {


    private final ShapeService shapeService;

    public SquareAttribute(ShapeService shapeService) {
        this.shapeService = shapeService;
    }

    @Override
    public String getType() {
        return "SQUARE";
    }

    @Override
    public List<Shape> getShapeByParameters(Map<String, String> param, List<Shape> shapes) {
        String sideLengthFrom = param.getOrDefault("sideLengthFrom", "0");
        String sideLengthTo = param.getOrDefault("sideLengthTo", "500");

        return shapes.stream()
                .filter(x -> x instanceof Square)
                .filter(x -> ((Square) x).getSideLength() >= Double.parseDouble(sideLengthFrom) && ((Square) x).getSideLength() <= Double.parseDouble(sideLengthTo))
                .collect(Collectors.toList());
    }
}
