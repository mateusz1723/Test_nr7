package pl.kurs.shape_api.shapeFactory;

import org.springframework.stereotype.Service;
import pl.kurs.shape_api.commands.CreateShapeCommand;
import pl.kurs.shape_api.commands.UpdateShapeCommand;
import pl.kurs.shape_api.models.Shape;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ShapeFactory {

    private final Map<String, ShapeCreator> creators;

    public ShapeFactory(Set<ShapeCreator> creators){
        this.creators = creators.stream().collect(Collectors.toMap(ShapeCreator::getType, Function.identity()));
    }

    public Shape creator(CreateShapeCommand createShapeCommand){
        if (!creators.containsKey(createShapeCommand.getType().toUpperCase()))
            throw new IllegalArgumentException("Nie ma takiej figury do stworzenia");
        return creators.get(createShapeCommand.getType().toUpperCase()).create(createShapeCommand.getParameters());
    }

    public Shape updateShape(UpdateShapeCommand updateShapeCommand, Shape shape){
         return creators.get(shape.getType()).update(updateShapeCommand.getParameters(), shape);
    }
}

