package pl.kurs.shape_api.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kurs.shape_api.commands.CreateShapeCommand;
import pl.kurs.shape_api.commands.UpdateShapeCommand;
import pl.kurs.shape_api.dto.ShapeChangesEventDto;
import pl.kurs.shape_api.dto.ShapeDto;
import pl.kurs.shape_api.exceptionhandling.VersionNotEqualsException;
import pl.kurs.shape_api.mapper.ShapeMapper;
import pl.kurs.shape_api.models.*;
import pl.kurs.shape_api.security.AppUser;
import pl.kurs.shape_api.services.AppUserService;
import pl.kurs.shape_api.services.ShapeChangesEventService;
import pl.kurs.shape_api.services.ShapeService;

import javax.validation.Valid;


import java.util.*;
import java.util.stream.Collectors;


@Validated
@RestController
@RequestMapping("/api/v1/shapes")
public class ShapeController {

    private final ShapeService shapeService;
    private final ShapeMapper shapeMapper;
    private final AppUserService appUserService;
    private final ShapeChangesEventService shapeChangesEventService;
    private final ModelMapper modelMapper;


    public ShapeController(ShapeService shapeService, ShapeMapper shapeMapper, AppUserService appUserService, ShapeChangesEventService shapeChangesEventService, ModelMapper modelMapper) {
        this.shapeService = shapeService;
        this.shapeMapper = shapeMapper;
        this.appUserService = appUserService;
        this.shapeChangesEventService = shapeChangesEventService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<ShapeDto> createShape(@RequestBody @Valid CreateShapeCommand createShapeCommand) {
        Shape shape = shapeService.createShape(createShapeCommand);
        AppUser appUser = appUserService.getAppUserByUsername(((UserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUsername());
        appUser.addShape(shape);
        shapeService.add(shape);
        return ResponseEntity.status(HttpStatus.CREATED).body(shapeMapper.mapToDto(shape));
    }


    @GetMapping
    public ResponseEntity<List<ShapeDto>> getShapesWithSpecificParameters(@RequestParam Map<String, String> parameters) {
        List<Shape> shapesBySpecificParameters = shapeService.getShapesBySpecificParameters(parameters);
        List<ShapeDto> shapesDto = shapesBySpecificParameters.stream()
                .map(shapeMapper::mapToDto)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(shapesDto);
    }

    @GetMapping("/{id}/changes")
    public ResponseEntity<List<ShapeChangesEventDto>> getShapeChangesEvent(@PathVariable (name = "id") long id){
        List<ShapeChangesEvent> eventList = shapeChangesEventService.getEventByShapeId(id);
        List<ShapeChangesEventDto> collect = eventList.stream()
                .map(x -> new ShapeChangesEventDto(x.getId(), x.getChangedDate(), x.getShapeId(), x.getChangesBy(), x.getChanges()))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(collect);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ShapeDto> editShape(@PathVariable(name = "id") long id, @RequestBody UpdateShapeCommand updateShapeCommand) throws VersionNotEqualsException {
        Shape edit = shapeService.edit(id, updateShapeCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(shapeMapper.mapToDto(edit));
    }
}
