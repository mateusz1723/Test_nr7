package pl.kurs.shape_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.shape_api.models.Shape;


public interface ShapeRepository extends JpaRepository<Shape, Long> {


}
