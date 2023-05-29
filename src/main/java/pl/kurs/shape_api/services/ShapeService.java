package pl.kurs.shape_api.services;

import org.hibernate.annotations.OptimisticLock;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.shape_api.commands.CreateShapeCommand;
import pl.kurs.shape_api.commands.UpdateShapeCommand;
import pl.kurs.shape_api.exceptionhandling.VersionNotEqualsException;
import pl.kurs.shape_api.models.Shape;
import pl.kurs.shape_api.repository.ShapeCriteriaRepository;
import pl.kurs.shape_api.repository.ShapeRepository;
import pl.kurs.shape_api.security.AppUser;
import pl.kurs.shape_api.shapeFactory.ShapeFactory;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ShapeService {

    private final ShapeRepository shapeRepository;
    private final ShapeFactory shapeFactory;
    private final ShapeCriteriaRepository shapeCriteriaRepository;
    private final AppUserService appUserService;

    public ShapeService(ShapeRepository shapeRepository, ShapeFactory shapeFactory, ShapeCriteriaRepository shapeCriteriaRepository, AppUserService appUserService) {
        this.shapeRepository = shapeRepository;
        this.shapeFactory = shapeFactory;
        this.shapeCriteriaRepository = shapeCriteriaRepository;
        this.appUserService = appUserService;
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

    public Shape edit(Long id, UpdateShapeCommand updateShapeCommand) throws VersionNotEqualsException {
        Shape shape = getById(id);
        AppUser shapeAppUser = appUserService.getSingleAppUserById(shape.getAppUser().getId());
        String basicAuthUsername = ((UserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUsername();
        AppUser basicAuthAppUser = appUserService.getAppUserByUsername(basicAuthUsername);
        if (shape.getVersion() != updateShapeCommand.getVersion())
            throw new VersionNotEqualsException("Version while updating is not same!");
        if (shapeAppUser.getUsername().equals(basicAuthUsername) || basicAuthAppUser.getRoles().stream().anyMatch(x -> x.getName().equals("ROLE_ADMIN"))) {
            shape.setVersion(updateShapeCommand.getVersion());
            shape.setLastModifiedBy(basicAuthUsername);
            shapeFactory.updateShape(updateShapeCommand, shape);
        } else
            throw new IllegalArgumentException("You have no permission to update");

        return add(shape);
    }

    public List<Shape> getShapesBySpecificParameters(Map<String, String> param) {
        return shapeCriteriaRepository.getByFilters(param);
    }
}
