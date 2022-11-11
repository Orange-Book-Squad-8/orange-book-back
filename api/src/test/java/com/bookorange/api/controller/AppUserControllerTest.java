package com.bookorange.api.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.bookorange.api.domain.AppUser;
import com.bookorange.api.domain.Lesson;
import com.bookorange.api.domain.Role;
import com.bookorange.api.enumerator.ContentType;
import com.bookorange.api.enumerator.StackCategories;
import com.bookorange.api.repository.AppUserRepository;
import com.bookorange.api.repository.LessonRepository;
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

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppUserRepository repository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private LessonRepository lessonRepository;




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

    public Optional<AppUser> returnOptionalAppUser(){
        return Optional.ofNullable(AppUser.builder()
                .email("email24@gmail.com")
                .id(1L)
                .username("string12")
                .build());

    }

    public Optional<Lesson> returnOptionalLesson(){
        return Optional.ofNullable(Lesson.builder()
                        .author("Fulano")
                        .contentType(ContentType.ARTICLE)
                        .description("Artigo bla bla bla")
                        .durationInMinutes(10)
                        .link("https://www.devmedia.com.br/conhecendo-a-interface-map-do-java/37463")
                        .title("Spring Boot Test")
                        .topic("Integration test")
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
        Mockito.when(repository.findByUsername(Mockito.anyString())).thenReturn(USER);
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
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(USER);

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

        Mockito.when(repository.findByUsername("user")).thenReturn(USER);

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

        Mockito.when(repository.findByUsername("user")).thenReturn(USER);

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

        Mockito.when(repository.findByUsername("user")).thenReturn(USER);

        this.mockMvc.perform(
                        post("/users/login")
                                .content(LOGIN_INVALID_USERNAME)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(403));
    }

    @Test
    public void setWatchedWithUserIdAndLessonIdValid_RETURNSIS2XX() throws Exception {

        Mockito.when(lessonRepository.findById(1L)).thenReturn(returnOptionalLesson());
        Mockito.when(repository.findById(1L)).thenReturn(returnOptionalAppUser());


        this.mockMvc.perform(
                        put("/watched")
                                .content(LOGIN_INVALID_USERNAME)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }



}
