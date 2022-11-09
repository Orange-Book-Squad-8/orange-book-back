package com.bookorange.api.service.implementation;

import com.bookorange.api.domain.Role;
import com.bookorange.api.dto.roleDto.RoleCreateDTO;
import com.bookorange.api.dto.roleDto.RoleDTO;
import com.bookorange.api.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class RoleServiceImpTest {

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    RoleServiceImp roleServiceImp;

    @Test
    void create() {
        RoleCreateDTO roleCreateDto = new RoleCreateDTO("name");
        roleServiceImp.create(roleCreateDto);
        ArgumentCaptor<Role> argumentCaptor = ArgumentCaptor.forClass(Role.class);

        Mockito.verify(roleRepository).save(argumentCaptor.capture());
        assertEquals(roleCreateDto.getRoleName(), argumentCaptor.getValue().getName());
    }

    @Test
    void findByName() {
        roleServiceImp.findByName("roleName");
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        Mockito.verify(roleRepository).findByName(argumentCaptor.capture());

        assertEquals("roleName", argumentCaptor.getValue());
    }

    @Test
    void findAll() {
        roleServiceImp.findAll();
        Mockito.verify(roleRepository, Mockito.times(1)).findAll();
    }

    @Test
    void update() {
        RoleDTO roleDto = new RoleDTO(1L, "roleName");

        Mockito.when(roleRepository.findById(1L)).thenReturn(Optional.of(new Role(1L, "oldName")));

        roleServiceImp.update(roleDto);
        ArgumentCaptor<Role> argumentCaptor = ArgumentCaptor.forClass(Role.class);

        Mockito.verify(roleRepository).save(argumentCaptor.capture());

        assertEquals(1L, argumentCaptor.getValue().getId());
        assertEquals("roleName", argumentCaptor.getValue().getName());
    }

    @Test
    void updateShouldThrowWhenRoleDoesNotExist() {
        RoleDTO roleDto = new RoleDTO(1L, "roleName");

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> roleServiceImp.update(roleDto));

        assertEquals("Role not found", runtimeException.getMessage());
    }

    @Test
    void delete() {
        roleServiceImp.delete(1L);
        Mockito.verify(roleRepository, Mockito.times(1)).deleteById(1L);
    }
}