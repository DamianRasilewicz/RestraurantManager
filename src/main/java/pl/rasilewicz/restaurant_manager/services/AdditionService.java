package pl.rasilewicz.restaurant_manager.services;

import pl.rasilewicz.restaurant_manager.entities.Addition;
import pl.rasilewicz.restaurant_manager.entities.Ingredient;

import java.util.List;

public interface AdditionService {

    List<Addition> findAdditionsByDescription(String description);

    Addition findAdditionById(Integer id);

    Addition findAdditionByName(String name);

    List<Addition> findAllAdditions();

    void update(String name, String description, Double price, Integer id);

    void deleteAdditionById(Integer id);
}
