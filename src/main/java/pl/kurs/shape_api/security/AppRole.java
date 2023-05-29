package pl.kurs.shape_api.security;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class AppRole implements GrantedAuthority {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_app_role")
    private long id;
    @Column(nullable = false)
    private String name;

    public AppRole() {
    }

    public AppRole( String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppRole appRole = (AppRole) o;
        return id == appRole.id && Objects.equals(name, appRole.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
