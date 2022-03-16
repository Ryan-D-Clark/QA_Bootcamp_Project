package main.reptileApplication.service;

import main.reptileApplication.entity.Reptile;
import main.reptileApplication.repo.ReptileRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReptileService implements ServiceMethods<Reptile>{

    private ReptileRepo repo;

    public ReptileService(ReptileRepo repo){
        this.repo = repo;
    }

    @Override
    public Reptile create(Reptile reptile){
        return this.repo.save(reptile);
    }

    @Override
    public List<Reptile> readAll(){
        return this.repo.findAll();
    }

    @Override
    public Reptile readById(long id){
        Optional<Reptile> getReptile = this.repo.findById(id);
        if (getReptile.isPresent()){
            return getReptile.get();
        }
        return null;
    }

    @Override
    public Reptile update(long id, Reptile reptile){
        Optional<Reptile> getExistingReptile = this.repo.findById(id);
        if (getExistingReptile.isPresent()){
            Reptile updatingReptile = getExistingReptile.get();
            updatingReptile.setName(reptile.getName());
            updatingReptile.setSpecie(reptile.getSpecie());
            updatingReptile.setAge(reptile.getAge());
            updatingReptile.setGender(reptile.getGender());
            updatingReptile.setInsectivore(reptile.isInsectivore());
            updatingReptile.setArboreal(reptile.isArboreal());
            return this.repo.saveAndFlush(updatingReptile);
        }
        return null;
    }
    @Override
    public boolean delete(long id){
        this.repo.deleteById(id);
        return  !this.repo.existsById(id);
    }
}
