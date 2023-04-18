package pl.kurs.shape_api.shapeAttribute;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.kurs.shape_api.models.Shape;

import java.util.List;
import java.util.Map;


public interface Attribute {

    String getType();
    List<Shape> getShapeByParameters(Map<String, String> param, List<Shape> shapes);

}
