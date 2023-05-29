package pl.kurs.shape_api.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kurs.shape_api.commands.CreateAppUserCommand;
import pl.kurs.shape_api.dto.AppUserDto;
import pl.kurs.shape_api.dto.SimpleAppUserDto;
import pl.kurs.shape_api.exceptionhandling.NotUniqueUsernameException;
import pl.kurs.shape_api.security.AppRole;
import pl.kurs.shape_api.security.AppUser;
import pl.kurs.shape_api.services.AppUserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
@Validated
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final AppUserService appUserService;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserController(AppUserService appUserService, ModelMapper mapper, PasswordEncoder passwordEncoder) {
        this.appUserService = appUserService;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<AppUserDto> addUser(@RequestBody @Valid CreateAppUserCommand command) throws NotUniqueUsernameException {
        appUserService.checkUniqueUsername(command.getUsername());
        AppUser newUser = mapper.map(command, AppUser.class);
        newUser.setPassword(passwordEncoder.encode(command.getPassword()));
        AppUser savedUser = appUserService.add(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(savedUser, AppUserDto.class));
    }

    @Transactional
    @GetMapping
    public ResponseEntity<List<SimpleAppUserDto>> getUsers(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(appUserService.getAll(pageable).stream()
                .map(x -> new SimpleAppUserDto(x.getId(), x.getName(), x.getSurname(),x.getUsername(), "*SECRET!*", x.getRoles(), x.getShapes().size()))
                .collect(Collectors.toList()));
    }



}
