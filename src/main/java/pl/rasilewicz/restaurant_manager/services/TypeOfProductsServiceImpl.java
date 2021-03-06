package pl.rasilewicz.restaurant_manager.services;

import org.springframework.stereotype.Service;
import pl.rasilewicz.restaurant_manager.entities.TypeOfProduct;
import pl.rasilewicz.restaurant_manager.repositories.TypeOfProductRepository;
import java.util.List;

@Service
public class TypeOfProductsServiceImpl implements TypeOfProductsService{

    private final TypeOfProductRepository typeOfProductRepository;

    public TypeOfProductsServiceImpl(TypeOfProductRepository typeOfProductRepository) {
        this.typeOfProductRepository = typeOfProductRepository;
    }

    @Override
    public List<TypeOfProduct> findAllTypesOfProduct() {
        return typeOfProductRepository.findAllTypesOfProduct();
    }

    @Override
    public TypeOfProduct findTypeOfProductById(Integer id) {
        return typeOfProductRepository.findTypeOfProductById(id);
    }

    @Override
    public void update(String name, Integer id) {
        typeOfProductRepository.update(name, id);
    }

    @Override
    public void deleteTypeOfProductById(Integer id) {
        typeOfProductRepository.deleteById(id);
    }

    @Override
    public void save(TypeOfProduct typeOfProduct) {
        typeOfProductRepository.save(typeOfProduct);
    }
}
