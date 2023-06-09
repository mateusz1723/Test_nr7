package pl.kurs.shape_api.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.kurs.shape_api.models.Shape;
import pl.kurs.shape_api.models.ShapeChangesEvent;
import pl.kurs.shape_api.repository.ShapeChangesEventRepository;
import pl.kurs.shape_api.repository.ShapeRepository;
import pl.kurs.shape_api.security.AppUser;

import java.util.List;
import java.util.Optional;

@Service
public class ShapeChangesEventService {

    private final ShapeChangesEventRepository shapeChangesEventRepository;

    public ShapeChangesEventService(ShapeChangesEventRepository shapeChangesEventRepository) {
        this.shapeChangesEventRepository = shapeChangesEventRepository;
    }

    public ShapeChangesEvent saveEvent(ShapeChangesEvent shapeChanges){
        return shapeChangesEventRepository.save( Optional.ofNullable(shapeChanges)
                .orElseThrow(() -> new RuntimeException("Bad entity!")));
    }

    @PreAuthorize("#shape.createdBy == principal.username or hasRole('ROLE_ADMIN')")
    public List<ShapeChangesEvent> getEventByShapeAndId(long id, Shape shape){
            return shapeChangesEventRepository.findAllByShapeId(id)
                    .orElseThrow(() -> new IllegalArgumentException("There is no changes with id" + id));
    }
}
