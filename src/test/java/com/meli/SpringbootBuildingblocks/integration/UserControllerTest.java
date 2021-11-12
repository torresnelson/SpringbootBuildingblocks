package com.meli.SpringbootBuildingblocks.integration;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

  private static final String USER_ENDPOINT = "/users";

  private static final String USER_ENDPOINT_BY_ID = "/users/{id}";

  private static final String USER_ENDPOINT_BY_USERNAME = "/users/by-username/{username}";

  private static final Integer ENTRY_COUNT = 100;

  private static final String RESPONSE_LENGTH = "$.length()";

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

  @SneakyThrows
  @Test
  void listAllUserTest() {
    for (int i = 0; i < ENTRY_COUNT; i++) {
      this.mockMvc.perform(post(USER_ENDPOINT)
          .contentType(MediaType.APPLICATION_JSON)
          .content(mapper.writeValueAsString(UserTestUtils.generateUser())));
    }
    this.mockMvc.perform(get(USER_ENDPOINT)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath(RESPONSE_LENGTH).value(ENTRY_COUNT));
  }

}
