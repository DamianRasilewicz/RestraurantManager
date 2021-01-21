package pl.rasilewicz.restaurant_manager.services;

import pl.rasilewicz.restaurant_manager.entities.Addition;

import java.util.List;

public interface AdditionService {

    List<Addition> findAdditionsByDescription(String description);

    Addition findAdditionById(Integer id);

    Addition findAdditionByName(String name);
}
