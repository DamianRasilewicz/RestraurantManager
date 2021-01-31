package pl.rasilewicz.restaurant_manager.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.rasilewicz.restaurant_manager.entities.Role;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleServiceImplTest {

    @Autowired
    private RoleServiceImpl roleService;

    @Test
    void findRoleById() {
        //given

        //when
        Role testRole = roleService.findRoleById(1);
        //then
        assertThat(testRole).isNotNull();
        assertThat(testRole.getName()).isEqualTo("ADMIN");

    }
}