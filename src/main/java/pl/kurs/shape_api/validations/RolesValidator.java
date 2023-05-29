package pl.kurs.shape_api.validations;

import pl.kurs.shape_api.security.AppRole;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.stream.Collectors;

public class RolesValidator implements ConstraintValidator<Roles, Set<AppRole>> {
    @Override
    public void initialize(Roles constraintAnnotation) {

    }

    @Override
    public boolean isValid(Set<AppRole> appRole, ConstraintValidatorContext constraintValidatorContext) {
        Set<AppRole> collect = appRole.stream().filter(x -> x.getName().equals("ROLE_ADMIN") || x.getName().equals("ROLE_CREATOR") || x.getName().equals("TAK")).collect(Collectors.toSet());
        return !collect.isEmpty();
    }
}
