package pl.kurs.shape_api.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.shape_api.repository.AppUserRepo;
import pl.kurs.shape_api.repository.AppUserRepository;
import pl.kurs.shape_api.security.AppUser;

import java.util.List;
import java.util.Optional;


@Service
public class AppUserService implements UserDetailsService {

    private final AppUserRepository repository;
    private final AppUserRepo appUserRepo;

    public AppUserService(AppUserRepository repository, AppUserRepo appUserRepo) {
        this.repository = repository;
        this.appUserRepo = appUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsernameWithRoles(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }


    @Transactional
    public AppUser add(AppUser entity) {
        return repository.save(
                Optional.ofNullable(entity)
                        .orElseThrow(() -> new RuntimeException("Bad entity!"))
        );
    }

    @Transactional(readOnly = true)
    public AppUser getAppUserByUsername(String username){
        return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Transactional(readOnly = true)
    public AppUser getSingleAppUserById(long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("There is no entity with this id"));
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<AppUser> getAll(Pageable pageable){
        return appUserRepo.getAppUsersByPageableWithRolesAndShapes(pageable);
    }


    @Transactional(readOnly = true)
    public AppUser getAppUserByUsernameWithRoles(String username){
        return repository.findByUsernameWithRoles(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

}
