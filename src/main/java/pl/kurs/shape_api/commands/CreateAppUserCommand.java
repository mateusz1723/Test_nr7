package pl.kurs.shape_api.commands;
import pl.kurs.shape_api.security.AppRole;
import pl.kurs.shape_api.validations.Roles;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

public class CreateAppUserCommand {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotEmpty
    @Roles
    private Set<AppRole> roles = new HashSet<>();


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Set<AppRole> getRoles() {
        return roles;
    }

}
