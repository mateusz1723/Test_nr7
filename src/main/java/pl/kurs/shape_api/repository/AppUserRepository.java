package pl.kurs.shape_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.kurs.shape_api.security.AppUser;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    @Query("SELECT u FROM AppUser u left join fetch  u.roles WHERE u.username = ?1")
    Optional<AppUser> findByUsernameWithRoles(String username);

    @Query("SELECT u FROM AppUser u WHERE u.username = ?1")
    Optional<AppUser> findByUsername(String username);

}

