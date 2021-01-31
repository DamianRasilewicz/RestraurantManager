package pl.rasilewicz.restaurant_manager.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.rasilewicz.restaurant_manager.entities.TypeOfProduct;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TypeOfProductsServiceImplTest {

    @Autowired
    private TypeOfProductsServiceImpl typeOfProductsService;

    @Test
    void findAllTypesOfProduct() {
        //given

        //when
        List<TypeOfProduct> testTypeOfProductList = typeOfProductsService.findAllTypesOfProduct();
        //then
        assertThat(testTypeOfProductList).isNotNull();
        assertThat(testTypeOfProductList.get(0).getName()).isEqualTo("Pizza");
    }
}