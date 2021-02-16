package pl.rasilewicz.restaurant_manager.services;

import pl.rasilewicz.restaurant_manager.entities.TypeOfProduct;
import java.util.List;

public interface TypeOfProductsService {

    List<TypeOfProduct> findAllTypesOfProduct();

    TypeOfProduct findTypeOfProductById(Integer id);
}
