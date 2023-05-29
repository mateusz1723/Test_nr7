package pl.kurs.shape_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.kurs.shape_api.models.ShapeChangesEvent;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShapeChangesEventRepository extends JpaRepository<ShapeChangesEvent, Long> {

    @Query(value = "SELECT distinct s FROM ShapeChangesEvent s LEFT JOIN FETCH s.changes WHERE s.shapeId = ?1",
    countQuery = "select count (s) from ShapeChangesEvent s")
    Optional<List<ShapeChangesEvent>> findAllByShapeId(long id);

}
