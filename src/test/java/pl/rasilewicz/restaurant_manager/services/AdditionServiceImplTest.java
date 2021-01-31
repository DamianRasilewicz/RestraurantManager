package pl.rasilewicz.restaurant_manager.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.rasilewicz.restaurant_manager.entities.Addition;
import pl.rasilewicz.restaurant_manager.repositories.AdditionRepository;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdditionServiceImplTest {

    @Autowired
    private AdditionServiceImpl additionService;

    @Autowired
    private AdditionRepository repository;

    @Test
    @Transactional
    void findAdditionsByDescription() {
        //given
//        Addition addition1 = new Addition();
//        addition1.setName("Salami");
//        addition1.setDescription("Pizza");
//        addition1.setPrice(5);
//        repository.save(addition1);
//
//        Addition addition2 = new Addition();
//        addition2.setName("Szynka");
//        addition2.setDescription("Pizza");
//        addition2.setPrice(5);
//        repository.save(addition2);
        //when
        List<Addition> additionTestList = additionService.findAdditionsByDescription("Pizza");

        //then
        assertThat(additionTestList).isNotNull();
        assertThat(additionTestList.size()).isNotNull();
    }

    @Test
    void findAdditionById() {
        //given

        //when
        Addition testAddition = additionService.findAdditionById(1);

        //then
        assertThat(testAddition).isNotNull();
        assertThat(testAddition.getId()).isEqualTo(1);

    }

    @Test
    void findAdditionByName() {
        //given

        //when
        Addition testAddition = additionService.findAdditionByName("Salami");

        //then
        assertThat(testAddition).isNotNull();
        assertThat(testAddition.getName()).isEqualTo("Salami");
    }
}