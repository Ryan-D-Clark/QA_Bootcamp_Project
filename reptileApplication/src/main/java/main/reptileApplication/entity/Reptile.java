package main.reptileApplication.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Reptile {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String specie;

    @Column
    private int age;

    @Column
    private String gender;

    @Column
    private boolean isInsectivore;

    @Column
    private boolean isArboreal;

    public Reptile(){

    }
    public Reptile(String name, String specie, int age, String gender, boolean isInsectivore, boolean isArboreal){
        this.name = name;
        this.specie = specie;
        this.age = age;
        this.gender = gender;
        this.isInsectivore = isInsectivore;
        this.isArboreal = isArboreal;
    }
    public Reptile(long id, String name, String specie, int age, String gender, boolean isInsectivore, boolean isArboreal){
        this.id = id;
        this.name = name;
        this.specie = specie;
        this.age = age;
        this.gender = gender;
        this.isInsectivore = isInsectivore;
        this.isArboreal = isArboreal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isInsectivore() {
        return isInsectivore;
    }

    public void setInsectivore(boolean insectivore) {
        isInsectivore = insectivore;
    }

    public boolean isArboreal() {
        return isArboreal;
    }

    public void setArboreal(boolean arboreal) {
        isArboreal = arboreal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reptile reptile = (Reptile) o;
        return age == reptile.age && isInsectivore == reptile.isInsectivore && isArboreal == reptile.isArboreal && Objects.equals(name, reptile.name) && Objects.equals(specie, reptile.specie) && Objects.equals(gender, reptile.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, specie, age, gender, isInsectivore, isArboreal);
    }
}
