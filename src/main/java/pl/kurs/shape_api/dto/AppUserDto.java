package pl.kurs.shape_api.dto;

import pl.kurs.shape_api.models.Shape;
import pl.kurs.shape_api.security.AppRole;

import java.util.Set;

public class AppUserDto {

    private long id;
    private String name;
    private String surname;
    private String username;
    private Set<AppRole> roles;
    private Set<Shape> shapes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AppRole> roles) {
        this.roles = roles;
    }

    public Set<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(Set<Shape> shapes) {
        this.shapes = shapes;
    }
}
