package com.challenge.sebastian.orellana.controller;

import com.challenge.sebastian.orellana.dto.PhoneDTO;
import com.challenge.sebastian.orellana.dto.UserDTO;
import com.challenge.sebastian.orellana.entity.Phone;
import com.challenge.sebastian.orellana.entity.User;
import com.challenge.sebastian.orellana.service.impl.UserServiceImp;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = UserController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest {


    @MockBean
    private UserServiceImp userServiceImp;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(1)
    void whenRequestedGetListThenReturnOK() throws Exception {
        Mockito.when(userServiceImp.getAllUsers()).thenReturn(this.getMockUsers());
        mvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].name").value("Test 1"))
                .andExpect(jsonPath("$[0].email").value("test1@gmail.com"))
                .andExpect(jsonPath("$[0].phones.length()").value(2))
                .andExpect(jsonPath("$[0].active").value(true))
                .andExpect(jsonPath("$[1].name").value("Test 2"))
                .andExpect(jsonPath("$[1].email").value("test2@gmail.com"))
                .andExpect(jsonPath("$[1].phones.length()").value(2))
                .andExpect(jsonPath("$[1].active").value(true))
                .andExpect(jsonPath("$[2].name").value("Test 3"))
                .andExpect(jsonPath("$[2].email").value("test3@gmail.com"))
                .andExpect(jsonPath("$[2].phones.length()").value(0))
                .andExpect(jsonPath("$[2].active").value(true))
        ;
    }

    @Test
    @Order(2)
    void whenRequestedRetrieveByEmailThenReturnOK() throws Exception {
        Mockito.when(userServiceImp.getUser("test1@gmail.com")).thenReturn(this.getMockRetrieveUserByEmail(1L,
                "Test 1",
                "test1@gmail.com"));
        mvc.perform(get("/user/test1@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test 1"))
                .andExpect(jsonPath("$.email").value("test1@gmail.com"))
                .andExpect(jsonPath("$.phones.length()").value(2))
                .andExpect(jsonPath("$.active").value(true))
        ;

        Mockito.when(userServiceImp.getUser("test2@gmail.com")).thenReturn(this.getMockRetrieveUserByEmail(2L,
                "Test 2",
                "test2@gmail.com"));
        mvc.perform(get("/user/test2@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test 2"))
                .andExpect(jsonPath("$.email").value("test2@gmail.com"))
                .andExpect(jsonPath("$.phones.length()").value(2))
                .andExpect(jsonPath("$.active").value(true))
        ;
    }


    private UserDTO getMockRetrieveUserByEmail(Long id, String name, String email) {
        List<PhoneDTO> phones = new ArrayList<>();
        phones.add(PhoneDTO.builder().number(1111111).countryCode(1).cityCode(1).build());
        phones.add(PhoneDTO.builder().number(2222222).countryCode(1).cityCode(1).build());
        return UserDTO.builder()
                .name(name)
                .email(email)
                .phones(phones)
                .active(true)
                .build();
    }

    private List<User> getMockUsers() {
        List<User> users = new ArrayList<>();
        List<Phone> phones = getMockPhones();

        users.add(
                User.builder()
                        .id(1L)
                        .name("Test 1")
                        .email("test1@gmail.com")
                        .createAt(new Date())
                        .isActive(true)
                        .phones(phones)
                        .build()
        );

        users.add(
                User.builder()
                        .id(2L)
                        .name("Test 2")
                        .email("test2@gmail.com")
                        .createAt(new Date())
                        .isActive(true)
                        .phones(phones)
                        .build()
        );

        users.add(
                User.builder()
                        .id(3L)
                        .name("Test 3")
                        .email("test3@gmail.com")
                        .createAt(new Date())
                        .isActive(true)
                        .phones(new ArrayList<>())
                        .build()
        );

        return users;
    }

    private List<Phone> getMockPhones() {
        List<Phone> phones = new ArrayList<>();
        phones.add(Phone.builder().id(1L).number(1111111).countryCode(1).cityCode(1).build());
        phones.add(Phone.builder().id(2L).number(2222222).countryCode(1).cityCode(1).build());
        return phones;
    }
}