package pl.kurs.shape_api.repository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import pl.kurs.shape_api.models.Shape;
import pl.kurs.shape_api.models.Square;

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
public class SquareCriteria implements ShapeCriteria {


    private final ModelMapper modelMapper;
    private final EntityManager entityManager;

    public SquareCriteria(ModelMapper modelMapper, EntityManager entityManager) {
        this.modelMapper = modelMapper;
        this.entityManager = entityManager;
    }

    @Override
    public String getType() {
        return "SQUARE";
    }

    @Override
    public List<Shape> getShapeByParameters(Map<String, String> param) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Square> criteriaQuery = criteriaBuilder.createQuery(Square.class);
        Root<Square> shapeRoot = criteriaQuery.from(Square.class);
        Predicate predicate = getPredicate(param, criteriaBuilder, shapeRoot);
        criteriaQuery.where(predicate);
        TypedQuery<Square> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getResultList().stream().map(x -> modelMapper.map(x, Shape.class)).collect(Collectors.toList());
    }

    private Predicate getPredicate(Map<String, String> param, CriteriaBuilder criteriaBuilder, Root<Square> shapeRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(param.get("sideLengthFrom"))) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(shapeRoot.get("sideLength"), param.get("sideLengthFrom")));
        }
        if (Objects.nonNull(param.get("sideLengthTo"))) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(shapeRoot.get("sideLength"), param.get("sideLengthTo")));
        }
        Predicate shapePredicates = ShapeCriteriaRepository.getPredicates(param, criteriaBuilder, shapeRoot);
        predicates.add(shapePredicates);
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
