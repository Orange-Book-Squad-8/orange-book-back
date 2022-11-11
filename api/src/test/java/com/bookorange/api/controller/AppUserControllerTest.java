package com.bookorange.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bookorange.api.domain.AppUser;
import com.bookorange.api.domain.Role;
import com.bookorange.api.enumerator.StackCategories;
import com.bookorange.api.repository.AppUserRepository;
import com.bookorange.api.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppUserControllerTest {

    private static final String BODY = """
                {
                  "email": "email24@gmail.com",
                  "password": "123214543",
                  "role": "admin",
                  "stackCategories": [
                    "BACK_END"
                  ],
                  "username": "string12"
                }
                """;
    private static final Role ROLE = Role.builder()
                                            .id(1L)
                                            .name("admin")
                                            .build();

    private static final AppUser USER = AppUser.builder()
                                                    .email("email24@gmail.com")
                                                    .password("123214543")
                                                    .role(ROLE)
                                                    .stackCategories(List.of(StackCategories.BACK_END))
                                                    .username("string12")
                                                    .build();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppUserRepository repository;

    @MockBean
    private RoleRepository roleRepository;


    //CREAT METHOD
    @Test
    public void whenCreatedAppUser_ReturnsIsSuccessful() throws Exception {
        Mockito.when(roleRepository.findByName(Mockito.anyString())).thenReturn(ROLE);
        Mockito.when(repository.save(Mockito.any())).thenReturn(USER);
        Mockito.when(repository.findByUsername(Mockito.anyString())).thenReturn(null);
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(null);

        this.mockMvc.perform(
                        post("/users/create")
                        .content(BODY)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void whenCreatedAppUser_ReturnsIsNotSuccessful() throws Exception {
        Mockito.when(roleRepository.findByName(Mockito.anyString())).thenReturn(ROLE);
        Mockito.when(repository.save(Mockito.any())).thenReturn(USER);
        Mockito.when(repository.findByUsername(Mockito.anyString())).thenReturn(null);
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(null);

        this.mockMvc.perform(
                        post("/users/create")
                                .content("")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());


    }

    @Test
    public void createAppUserWithEmailDuplicated_ReturnsForbiddenStatus() throws Exception {

        Mockito.when(roleRepository.findByName(Mockito.anyString())).thenReturn(ROLE);
        Mockito.when(repository.save(Mockito.any())).thenReturn(USER);
        Mockito.when(repository.findByUsername(Mockito.anyString())).thenReturn(null);
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(USER);

        this.mockMvc.perform(
                        post("/users/create")
                                .content(BODY)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(403));

    }

    @Test
    public void createAppUserWithUserNameDuplicated() throws Exception {

        Mockito.when(roleRepository.findByName(Mockito.anyString())).thenReturn(ROLE);
        Mockito.when(repository.save(Mockito.any())).thenReturn(USER);
        Mockito.when(repository.findByUsername(Mockito.anyString())).thenReturn(USER);
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(null);

        this.mockMvc.perform(
                        post("/users/create")
                                .content(BODY)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(403));

    }
}
