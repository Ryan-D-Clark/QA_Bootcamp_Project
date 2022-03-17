package main.reptileApplication.service;

import main.reptileApplication.entity.Reptile;
import main.reptileApplication.repo.ReptileRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"classpath:reptile-schema.sql", "classpath:reptile-data.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ReptileServiceUnitTest {

    @Autowired
    private ReptileService service;

    @MockBean
    private ReptileRepo repo;

    @Test
    public void createReptileTest(){
        Reptile newReptile = new Reptile("Charm","Chameleon",2,
                "Male",true, true);
        Reptile existingReptile = new Reptile(1, "Charm", "Chameleon",
                2, "Male", true, true);
        Mockito.when(this.repo.save(newReptile)).thenReturn(existingReptile);
        assertEquals(existingReptile, this.service.create(newReptile));
        Mockito.verify(this.repo, Mockito.times(1)).save(newReptile);
    }

    @Test
    public void readAllTest(){
        Reptile existingReptile = new Reptile("Charm","Chameleon",2,
                "Male",true, true);
        List<Reptile> reptileList = List.of(existingReptile);
        Mockito.when(this.repo.findAll()).thenReturn(reptileList);
        assertEquals(reptileList, this.service.readAll());
        Mockito.verify(this.repo, Mockito.times(1)).findAll();
    }

    @Test
    public void readByIdTest(){
        Optional<Reptile> optionalExistingReptile = Optional.of(new Reptile(1,"Charm","Chameleon",2,
                "Male",true, true));
        Reptile expectedExistingReptile = new Reptile(1,"Charm","Chameleon",2,
                "Male",true, true);

        Mockito.when(this.repo.findById(1L)).thenReturn(optionalExistingReptile);

        assertEquals(null, this.service.readById(100L));
        assertEquals(expectedExistingReptile, this.service.readById(1L));
        Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
    }

    @Test
    public void updateTest(){
        Optional<Reptile> optionalExistingReptile = Optional.of(new Reptile(1,"Charm","Chameleon",2,
                "Male",true, true));


        Reptile updatedReptile = new Reptile(1,"Charmette","Chameleon",4,
                "Female",true, true);

        Mockito.when(this.repo.findById(1L)).thenReturn(optionalExistingReptile);
        Mockito.when(this.repo.saveAndFlush(updatedReptile)).thenReturn(updatedReptile);

        assertEquals(updatedReptile, this.service.update(1L,updatedReptile));

        assertEquals(null, this.service.update(100L, updatedReptile));

        Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
        Mockito.verify(this.repo, Mockito.times(1)).findById(100L);
        Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush((updatedReptile));
    }

    @Test
    public void deleteTrueTest(){
        Mockito.when(this.repo.existsById(1L)).thenReturn(false);

        assertEquals(true, this.service.delete(1L));

        Mockito.verify(this.repo, Mockito.times(1)).deleteById(1L);
        Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
    }
    @Test
    public void deleteFalseTest(){
        Mockito.when(this.repo.existsById(1L)).thenReturn(true);

        assertEquals(false, this.service.delete(1L));

        Mockito.verify(this.repo, Mockito.times(1)).deleteById(1L);
        Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
    }


}