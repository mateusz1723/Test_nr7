package pl.kurs.shape_api.shapeFactory;

import org.springframework.stereotype.Service;
import pl.kurs.shape_api.models.*;
import pl.kurs.shape_api.services.ShapeChangesEventService;

import java.time.LocalDate;
import java.util.Map;

@Service
public class SquareCreator implements ShapeCreator {

    private final ShapeChangesEventService shapeChangesEventService;

    public SquareCreator(ShapeChangesEventService shapeChangesEventService) {
        this.shapeChangesEventService = shapeChangesEventService;
    }

    @Override
    public String getType() {
        return "SQUARE";
    }

    @Override
    public Shape create(Map<String, Object> parameters) {
        if (!parameters.containsKey("sideLength"))
            throw new IllegalArgumentException("Figura square posiada tylko jeden parametr: sideLength");
        if (parameters.containsValue(0))
            throw new IllegalArgumentException("Parametry nie moga miec wartosci 0");
        return new Square(getDoubleParameters("sideLength", parameters));
    }

    @Override
    public void update(Map<String, Object> parameters, Shape shape) {
        if (!parameters.containsKey("sideLength"))
            throw new IllegalArgumentException("Figura square posiada tylko jeden parametr: sideLength");
        if (parameters.containsValue(0))
            throw new IllegalArgumentException("Parametry nie moga miec wartosci 0");

        ShapeChangesEvent shapeChangesEvent = new ShapeChangesEvent(LocalDate.now(), shape.getId(), shape.getLastModifiedBy());
        ShapeChanges shapeChanges = new ShapeChanges("sideLength", String.valueOf(((Square) shape).getSideLength()), parameters.get("sideLength").toString());

        ((Square) shape).setSideLength(getDoubleParameters("sideLength", parameters));

        shapeChangesEvent.addChanges(shapeChanges);
        shapeChangesEventService.saveEvent(shapeChangesEvent);

    }

}
