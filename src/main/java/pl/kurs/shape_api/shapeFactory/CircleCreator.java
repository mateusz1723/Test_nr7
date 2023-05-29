package pl.kurs.shape_api.shapeFactory;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.kurs.shape_api.models.*;
import pl.kurs.shape_api.services.ShapeChangesEventService;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@Service
public class CircleCreator implements ShapeCreator {

    private final ShapeChangesEventService shapeChangesEventService;

    public CircleCreator(ShapeChangesEventService shapeChangesEventService) {
        this.shapeChangesEventService = shapeChangesEventService;
    }

    @Override
    public String getType() {
        return "CIRCLE";
    }

    @Override
    public Shape create(Map<String, Object> parameters) {
        if (!parameters.containsKey("radius"))
            throw new IllegalArgumentException("Figura circle ma ma jeden parametr: radius");
        if (parameters.containsValue(0))
            throw new IllegalArgumentException("Parametry nie moga miec wartosci 0");
        return new Circle(getDoubleParameters("radius", parameters));
    }

    @Override
    public void update(Map<String, Object> parameters, Shape shape) {
        if (!parameters.containsKey("radius"))
            throw new IllegalArgumentException("Figura circle ma ma jeden parametr: radius");
        if (parameters.containsValue(0))
            throw new IllegalArgumentException("Parametry nie moga miec wartosci 0");

        ShapeChangesEvent shapeChangesEvent = new ShapeChangesEvent(LocalDate.now(), shape.getId(), shape.getLastModifiedBy());
        ShapeChanges shapeChanges = new ShapeChanges("radius", String.valueOf(((Circle) shape).getRadius()), parameters.get("radius").toString());
        ((Circle) shape).setRadius(getDoubleParameters("radius", parameters));

        shapeChangesEvent.addChanges(shapeChanges);
        shapeChangesEventService.saveEvent(shapeChangesEvent);
    }
}
