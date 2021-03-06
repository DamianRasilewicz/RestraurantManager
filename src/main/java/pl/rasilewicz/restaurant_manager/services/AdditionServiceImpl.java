package pl.rasilewicz.restaurant_manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.rasilewicz.restaurant_manager.entities.Addition;
import pl.rasilewicz.restaurant_manager.repositories.AdditionRepository;

import java.util.List;

@Service
public class AdditionServiceImpl implements AdditionService{

    private final AdditionRepository additionRepository;

    @Autowired
    public AdditionServiceImpl(AdditionRepository additionRepository) {
        this.additionRepository = additionRepository;
    }

    @Override
    public List<Addition> findAdditionsByDescription(String description) {
        return additionRepository.findAdditionsByDescription(description);
    }

    @Override
    public Addition findAdditionById(Integer id) {
        return additionRepository.findAdditionById(id);
    }

    @Override
    public Addition findAdditionByName(String name) {
        return additionRepository.findAdditionByName(name);
    }

    @Override
    public List<Addition> findAllAdditions() {
        return additionRepository.findAllAdditions();
    }

    @Override
    public void update(String name, String description, Double price, Integer id) {
        additionRepository.update(name, description, price, id);
    }

    @Override
    public void deleteAdditionById(Integer id) {
        additionRepository.deleteById(id);
    }
}
