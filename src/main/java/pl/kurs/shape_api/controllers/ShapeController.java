package pl.kurs.shape_api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kurs.shape_api.commands.CreateShapeCommand;
import pl.kurs.shape_api.dto.ShapeDto;
import pl.kurs.shape_api.mapper.ShapeMapper;
import pl.kurs.shape_api.models.*;
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


    public ShapeController(ShapeService shapeService, ShapeMapper shapeMapper) {
        this.shapeService = shapeService;
        this.shapeMapper = shapeMapper;
    }

    @PostMapping
    public ResponseEntity<ShapeDto> createShape(@RequestBody @Valid CreateShapeCommand createShapeCommand) {
        Shape shape = shapeService.createShape(createShapeCommand);
        shapeService.add(shape);
        return ResponseEntity.status(HttpStatus.CREATED).body(shapeMapper.mapToDto(shape));
    }


    @GetMapping
    public ResponseEntity<List<ShapeDto>> getShapesWithSpecificParameters(@RequestParam Map<String, String> parameters) {
        List<? extends Shape> shapesBySpecificParameters = shapeService.getShapesBySpecificParameters(parameters);
        List<ShapeDto> shapesDto = shapesBySpecificParameters.stream()
                .map(shapeMapper::mapToDto)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(shapesDto);
    }
}
