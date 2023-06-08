package pl.kurs.shape_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.kurs.shape_api.security.AppUser;

import javax.persistence.NamedQuery;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    @Query("SELECT u FROM AppUser u left join fetch  u.roles WHERE u.username = ?1")
    Optional<AppUser> findByUsernameWithRoles(String username);

    @Query("SELECT u FROM AppUser u WHERE u.username = ?1")
    Optional<AppUser> findByUsername(String username);

    @Query(value = "SELECT distinct u FROM AppUser u LEFT JOIN FETCH u.roles LEFT JOIN FETCH u.shapes",
    countQuery = "select count (u) from AppUser u")
    Page<AppUser> findAllByPageable(Pageable pageable);

}
