package pl.kurs.shape_api.shapeAttribute;


import org.springframework.stereotype.Service;
import pl.kurs.shape_api.models.Shape;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ShapeAttribute {

    private final Map<String, Attribute> filters;

    public ShapeAttribute(Set<Attribute> filters) {
        this.filters = filters.stream().collect(Collectors.toMap(Attribute::getType, Function.identity()));
    }

    public List<Shape> getAttributes(Map<String, String> param, List<Shape> shapes) {
        String typeBy = param.get("typeBy");
        if (typeBy == null)
            return shapes;
        if (!filters.containsKey(typeBy.toUpperCase()))
            throw new IllegalArgumentException("Nie ma takiej figury do wyszukania albo podales wiecej niz jedna figure");
        return filters.get(typeBy.toUpperCase()).getShapeByParameters(param, shapes);
    }
}
