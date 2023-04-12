package pl.kurs.shape_api.shapeFactory;

import org.springframework.stereotype.Service;
import pl.kurs.shape_api.commands.CreateShapeCommand;
import pl.kurs.shape_api.models.Shape;
import pl.kurs.shape_api.models.ShapeType;

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
        ShapeType[] shapes = ShapeType.values();
        if (Arrays.stream(shapes).noneMatch(x -> x.name().equals(createShapeCommand.getType().toUpperCase())))
            throw new IllegalArgumentException("Nie ma takiej figury do stworzenia");
        return creators.get(createShapeCommand.getType().toUpperCase().trim()).create(createShapeCommand.getParameters());
    }
}

