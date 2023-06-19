package pl.kurs.shape_api.shapeFactory;

import pl.kurs.shape_api.models.Shape;

import java.util.Map;

public interface ShapeCreator {

     String getType();
     Shape create(Map<String, Object> parameters);
     Shape update(Map<String, Object> parameters, Shape shape);


     default Double getDoubleParameters(String name, Map<String, Object> parameters){
         try {
             return (Double) parameters.get(name);

         }catch (ClassCastException e){
             throw new ClassCastException("Tylko liczby zmiennoprzecinkowe");
         }
     }
}
