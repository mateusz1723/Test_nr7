package pl.kurs.shape_api.validations;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = RolesValidator.class)
@Target({FIELD, PARAMETER, ANNOTATION_TYPE, TYPE_USE})
@Retention(RUNTIME)
public @interface Roles {

    String message() default "You have only CREATOR and ADMIN role";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
