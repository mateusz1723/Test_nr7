package pl.kurs.shape_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.kurs.shape_api.models.Shape;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ShapeRepository extends JpaRepository<Shape, Long> {

    @Query("SELECT s FROM Square s WHERE s.sideLength >= :sideLength")
    List<Shape> findAllByTypeSquareGreaterThanSideLength(double sideLength);

//    List<Car> findAllByProducer(String producer);
//    List<Car> getAllByModel(String model);
//    List<Car> readAllByDisplacementCm3(int displacementCm3);
//    List<Car> queryAllByPowerHp(int powerHp);
//
//
////    List<Car> findAllByPowerHpGreaterThanEqualOrderByPowerHpDesc(int powerHp);
//
//    @Query("SELECT c FROM Car c WHERE c.powerHp >= :powerHp ORDER BY c.powerHp DESC")
//    List<Car> findAllByPowerHpGreaterThanEqualOrderByPowerHpDesc(int powerHp);
//
//    Optional<Car> findTopByDisplacementCm3(int displacementCm3);
//
//    @Transactional
//    int deleteAllByDisplacementCm3LessThanEqual(int displacementCm3);


//    Dodatkowo:
//Operatory logiczne - And(koniunkcja) oraz Or(alternatywa);
//Dopasowanie do wzorca – Like / NotLike / StartingWith  / EndingWith / Containing;
//Ograniczenia liczbowe – GreaterThan / GreaterThanEqual / LessThan / LessThanEqual /Between;
//Sortowanie – OrderBy…Asc / OrderBy…Desc;
//Null checki – IsNull  / IsNotNull (lub NotNull);
//Dopasowanie do zbioru – In;
//Negacja – Not;
//Warunki logiczne – True /  False;
}
