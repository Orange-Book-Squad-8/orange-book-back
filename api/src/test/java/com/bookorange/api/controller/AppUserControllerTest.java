package com.bookorange.api.controller;


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
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppUserControllerTest {

    private static final Long ID = 1L;
    private static final String LOGIN = """
            {
              "password": "password",
              "username": "user"
            }
            """;
    private static final String LOGIN_INVALID_PASSWORD = """
            {
              "password": "password1",
              "username": "user"
            }
            """;
    private static final String LOGIN_INVALID_USERNAME = """
            {
              "password": "password",
              "username": "user1"
            }
            """;
    private static final String BODY = """
            {
              "email": "email@gmail.com",
              "password": "password",
              "role": "user",
              "stackCategories": [
                "BACK_END"
              ],
              "username": "user"
            }
            """;
    private static final Role ROLE = Role.builder()
            .id(1L)
            .name("admin")
            .build();
    private static final AppUser USER = AppUser.builder()
            .email("email24@gmail.com")
            .password("password")
            .role(ROLE)
            .stackCategories(List.of(StackCategories.BACK_END))
            .username("user")
            .build();
    private static final String BODY_UPDATE = """
            {
              "email": "emailnovo@email.com",
              "id": 1,
              "username": "usernovo"
            }
            """;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AppUserRepository repository;
    @MockBean
    private RoleRepository roleRepository;

    public Optional<AppUser> returnOptionalAppUser() {
        return Optional.ofNullable(AppUser.builder()
                .email("email24@gmail.com")
                .id(1L)
                .username("string12")
                .build());

    }


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
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.ofNullable(USER));

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
        Mockito.when(repository.findByUsername(Mockito.anyString())).thenReturn(Optional.ofNullable(USER));
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(null);

        this.mockMvc.perform(
                        post("/users/create")
                                .content(BODY)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(403));

    }

    // UPDATE METHOD
    @Test
    public void whenUpdateAppUser_ReturnsIsSuccessful() throws Exception {
        Mockito.when(roleRepository.findByName(Mockito.anyString())).thenReturn(ROLE);
        Mockito.when(repository.save(Mockito.any())).thenReturn(USER);


        Mockito.when(repository.findById(ID)).thenReturn(returnOptionalAppUser());
        Mockito.when(repository.findByUsername(Mockito.anyString())).thenReturn(null);
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(null);

        this.mockMvc.perform(
                        put("/users/edit")
                                .content(BODY_UPDATE)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void whenUpdateAppUser_ReturnsIsNotSuccessful() throws Exception {
        Mockito.when(roleRepository.findByName(Mockito.anyString())).thenReturn(ROLE);
        Mockito.when(repository.save(Mockito.any())).thenReturn(USER);


        Mockito.when(repository.findById(ID)).thenReturn(returnOptionalAppUser());
        Mockito.when(repository.findByUsername(Mockito.anyString())).thenReturn(null);
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(null);

        this.mockMvc.perform(
                        put("/users/edit")
                                .content("")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateAppUserWithUserNameDuplicated() throws Exception {
        Mockito.when(roleRepository.findByName(Mockito.anyString())).thenReturn(ROLE);
        Mockito.when(repository.save(Mockito.any())).thenReturn(USER);


        Mockito.when(repository.findById(ID)).thenReturn(returnOptionalAppUser());
        Mockito.when(repository.findByUsername(Mockito.anyString())).thenReturn(Optional.ofNullable(USER));
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(null);

        this.mockMvc.perform(
                        put("/users/edit")
                                .content(BODY_UPDATE)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void updateAppUserWithEmailDuplicated() throws Exception {
        Mockito.when(roleRepository.findByName(Mockito.anyString())).thenReturn(ROLE);
        Mockito.when(repository.save(Mockito.any())).thenReturn(USER);


        Mockito.when(repository.findById(ID)).thenReturn(returnOptionalAppUser());
        Mockito.when(repository.findByUsername(Mockito.anyString())).thenReturn(null);
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.ofNullable(USER));

        this.mockMvc.perform(
                        put("/users/edit")
                                .content(BODY_UPDATE)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    //LOGIN METHOD

    @Test
    public void loginWhitUserNameAndPassword_RETURNSIS2XX() throws Exception {

        Mockito.when(roleRepository.findByName(Mockito.anyString())).thenReturn(ROLE);
        Mockito.when(repository.save(Mockito.any())).thenReturn(USER);

        Mockito.when(repository.findByUsername("user")).thenReturn(Optional.ofNullable(USER));

        this.mockMvc.perform(
                        post("/users/login")
                                .content(LOGIN)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void loginWhitUserNameAndPasswordINVALID_RETURNSIS4XX() throws Exception {

        Mockito.when(roleRepository.findByName(Mockito.anyString())).thenReturn(ROLE);
        Mockito.when(repository.save(Mockito.any())).thenReturn(USER);

        Mockito.when(repository.findByUsername("user")).thenReturn(Optional.ofNullable(USER));

        this.mockMvc.perform(
                        post("/users/login")
                                .content(LOGIN_INVALID_PASSWORD)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(403));
    }

    @Test
    public void loginWhitUserNameNVALIDAndPassword_RETURNSIS4XX() throws Exception {

        Mockito.when(roleRepository.findByName(Mockito.anyString())).thenReturn(ROLE);
        Mockito.when(repository.save(Mockito.any())).thenReturn(USER);

        Mockito.when(repository.findByUsername("user")).thenReturn(Optional.ofNullable(USER));

        this.mockMvc.perform(
                        post("/users/login")
                                .content(LOGIN_INVALID_USERNAME)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(403));
    }

}
