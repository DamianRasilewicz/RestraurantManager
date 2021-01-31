package pl.rasilewicz.restaurant_manager.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.rasilewicz.restaurant_manager.entities.Addition;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdditionServiceImplTest {

    @Autowired
    private AdditionServiceImpl additionService;

    @Test
    void findAdditionsByDescription() {
        //given

        //when
        List<Addition> additionTestList = additionService.findAdditionsByDescription("Pizza");

        //then
        assertThat(additionTestList).isNotNull();
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