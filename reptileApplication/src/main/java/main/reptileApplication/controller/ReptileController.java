package main.reptileApplication.controller;


import main.reptileApplication.entity.Reptile;
import main.reptileApplication.service.ReptileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReptileController {

    private ReptileService service;

    @Autowired
    public ReptileController(ReptileService service){
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Reptile> createReptile(@RequestBody Reptile reptile){
        return new ResponseEntity<>(this.service.create(reptile), HttpStatus.CREATED);

    }

    @GetMapping("/readAll")
    public ResponseEntity<List<Reptile>> readAll(){
        return new ResponseEntity<>(this.service.readAll(), HttpStatus.OK);
    }

    @GetMapping("/readById/{id}")
    public ResponseEntity<Reptile> readById(@PathVariable long id){
        return new ResponseEntity<>(this.service.readById(id), HttpStatus.OK);

    }
    @GetMapping("/readByName/{name}")
    public ResponseEntity<List<Reptile>> readByName(@PathVariable String name){
        return new ResponseEntity<>(this.service.readByName(name), HttpStatus.OK);
    }

    @GetMapping("/readBySpecie/{specie}")
    public ResponseEntity<List<Reptile>> readBySpecie(@PathVariable String specie){
        return new ResponseEntity<>(this.service.readBySpecie(specie), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateReptile(@PathVariable long id, @RequestBody Reptile reptile){
        return new ResponseEntity<>(this.service.update(id, reptile), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteReptile(@PathVariable long id){
        try {
            this.service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
