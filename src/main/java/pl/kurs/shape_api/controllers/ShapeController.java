package pl.kurs.shape_api.controllers;

import org.springframework.format.annotation.DateTimeFormat;
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
import java.util.Optional;
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
        shape.setArea(shape.calculateArea());
        shape.setPerimeter(shape.calculatePerimeter());
        shape.setType(shape.getType());
        shapeService.add(shape);
        return ResponseEntity.status(HttpStatus.CREATED).body(shapeMapper.mapToDto(shape));
    }


    @GetMapping
    public ResponseEntity<List<ShapeDto>> getShapesWithSpecificParameters(@RequestParam(value = "createdBy", defaultValue = "Mateusz") List<String> name,
                                                                          @RequestParam(value = "areaFrom", defaultValue = "0") double areaFrom,
                                                                          @RequestParam(value = "areaTo", defaultValue = "9999") double areaTo,
                                                                          @RequestParam(value = "perimeterFrom", defaultValue = "0") double perimeterFrom,
                                                                          @RequestParam(value = "perimeterTo", defaultValue = "9999") double perimeterTo,
                                                                          @RequestParam(value = "createdAtFrom", defaultValue = "2023-01-04") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdAtFrom,
                                                                          @RequestParam(value = "createdAtTo", defaultValue = "2023-05-06") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdAtTo,
                                                                          @RequestParam(value = "shapeType", defaultValue = "CIRCLE,SQUARE,RECTANGLE") List<String> type,
                                                                          @RequestParam(value = "radiusFrom") Optional<Double> radiusFrom,
                                                                          @RequestParam(value = "radiusTo") Optional<Double> radiusTo,
                                                                          @RequestParam(value = "sideLengthFrom") Optional<Double> sideLengthFrom,
                                                                          @RequestParam(value = "sideLengthTo") Optional<Double> sideLengthTo,
                                                                          @RequestParam(value = "widthFrom") Optional<Double> widthFrom,
                                                                          @RequestParam(value = "widthTo") Optional<Double> widthTo,
                                                                          @RequestParam(value = "heightFrom") Optional<Double> heightFrom,
                                                                          @RequestParam(value = "heightTo") Optional<Double> heightTo) {


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
                .filter(x -> name.contains(x.getCreatedBy()))
                .filter(x -> x.getArea() >= areaFrom && x.getArea() <= areaTo)
                .filter(x -> x.getPerimeter() >= perimeterFrom && x.getPerimeter() <= perimeterTo)
                .filter(x -> (x.getCreatedAt().isEqual(createdAtFrom) || x.getCreatedAt().isAfter(createdAtFrom)) && (x.getCreatedAt().isBefore(createdAtTo) || x.getCreatedAt().isEqual(createdAtTo)))
                .filter(x -> type.contains(x.getType().toString()))
                .map(shapeMapper::mapToDto)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(shapesDto);
    }


    private List<Shape> getShapesWithRadius(Optional<Double> radiusFrom, Optional<Double> radiusTo) {
        List<Shape> shapes = null;
        if (radiusFrom.isPresent()) {
            shapes = shapeService.getAll().stream()
                    .filter(x -> x instanceof Circle)
                    .filter(x -> ((Circle) x).getRadius() >= radiusFrom.get())
                    .collect(Collectors.toList());
        }
        if (radiusTo.isPresent()) {
            shapes = shapeService.getAll().stream()
                    .filter(x -> x instanceof Circle)
                    .filter(x -> ((Circle) x).getRadius() >= radiusTo.get())
                    .collect(Collectors.toList());
        }
        return shapes;
    }

    private List<Shape> getShapesWithSideLength(Optional<Double> sideLengthFrom, Optional<Double> sideLengthTo) {
        List<Shape> shapes = null;
        if (sideLengthFrom.isPresent()) {
            shapes = shapeService.getAll().stream()
                    .filter(x -> x instanceof Square)
                    .filter(x -> ((Square) x).getSideLength() >= sideLengthFrom.get())
                    .collect(Collectors.toList());
        }
        if (sideLengthTo.isPresent()) {
            shapes = shapeService.getAll().stream()
                    .filter(x -> x instanceof Square)
                    .filter(x -> ((Square) x).getSideLength() >= sideLengthTo.get())
                    .collect(Collectors.toList());
        }
        return shapes;
    }

    private List<Shape> getShapesWithWidth(Optional<Double> widthFrom, Optional<Double> widthTo) {
        List<Shape> shapes = null;
        if (widthFrom.isPresent()) {
            shapes = shapeService.getAll().stream()
                    .filter(x -> x instanceof Rectangle)
                    .filter(x -> ((Rectangle) x).getWidth() >= widthFrom.get())
                    .collect(Collectors.toList());
        }
        if (widthTo.isPresent()) {
            shapes = shapeService.getAll().stream()
                    .filter(x -> x instanceof Rectangle)
                    .filter(x -> ((Rectangle) x).getWidth() >= widthTo.get())
                    .collect(Collectors.toList());
        }
        return shapes;
    }

    private List<Shape> getShapesWithHeight(Optional<Double> heightFrom, Optional<Double> heightTo) {
        List<Shape> shapes = null;
        if (heightFrom.isPresent()) {
            shapes = shapeService.getAll().stream()
                    .filter(x -> x instanceof Rectangle)
                    .filter(x -> ((Rectangle) x).getHeight() >= heightFrom.get())
                    .collect(Collectors.toList());
        }
        if (heightTo.isPresent()) {
            shapes = shapeService.getAll().stream()
                    .filter(x -> x instanceof Rectangle)
                    .filter(x -> ((Rectangle) x).getHeight() >= heightTo.get())
                    .collect(Collectors.toList());
        }
        return shapes;
    }


}
