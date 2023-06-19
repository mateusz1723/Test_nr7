package pl.kurs.shape_api.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.shape_api.commands.CreateShapeCommand;
import pl.kurs.shape_api.commands.UpdateShapeCommand;
import pl.kurs.shape_api.models.Shape;
import pl.kurs.shape_api.repository.ShapeCriteriaRepository;
import pl.kurs.shape_api.repository.ShapeRepository;
import pl.kurs.shape_api.shapeFactory.ShapeFactory;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ShapeService {

    private final ShapeRepository shapeRepository;
    private final ShapeFactory shapeFactory;
    private final ShapeCriteriaRepository shapeCriteriaRepository;

    public ShapeService(ShapeRepository shapeRepository, ShapeFactory shapeFactory, ShapeCriteriaRepository shapeCriteriaRepository) {
        this.shapeRepository = shapeRepository;
        this.shapeFactory = shapeFactory;
        this.shapeCriteriaRepository = shapeCriteriaRepository;
    }

    public Shape createShape(CreateShapeCommand createShapeCommand) {
        return shapeFactory.creator(createShapeCommand);
    }

    @Transactional
    public Shape add(Shape entity) {
        return shapeRepository.saveAndFlush(
                Optional.ofNullable(entity)
                        .orElseThrow(() -> new RuntimeException("Bad entity!"))
        );
    }

    @Transactional(readOnly = true)
    public Shape getById(Long id) {
        return shapeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("There is no entity with id: " + id));
    }

    @Transactional
    @PreAuthorize("#shape.createdBy == principal.username or hasRole('ROLE_ADMIN')")
    public Shape edit(Shape shape, UpdateShapeCommand updateShapeCommand) {
        Shape newShape = shapeFactory.updateShape(updateShapeCommand, shape);
        newShape.setVersion(updateShapeCommand.getVersion());
        return add(newShape);
    }


    public List<Shape> getShapesBySpecificParameters(Map<String, String> param) {
        return shapeCriteriaRepository.getByFilters(param);
    }
}
