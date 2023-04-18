package pl.kurs.shape_api.shapeAttribute;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.kurs.shape_api.models.Circle;
import pl.kurs.shape_api.models.Shape;
import pl.kurs.shape_api.services.ShapeService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CircleAttribute implements Attribute {

    private final ShapeService shapeService;

    public CircleAttribute(ShapeService shapeService) {
        this.shapeService = shapeService;
    }

    @Override
    public String getType() {
        return "CIRCLE";
    }

    @Override
    public List<Shape> getShapeByParameters(Map<String, String> param, List<Shape> shapes) {
        String radiusFrom = param.getOrDefault("radiusFrom", "0");
        String radiusTo = param.getOrDefault("radiusTo", "500");

        return shapes.stream()
                .filter(x -> x instanceof Circle)
                .filter(x -> ((Circle) x).getRadius() >= Double.parseDouble(radiusFrom) && ((Circle) x).getRadius() <= Double.parseDouble(radiusTo))
                .collect(Collectors.toList());
    }
}
