package pl.kurs.shape_api.shapeFactory;

import org.springframework.stereotype.Service;
import pl.kurs.shape_api.models.*;
import pl.kurs.shape_api.services.ShapeChangesEventService;

import java.time.LocalDate;
import java.util.Map;

@Service
public class RectangleCreator implements ShapeCreator {

    private final ShapeChangesEventService shapeChangesEventService;

    public RectangleCreator(ShapeChangesEventService shapeChangesEventService) {
        this.shapeChangesEventService = shapeChangesEventService;
    }

    @Override
    public String getType() {
        return "RECTANGLE";
    }

    @Override
    public Shape create(Map<String, Object> parameters) {
        if (!parameters.containsKey("width") || !parameters.containsKey("height"))
            throw new IllegalArgumentException("Figura rectangle ma dwa parametry ktore muszą byc podane: width i height");
        if (parameters.containsValue(0))
            throw new IllegalArgumentException("Parametry nie moga miec wartosci 0");
        return new Rectangle(getDoubleParameters("width", parameters), getDoubleParameters("height", parameters));
    }

    @Override
    public void update(Map<String, Object> parameters, Shape shape) {
        if (!parameters.containsKey("width") || !parameters.containsKey("height"))
            throw new IllegalArgumentException("Figura rectangle ma dwa parametry ktore muszą byc podane: width i height");
        if (parameters.containsValue(0))
            throw new IllegalArgumentException("Parametry nie moga miec wartosci 0");

        ShapeChangesEvent shapeChangesEvent = new ShapeChangesEvent(LocalDate.now(), shape.getId(), shape.getLastModifiedBy());

        ShapeChanges shapeChanges1 = new ShapeChanges("width", String.valueOf(((Rectangle) shape).getWidth()), parameters.get("width").toString());
        ShapeChanges shapeChanges2 = new ShapeChanges("height", String.valueOf(((Rectangle) shape).getHeight()), parameters.get("height").toString());

        ((Rectangle) shape).setWidth(getDoubleParameters("width", parameters));
        ((Rectangle) shape).setHeight(getDoubleParameters("height", parameters));

        shapeChangesEvent.addChanges(shapeChanges1);
        shapeChangesEvent.addChanges(shapeChanges2);
        shapeChangesEventService.saveEvent(shapeChangesEvent);
    }
}
