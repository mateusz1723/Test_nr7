package pl.kurs.shape_api.services;

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
    private final AppUserService appUserService;
    private final ShapeRepository shapeRepository;

    public ShapeChangesEventService(ShapeChangesEventRepository shapeChangesEventRepository, AppUserService appUserService, ShapeRepository shapeRepository) {
        this.shapeChangesEventRepository = shapeChangesEventRepository;
        this.appUserService = appUserService;
        this.shapeRepository = shapeRepository;
    }

    public ShapeChangesEvent saveEvent(ShapeChangesEvent shapeChanges){
        return shapeChangesEventRepository.save( Optional.ofNullable(shapeChanges)
                .orElseThrow(() -> new RuntimeException("Bad entity!")));
    }

    public List<ShapeChangesEvent> getEventByShapeId(long id){
        Shape shape = shapeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("There is no shape with id:" + id));
        AppUser shapeAppUser = appUserService.getSingleAppUserById(shape.getAppUser().getId());
        String basicAuthUsername = ((UserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUsername();
        AppUser basicAuthAppUser = appUserService.getAppUserByUsernameWithRoles(basicAuthUsername);
        if (shapeAppUser.getUsername().equals(basicAuthUsername) || basicAuthAppUser.getRoles().stream().anyMatch(x -> x.getName().equals("ROLE_ADMIN"))) {
            return shapeChangesEventRepository.findAllByShapeId(id).orElseThrow(() -> new IllegalArgumentException("There is no changes with id" + id));
        } else
            throw new IllegalArgumentException("You have no permission!");
    }
}
