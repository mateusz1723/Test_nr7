package pl.kurs.shape_api.repository;

import org.springframework.stereotype.Repository;
import pl.kurs.shape_api.models.Shape;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Repository
public class ShapeCriteriaRepo {


    private final Map<String, ShapeCriteria> filters;

    public ShapeCriteriaRepo(Set<ShapeCriteria> filters) {
        this.filters = filters.stream().collect(Collectors.toMap(ShapeCriteria::getType, Function.identity()));
    }

    public List<Shape> getSpecificShapes(Map<String, String> param) {
        String typeBy = param.get("typeBy");
        if (typeBy == null)
            return null;
        if (!filters.containsKey(typeBy.toUpperCase()))
            throw new IllegalArgumentException("Nie ma takiej figury do wyszukania albo podales wiecej niz jedna figure");
        return filters.get(typeBy.toUpperCase()).getShapeByParameters(param);
    }


}
