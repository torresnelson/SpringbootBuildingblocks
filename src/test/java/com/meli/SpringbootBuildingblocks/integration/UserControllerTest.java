package com.meli.SpringbootBuildingblocks.integration;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.SpringbootBuildingblocks.utils.UserTestUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

/**
 * ChronosControllerTest integration test suite.
 */
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserControllerTest {

  private static final String USER_ENDPOINT = "/udemy-course/api/users";

  private static final String USER_ENDPOINT_BY_ID = "/udemy-course/api/users/{id}";

  private static final String USER_ENDPOINT_BY_USERNAME = "/udemy-course/api/users/by-username/{username}";

  private static final Integer ENTRY_COUNT = 200;

  private static final String LENGTH = "$.length()";

  private static ObjectMapper mapper;

  @Autowired
  private MockMvc mockMvc;

  @BeforeAll
  public static void setUp() {
    mapper = new ObjectMapper();
  }


  @SneakyThrows
  @Test
  void createUserSuccessfullyTest() {
    String jsonPayload = mapper.writeValueAsString(UserTestUtils.generateUser());
    this.mockMvc.perform(post(USER_ENDPOINT)
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonPayload))
        .andExpect(status().isCreated());
  }
}
