package pl.kurs.shape_api.repository;

import org.hibernate.mapping.Formula;
import org.hibernate.metamodel.model.domain.internal.SingularAttributeImpl;
import org.springframework.data.spel.spi.Function;
import org.springframework.stereotype.Repository;
import pl.kurs.shape_api.models.Circle;
import pl.kurs.shape_api.models.Shape;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.*;

@Repository
public class ShapeCriteriaRepository {

    private final EntityManager entityManager;
    private final ShapeCriteriaRepo shapeCriteriaRepo;

    public ShapeCriteriaRepository(EntityManager entityManager, ShapeCriteriaRepo shapeCriteriaRepo) {
        this.entityManager = entityManager;
        this.shapeCriteriaRepo = shapeCriteriaRepo;
    }


    public List<Shape> getByFilters(Map<String, String> param) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Shape> criteriaQuery = criteriaBuilder.createQuery(Shape.class);
        Root<Shape> shapeRoot = criteriaQuery.from(Shape.class);

        if (shapeCriteriaRepo.getSpecificShapes(param) != null) {
            return shapeCriteriaRepo.getSpecificShapes(param);
        }
        Predicate predicate = getPredicates(param, criteriaBuilder, shapeRoot);
        criteriaQuery.where(predicate);

        TypedQuery<Shape> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }


    public static Predicate getPredicates(Map<String, String> param, CriteriaBuilder criteriaBuilder, Root<? extends Shape> shapeRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(param.get("createdBy"))) {
            predicates.add(criteriaBuilder.equal(shapeRoot.get("createdBy"), param.get("createdBy")));
        }
        if (Objects.nonNull(param.get("createdAtFrom"))) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(shapeRoot.get("createdAt"), LocalDate.parse(param.get("createdAtFrom"))));
        }
        if (Objects.nonNull(param.get("createdAtTo"))) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(shapeRoot.get("createdAt"), LocalDate.parse(param.get("createdAtTo"))));
        }
        if (Objects.nonNull(param.get("areaFrom"))) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(shapeRoot.get("area"), param.get("areaFrom")));
        }
        if (Objects.nonNull(param.get("areaTo"))) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(shapeRoot.get("area"), param.get("areaTo")));
        }
        if (Objects.nonNull(param.get("perimeterFrom"))) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(shapeRoot.get("perimeter"), param.get("perimeterFrom")));
        }
        if (Objects.nonNull(param.get("perimeterTo"))) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(shapeRoot.get("perimeter"), param.get("perimeterTo")));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
