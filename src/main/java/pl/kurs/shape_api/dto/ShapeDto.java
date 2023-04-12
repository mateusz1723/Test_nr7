package pl.kurs.shape_api.dto;

import pl.kurs.shape_api.models.ShapeType;

import java.time.LocalDate;

public abstract class ShapeDto {

    private Long id;
    private ShapeType type;
    private String version;
    private String createdBy;
    private LocalDate createdAt;
    private LocalDate lastModifiedAt;
    private LocalDate lastModifiedBy;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShapeType getType() {
        return type;
    }

    public void setType(ShapeType type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(LocalDate lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public LocalDate getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(LocalDate lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
