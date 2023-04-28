package pl.kurs.shape_api.services;

import org.springframework.stereotype.Service;
import pl.kurs.shape_api.commands.CreateShapeCommand;
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

    public Shape createShape(CreateShapeCommand createShapeCommand){
        return shapeFactory.creator(createShapeCommand);
    }


    public Shape add(Shape entity) {
        return shapeRepository.save(
                Optional.ofNullable(entity)
                        .orElseThrow(() -> new RuntimeException("Bad entity!"))
        );
    }


    public Shape get(Long id) {
        return shapeRepository.findById(id).orElseThrow(() -> new RuntimeException("There is no entity with id: " + id));
    }


    public List<Shape> getAll() {
        return shapeRepository.findAll();
    }

    public List<Shape> getShapesBySpecificParameters(Map<String, String> param) {
        return shapeCriteriaRepository.getByFilters(param);
    }
}
