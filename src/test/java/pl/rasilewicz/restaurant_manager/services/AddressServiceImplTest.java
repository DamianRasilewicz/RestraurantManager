package pl.rasilewicz.restaurant_manager.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.rasilewicz.restaurant_manager.entities.Address;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AddressServiceImplTest {

    @Autowired
    private AddressServiceImpl addressService;

    @Test
    void findAddressByPersonId() {
        //given

        //when
        Address testAddress = addressService.findAddressByPersonId(1L);

        //then
        assertThat(testAddress).isNotNull();
        assertThat(testAddress.getBuildingNumber()).isEqualTo("44/2");
    }
}