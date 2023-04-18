package pl.kurs.shape_api.shapeAttribute;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.kurs.shape_api.models.Rectangle;
import pl.kurs.shape_api.models.Shape;
import pl.kurs.shape_api.services.ShapeService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RectangleAttribute implements Attribute {

    private final ShapeService shapeService;

    public RectangleAttribute(ShapeService shapeService) {
        this.shapeService = shapeService;
    }


    @Override
    public String getType() {
        return "RECTANGLE";
    }

    @Override
    public List<Shape> getShapeByParameters(Map<String, String> param, List<Shape> shapes) {
        String widthFrom = param.getOrDefault("widthFrom", "0");
        String widthTo = param.getOrDefault("widthTo", "500");
        String heightFrom = param.getOrDefault("heightFrom", "0");
        String heightTo = param.getOrDefault("heightTo", "500");

        return shapes.stream()
                .filter(x -> x instanceof Rectangle)
                .filter(x -> ((Rectangle) x).getWidth() >= Double.parseDouble(widthFrom) && ((Rectangle) x).getWidth() <= Double.parseDouble(widthTo))
                .filter(x -> ((Rectangle) x).getHeight() >= Double.parseDouble(heightFrom) && ((Rectangle) x).getHeight() <= Double.parseDouble(heightTo))
                .collect(Collectors.toList());
    }
}
