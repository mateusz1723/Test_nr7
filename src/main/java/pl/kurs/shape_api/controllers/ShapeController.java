package pl.kurs.shape_api.controllers;

import org.hibernate.boot.spi.MetadataBuildingContext;
import org.hibernate.cfg.annotations.QueryBinder;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kurs.shape_api.commands.CreateShapeCommand;
import pl.kurs.shape_api.dto.ShapeDto;
import pl.kurs.shape_api.mapper.ShapeMapper;
import pl.kurs.shape_api.models.*;
import pl.kurs.shape_api.services.ShapeService;
import pl.kurs.shape_api.shapeAttribute.Attribute;
import pl.kurs.shape_api.shapeAttribute.ShapeAttribute;

import javax.management.AttributeValueExp;
import javax.management.Query;
import javax.management.QueryExp;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQuery;
import javax.validation.Valid;
import java.time.LocalDate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Validated
@RestController
@RequestMapping("/api/v1/shapes")
public class ShapeController {

    private final ShapeService shapeService;
    private final ShapeMapper shapeMapper;
    private final ShapeAttribute attribute;


    public ShapeController(ShapeService shapeService, ShapeMapper shapeMapper, ShapeAttribute attribute) {
        this.shapeService = shapeService;
        this.shapeMapper = shapeMapper;
        this.attribute = attribute;
    }

    @PostMapping
    public ResponseEntity<ShapeDto> createShape(@RequestBody @Valid CreateShapeCommand createShapeCommand) {
        Shape shape = shapeService.createShape(createShapeCommand);
        shapeService.add(shape);
        return ResponseEntity.status(HttpStatus.CREATED).body(shapeMapper.mapToDto(shape));
    }


    @GetMapping
    public ResponseEntity<List<ShapeDto>> getShapesWithSpecificParameters(@RequestParam Map<String, String> parameters) {
        String createdBy = parameters.getOrDefault("createdBy", "Mateusz");
        double areaFrom = Double.parseDouble(parameters.getOrDefault("areaFrom", "0"));
        double areaTo = Double.parseDouble(parameters.getOrDefault("areaTo", "500"));
        double perimeterFrom = Double.parseDouble(parameters.getOrDefault("perimeterFrom", "0"));
        double perimeterTo = Double.parseDouble(parameters.getOrDefault("perimeterTo", "500"));
        LocalDate createdAtFrom = LocalDate.parse(parameters.getOrDefault("createdAtFrom", "2023-01-01"));
        LocalDate createdAtTo = LocalDate.parse(parameters.getOrDefault("createdAtTo", "2024-01-01"));
        String typeBy = parameters.getOrDefault("typeBy", "CIRCLE,SQUARE,RECTANGLE");

        List<Shape> shapes = shapeService.getAll().stream()
                .filter(x -> x.getCreatedBy().equalsIgnoreCase(createdBy))
                .filter(x -> x.getArea() >= areaFrom && x.getArea() <= areaTo)
                .filter(x -> x.getPerimeter() >= perimeterFrom && x.getPerimeter() <= perimeterTo)
                .filter(x -> (x.getCreatedAt().isEqual(createdAtFrom) || x.getCreatedAt().isAfter(createdAtFrom)) && (x.getCreatedAt().isBefore(createdAtTo) || x.getCreatedAt().isEqual(createdAtTo)))
                .filter(x -> typeBy.toUpperCase().contains(x.getType()))
                .collect(Collectors.toList());

        List<ShapeDto> shapesDto = attribute.getAttributes(parameters, shapes).stream()
                .map(shapeMapper::mapToDto)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(shapesDto);
    }
}
