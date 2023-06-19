package pl.kurs.shape_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import pl.kurs.shape_api.security.AppUser;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.data.domain.Pageable;
import java.util.List;


@Repository
public class AppUserRepo {

    private final EntityManager entityManager;

    public AppUserRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<AppUser> getAppUsersByPageableWithRolesAndShapes(Pageable pageable){
        TypedQuery<Long> idQuery = entityManager.createQuery("SELECT u.id FROM AppUser u order by u.id ASC ", Long.class);
        idQuery.setFirstResult(pageable.getPageNumber());
        idQuery.setMaxResults(pageable.getPageSize());
        List<Long> appUserIds = idQuery.getResultList();

        TypedQuery<AppUser> query = entityManager.createQuery("SELECT DISTINCT u FROM AppUser u LEFT JOIN FETCH u.roles LEFT JOIN FETCH u.shapes WHERE u.id IN :ids", AppUser.class);
        query.setParameter("ids", appUserIds);
        return query.getResultList();
    }
}
