package pl.kurs.shape_api.repository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import pl.kurs.shape_api.models.Rectangle;
import pl.kurs.shape_api.models.Shape;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class RectangleCriteria implements ShapeCriteria {

    private final ModelMapper modelMapper;
    private final EntityManager entityManager;

    public RectangleCriteria(ModelMapper modelMapper, EntityManager entityManager) {
        this.modelMapper = modelMapper;
        this.entityManager = entityManager;
    }

    @Override
    public String getType() {
        return "RECTANGLE";
    }

    @Override
    public List<Shape> getShapeByParameters(Map<String, String> param) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Rectangle> criteriaQuery = criteriaBuilder.createQuery(Rectangle.class);
        Root<Rectangle> shapeRoot = criteriaQuery.from(Rectangle.class);
        Predicate predicate = getPredicate(param, criteriaBuilder, shapeRoot);
        criteriaQuery.where(predicate);
        TypedQuery<Rectangle> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getResultList().stream().map(x -> modelMapper.map(x, Shape.class)).collect(Collectors.toList());

    }


    private Predicate getPredicate(Map<String, String> param, CriteriaBuilder criteriaBuilder, Root<Rectangle> shapeRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(param.get("widthFrom"))) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(shapeRoot.get("width"), param.get("widthFrom")));
        }
        if (Objects.nonNull(param.get("widthTo"))) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(shapeRoot.get("width"), param.get("widthTo")));
        }
        if (Objects.nonNull(param.get("heightFrom"))) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(shapeRoot.get("height"), param.get("heightFrom")));
        }
        if (Objects.nonNull(param.get("heightTo"))) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(shapeRoot.get("height"), param.get("heightTo")));
        }
        Predicate shapePredicates = ShapeCriteriaRepository.getPredicates(param, criteriaBuilder, shapeRoot);
        predicates.add(shapePredicates);
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
