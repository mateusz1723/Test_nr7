package pl.kurs.shape_api.repository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import pl.kurs.shape_api.models.Circle;
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
public class CircleCriteria implements ShapeCriteria {


    private final ModelMapper modelMapper;
    private final EntityManager entityManager;

    public CircleCriteria(ModelMapper modelMapper, EntityManager entityManager) {
        this.modelMapper = modelMapper;
        this.entityManager = entityManager;
    }

    @Override
    public String getType() {
        return "CIRCLE";
    }

    @Override
    public List<Shape> getShapeByParameters(Map<String, String> param) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Circle> criteriaQuery = criteriaBuilder.createQuery(Circle.class);
        Root<Circle> shapeRoot = criteriaQuery.from(Circle.class);
        Predicate predicate = getPredicate(param, criteriaBuilder, shapeRoot);
        criteriaQuery.where(predicate);
        TypedQuery<Circle> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getResultList().stream().map(x -> modelMapper.map(x, Shape.class)).collect(Collectors.toList());
    }


    private Predicate getPredicate(Map<String, String> param, CriteriaBuilder criteriaBuilder, Root<Circle> shapeRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(param.get("radiusFrom"))) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(shapeRoot.get("radius"), param.get("radiusFrom")));
        }
        if (Objects.nonNull(param.get("radiusTo"))) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(shapeRoot.get("radius"), param.get("radiusTo")));
        }
        Predicate shapePredicates = ShapeCriteriaRepository.getPredicates(param, criteriaBuilder, shapeRoot);
        predicates.add(shapePredicates);

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
