package main.reptileApplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.reptileApplication.entity.Reptile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"classpath:reptile-schema.sql", "classpath:reptile-data.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ReptileControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void createTest() throws Exception {
        Reptile expectedReptile = new Reptile
                ("Charm","Chameleon",2,"Male",true,true);
        String expectedReptileAsJSON = this.mapper.writeValueAsString(expectedReptile);

        RequestBuilder requestBuilder = post("/api/create")
                .content(expectedReptileAsJSON)
                .contentType(MediaType.APPLICATION_JSON);

        Reptile expectedSavedReptile = new Reptile
                (2,"Charm","Chameleon",2,"Male",true,true);
        String expectedSavedReptileAsJSON = this.mapper.writeValueAsString(expectedSavedReptile);

        ResultMatcher checkStatus = status().isCreated();
        ResultMatcher checkBody = content().json(expectedSavedReptileAsJSON);
        this.mvc.perform(requestBuilder).andExpect(checkStatus).andExpect(checkBody);

    }

    @Test
    public void readAllTest() throws Exception {
        Reptile existingReptile = new Reptile(1,"Thorette", "Crested Gecko", 2, "Female", false, true);
        List<Reptile> reptileList = new ArrayList<>();
        reptileList.add(existingReptile);
        String reptileListAsJSON = this.mapper.writeValueAsString(reptileList);

        RequestBuilder requestBuilder = get("/api/readAll");

        ResultMatcher checkStatus = status().isOk();
        ResultMatcher checkBody = content().json(reptileListAsJSON);

        this.mvc.perform(requestBuilder).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    public void  readByIdTest() throws Exception {
        Reptile existingReptile = new Reptile(1,"Thorette", "Crested Gecko", 2, "Female", false, true);
        String existingReptileAsJSON = this.mapper.writeValueAsString(existingReptile);

        RequestBuilder requestBuilder = get("/api/readById/1");

        ResultMatcher checkStatus = status().isOk();
        ResultMatcher checkBody = content().json(existingReptileAsJSON);

        this.mvc.perform(requestBuilder).andExpect(checkStatus).andExpect(checkBody);

    }

    @Test
    public void updateTest() throws Exception {
        Reptile updatingExistingReptile = new Reptile(1,"Thorette","Crested Gecko",3,"Female",false,true);
        String updatingExistingReptileAsJSON = this.mapper.writeValueAsString(updatingExistingReptile);

        RequestBuilder requestBuilder = put("/api/update/1").content(updatingExistingReptileAsJSON).contentType(MediaType.APPLICATION_JSON);
        this.mvc.perform(requestBuilder).andExpect(status().isAccepted());

        RequestBuilder requestBuilderTwo = get("/api/readById/1");

        ResultMatcher checkStatus = status().isOk();
        ResultMatcher checkBody = content().json(updatingExistingReptileAsJSON);

        this.mvc.perform(requestBuilderTwo).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    public void deleteTest() throws Exception {
        RequestBuilder requestBuilder = delete("/api/delete/1");
        ResultMatcher checkStatus = status().isNoContent();
        this.mvc.perform(requestBuilder).andExpect(checkStatus);

        RequestBuilder requestBuilderTwo = delete("/api/delete/2");
        ResultMatcher checkStatusTwo = status().isNotFound();
        this.mvc.perform(requestBuilderTwo).andExpect(checkStatusTwo);
    }
}
