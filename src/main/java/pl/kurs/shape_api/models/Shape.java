package pl.kurs.shape_api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pl.kurs.shape_api.security.AppUser;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Shape implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Version
    private int version;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    @CreatedBy
    private String createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    @CreatedDate
    private LocalDate createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDate lastModifiedAt;

    @Column(nullable = false)
    @LastModifiedBy
    private String lastModifiedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;


    public Shape() {
    }

    public Shape(int version, String type, String createdBy, LocalDate createdAt, LocalDate lastModifiedAt, String lastModifiedBy) {
        this.version = version;
        this.type = type;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
        this.lastModifiedBy = lastModifiedBy;
    }


    public abstract double getPerimeter();
    public abstract double getArea();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @Override
    public String toString() {
        return "Shape{" +
                "id=" + id +
                ", version='" + version + '\'' +
                ", type=" + type +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", lastModifiedAt=" + lastModifiedAt +
                ", lastModifiedBy='" + lastModifiedBy +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape shape = (Shape) o;
        return id == shape.id && version == shape.version && Objects.equals(type, shape.type) && Objects.equals(createdBy, shape.createdBy) && Objects.equals(createdAt, shape.createdAt) && Objects.equals(lastModifiedAt, shape.lastModifiedAt) && Objects.equals(lastModifiedBy, shape.lastModifiedBy) && Objects.equals(appUser, shape.appUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, type, createdBy, createdAt, lastModifiedAt, lastModifiedBy, appUser);
    }
}
