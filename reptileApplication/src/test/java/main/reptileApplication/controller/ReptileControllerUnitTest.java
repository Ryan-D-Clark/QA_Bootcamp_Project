package main.reptileApplication.controller;


import main.reptileApplication.entity.Reptile;
import main.reptileApplication.service.ReptileService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"classpath:reptile-schema.sql", "classpath:reptile-data.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ReptileControllerUnitTest {

    @Autowired
    ReptileController controller;

    @MockBean
    ReptileService service;

    @Test
    public void createReptileTest(){
        Reptile reptile = new Reptile("Charm","Chameleon",
                3,"Male",true,true);

        Mockito.when(this.service.create(reptile)).thenReturn(reptile);

        ResponseEntity<Reptile> responseEntity = new ResponseEntity<>(reptile, HttpStatus.CREATED);

        assertEquals(responseEntity, this.controller.createReptile(reptile));
        Mockito.verify(this.service, Mockito.times(1)).create(reptile);
    }


    @Test
    public void readAllTest(){
        Reptile reptile = new Reptile("Charm","Chameleon",
                3,"Male",true,true);
        List<Reptile> reptileList = List.of(reptile);

        Mockito.when(this.service.readAll()).thenReturn(reptileList);

        ResponseEntity<List<Reptile>> responseEntity = new ResponseEntity<>(reptileList, HttpStatus.OK);

        assertEquals(responseEntity, this.controller.readAll());
        Mockito.verify(this.service, Mockito.times(1)).readAll();
    }


    @Test
    public void readByIdTest(){
        Reptile reptile = new Reptile(1,"Charm","Chameleon",
                3,"Male",true,true);

        Mockito.when(this.service.readById(Mockito.anyLong())).thenReturn(reptile);

        ResponseEntity<Reptile> responseEntity = new ResponseEntity<>(reptile, HttpStatus.OK);

        assertEquals(responseEntity, this.controller.readById(1L));
        Mockito.verify(this.service, Mockito.times(1)).readById(1L);
    }


    @Test
    public void readByNameTest(){
        Reptile reptile = new Reptile("Charm","Chameleon",
                3,"Male",true,true);
        List<Reptile> reptileList = List.of(reptile);

        Mockito.when(this.service.readByName(Mockito.anyString())).thenReturn(reptileList);

        ResponseEntity<List<Reptile>> responseEntity = new ResponseEntity<>(reptileList, HttpStatus.OK);

        assertEquals(responseEntity, this.controller.readByName("Charm"));
        Mockito.verify(this.service, Mockito.times(1)).readByName("Charm");
    }


    @Test
    public void readBySpecieTest(){
        Reptile reptile = new Reptile("Charm","Chameleon",
                3,"Male",true,true);
        List<Reptile> reptileList = List.of(reptile);

        Mockito.when(this.service.readBySpecie(Mockito.anyString())).thenReturn(reptileList);

        ResponseEntity<List<Reptile>> responseEntity = new ResponseEntity<>(reptileList, HttpStatus.OK);

        assertEquals(responseEntity, this.controller.readBySpecie("Charm"));
        Mockito.verify(this.service, Mockito.times(1)).readBySpecie("Charm");
    }


    @Test
    public void updateTest(){
        Reptile updatedReptile = new Reptile(1,"Charm","Chameleon",
                3,"Male",true,true);
        Mockito.when(this.service.update(1L,updatedReptile)).thenReturn(updatedReptile);

        ResponseEntity<Reptile> responseEntity = new ResponseEntity<>(updatedReptile, HttpStatus.ACCEPTED);

        assertEquals(responseEntity, this.controller.updateReptile(1L,updatedReptile));
        Mockito.verify(this.service, Mockito.times(1)).update(1L,updatedReptile);
    }


    @Test
    public void deleteTrueTest(){
        Mockito.when(this.service.delete(1L)).thenReturn(true);

        ResponseEntity<Boolean> responseEntity = new ResponseEntity<>( HttpStatus.NO_CONTENT);

        assertEquals(responseEntity, this.controller.deleteReptile(1L));
        Mockito.verify(this.service, Mockito.times(1)).delete(1L);
    }


    @Test
    public void deleteFalseTest(){
        Mockito.when(this.service.delete(1L)).thenThrow(EntityNotFoundException.class);


        ResponseEntity<Boolean> responseEntity = new ResponseEntity<>( HttpStatus.NOT_FOUND);

        assertEquals(responseEntity, this.controller.deleteReptile(1L));
        Mockito.verify(this.service, Mockito.times(1)).delete(1L);
    }

}
