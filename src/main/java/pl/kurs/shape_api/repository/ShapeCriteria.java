package pl.kurs.shape_api.repository;

import pl.kurs.shape_api.models.Shape;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Map;

public interface ShapeCriteria {

    String getType();
    List<Shape> getShapeByParameters(Map<String, String> param);
}
