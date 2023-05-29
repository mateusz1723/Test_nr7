package pl.kurs.shape_api.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.shape_api.exceptionhandling.NotUniqueUsernameException;
import pl.kurs.shape_api.repository.AppUserRepository;
import pl.kurs.shape_api.security.AppUser;

import java.util.Optional;


@Service
public class AppUserService implements UserDetailsService {

    private final AppUserRepository repository;

    public AppUserService(AppUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsernameWithRoles(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }


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

    public Page<AppUser> getAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public void checkUniqueUsername(String username) throws NotUniqueUsernameException {
        Optional<AppUser> byUsername = repository.findByUsername(username);
        if (byUsername.isPresent())
            throw new NotUniqueUsernameException("Username is already exist!");
    }

}
