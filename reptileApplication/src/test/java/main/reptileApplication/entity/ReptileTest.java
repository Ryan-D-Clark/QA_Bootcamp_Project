package main.reptileApplication.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"classpath:reptile-schema.sql", "classpath:reptile-data.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ReptileTest {

    @Test
    public void classEqualsTest(){
        EqualsVerifier.forClass(Reptile.class).usingGetClass().verify();
    }

    @Test
    public void setAndGetEmptyTest(){
        Reptile reptile = new Reptile();

        reptile.setId(1L);
        reptile.setName("Charmette");
        reptile.setSpecie("Chameleon");
        reptile.setAge(4);
        reptile.setGender("Female");
        reptile.setInsectivore(true);
        reptile.setArboreal(true);

        assertNotNull(reptile.getId());
        assertNotNull(reptile.getName());
        assertNotNull(reptile.getSpecie());
        assertNotNull(reptile.getAge());
        assertNotNull(reptile.getGender());
        assertNotNull(reptile.isArboreal());
        assertNotNull(reptile.isInsectivore());
    }

    @Test
    public void setAndGetTest(){
        Reptile reptile = new Reptile(1L, "Charmette", "Chameleon",
                4, "Female",true, true);



        assertNotNull(reptile.getId());
        assertNotNull(reptile.getName());
        assertNotNull(reptile.getSpecie());
        assertNotNull(reptile.getAge());
        assertNotNull(reptile.getGender());
        assertNotNull(reptile.isArboreal());
        assertNotNull(reptile.isInsectivore());
    }

    @Test
    public void setAndGetWithoutIdTest(){
        Reptile reptile = new Reptile( "Charmette", "Chameleon",
                4, "Female",true, true);

        assertNotNull(reptile.getName());
        assertNotNull(reptile.getSpecie());
        assertNotNull(reptile.getAge());
        assertNotNull(reptile.getGender());
        assertNotNull(reptile.isArboreal());
        assertNotNull(reptile.isInsectivore());
    }

}
