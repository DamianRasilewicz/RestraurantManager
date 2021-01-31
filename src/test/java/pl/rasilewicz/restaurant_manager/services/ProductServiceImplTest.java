package pl.rasilewicz.restaurant_manager.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.rasilewicz.restaurant_manager.entities.Product;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    void findProductsByType() {
        //given

        //when
        List<Product> testProductList = productService.findProductsByType(1);
        //then
        assertThat(testProductList).isNotNull();
        assertThat(testProductList.get(0).getName()).isEqualTo("Margheritta");
    }


    @Test
    void findProductById() {
        //given

        //when
        Product testProduct = productService.findProductById(1L);
        //then
        assertThat(testProduct).isNotNull();
        assertThat(testProduct.getName()).isEqualTo("Margheritta");
    }
}