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
import java.time.LocalDate;

import java.util.List;
import java.util.Map;
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
        String createdBy = parameters.getOrDefault("createdBy", "Mateusz");
        double areaFrom = Double.parseDouble(parameters.getOrDefault("areaFrom", "0"));
        double areaTo = Double.parseDouble(parameters.getOrDefault("areaTo", "500"));
        double perimeterFrom = Double.parseDouble(parameters.getOrDefault("perimeterFrom", "0"));
        double perimeterTo = Double.parseDouble(parameters.getOrDefault("perimeterTo", "500"));
        LocalDate createdAtFrom = LocalDate.parse(parameters.getOrDefault("createdAtFrom", "2023-01-01"));
        LocalDate createdAtTo = LocalDate.parse(parameters.getOrDefault("createdAtTo", "2024-01-01"));
        String typeBy = parameters.getOrDefault("typeBy", "CIRCLE,SQUARE,RECTANGLE");
        String radiusFrom = parameters.get("radiusFrom");
        String radiusTo = parameters.get("radiusTo");
        String sideLengthFrom = parameters.get("sideLengthFrom");
        String sideLengthTo = parameters.get("sideLengthTo");
        String widthFrom = parameters.get("widthFrom");
        String widthTo = parameters.get("widthTo");
        String heightFrom = parameters.get("heightFrom");
        String heightTo = parameters.get("heightTo");


        List<Shape> shapes = null;
        List<ShapeDto> shapesDto;

        if (getShapesWithRadius(radiusFrom, radiusTo) != null) {
            shapes = getShapesWithRadius(radiusFrom, radiusTo);
        }
        if (getShapesWithSideLength(sideLengthFrom, sideLengthTo) != null) {
            shapes = getShapesWithSideLength(sideLengthFrom, sideLengthTo);
        }
        if (getShapesWithWidth(widthFrom, widthTo) != null) {
            shapes = getShapesWithWidth(widthFrom, widthTo);
        }
        if (getShapesWithHeight(heightFrom, heightTo) != null) {
            shapes = getShapesWithHeight(heightFrom, heightTo);
        }
        if (shapes == null) {
            shapes = shapeService.getAll();
        }
        shapesDto = shapes.stream()
                .filter(x -> x.getCreatedBy().contains(createdBy))
                .filter(x -> x.getArea() >= areaFrom && x.getArea() <= areaTo)
                .filter(x -> x.getPerimeter() >= perimeterFrom && x.getPerimeter() <= perimeterTo)
                .filter(x -> (x.getCreatedAt().isEqual(createdAtFrom) || x.getCreatedAt().isAfter(createdAtFrom)) && (x.getCreatedAt().isBefore(createdAtTo) || x.getCreatedAt().isEqual(createdAtTo)))
                .filter(x -> typeBy.contains(x.getType()))
                .map(shapeMapper::mapToDto)
                .collect(Collectors.toList());


        return ResponseEntity.status(HttpStatus.OK).body(shapesDto);
    }


    private List<Shape> getShapesWithRadius(String radiusFrom, String radiusTo) {
        List<Shape> shapes = null;
        if (radiusFrom != null) {
            shapes = shapeService.getAll().stream()
                    .filter(x -> x instanceof Circle)
                    .filter(x -> ((Circle) x).getRadius() >= Double.parseDouble(radiusFrom))
                    .collect(Collectors.toList());
        }
        if (radiusTo != null) {
            shapes = shapeService.getAll().stream()
                    .filter(x -> x instanceof Circle)
                    .filter(x -> ((Circle) x).getRadius() <= Double.parseDouble(radiusTo))
                    .collect(Collectors.toList());
        }
        return shapes;
    }

    private List<Shape> getShapesWithSideLength(String sideLengthFrom, String sideLengthTo) {
        List<Shape> shapes = null;
        if (sideLengthFrom != null) {
            shapes = shapeService.getAll().stream()
                    .filter(x -> x instanceof Square)
                    .filter(x -> ((Square) x).getSideLength() >= Double.parseDouble(sideLengthFrom))
                    .collect(Collectors.toList());
        }
        if (sideLengthTo != null) {
            shapes = shapeService.getAll().stream()
                    .filter(x -> x instanceof Square)
                    .filter(x -> ((Square) x).getSideLength() <= Double.parseDouble(sideLengthTo))
                    .collect(Collectors.toList());
        }
        return shapes;
    }

    private List<Shape> getShapesWithWidth(String widthFrom, String widthTo) {
        List<Shape> shapes = null;
        if (widthFrom != null) {
            shapes = shapeService.getAll().stream()
                    .filter(x -> x instanceof Rectangle)
                    .filter(x -> ((Rectangle) x).getWidth() >= Double.parseDouble(widthFrom))
                    .collect(Collectors.toList());
        }
        if (widthTo != null) {
            shapes = shapeService.getAll().stream()
                    .filter(x -> x instanceof Rectangle)
                    .filter(x -> ((Rectangle) x).getWidth() <= Double.parseDouble(widthTo))
                    .collect(Collectors.toList());
        }
        return shapes;
    }

    private List<Shape> getShapesWithHeight(String heightFrom, String heightTo) {
        List<Shape> shapes = null;
        if (heightFrom != null) {
            shapes = shapeService.getAll().stream()
                    .filter(x -> x instanceof Rectangle)
                    .filter(x -> ((Rectangle) x).getHeight() >= Double.parseDouble(heightFrom))
                    .collect(Collectors.toList());
        }
        if (heightTo != null) {
            shapes = shapeService.getAll().stream()
                    .filter(x -> x instanceof Rectangle)
                    .filter(x -> ((Rectangle) x).getHeight() >= Double.parseDouble(heightTo))
                    .collect(Collectors.toList());
        }
        return shapes;
    }


}
