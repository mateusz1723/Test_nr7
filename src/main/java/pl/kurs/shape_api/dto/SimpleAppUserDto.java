package pl.kurs.shape_api.dto;

import pl.kurs.shape_api.security.AppRole;

import java.util.Set;

public class SimpleAppUserDto {

    private long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private Set<AppRoleDto> roles;
    private int numberOfShapes;


    public SimpleAppUserDto(long id, String name, String surname, String username, String password, Set<AppRoleDto> roles, int numberOfShapes) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.numberOfShapes = numberOfShapes;
    }

    public Set<AppRoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<AppRoleDto> roles) {
        this.roles = roles;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNumberOfShapes() {
        return numberOfShapes;
    }

    public void setNumberOfShapes(int numberOfShapes) {
        this.numberOfShapes = numberOfShapes;
    }
}
