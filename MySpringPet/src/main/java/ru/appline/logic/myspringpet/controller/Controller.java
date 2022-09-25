package ru.appline.logic.myspringpet.controller;


import org.springframework.web.bind.annotation.*;
import ru.appline.logic.myspringpet.logic.Pet;
import ru.appline.logic.myspringpet.logic.PetModel;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class Controller {
    private static final PetModel petModel = PetModel.getInstance();
    private static final AtomicInteger newID = new AtomicInteger(1);

    @PostMapping(value="/createPet",consumes = "application/json", produces = "application/json")
    public String createPet(@RequestBody Pet pet) {
        int id = newID.getAndIncrement();
        petModel.add(pet, id);
        String result;
        if (id == 1){
            result = "Вы создали своего первого домашнего питомца!";
        } else {
            result = "Вы создали своего домашнего питомца!";
        }
        return result;
    }

    @GetMapping(value = "/getPet", consumes = "application/json", produces = "application/json")
    public Pet getPet(@RequestBody Map<String, Integer> id) {
        return petModel.getFromList(id.get("id"));
    }

    @DeleteMapping(value = "/deletePet", consumes = "application/json", produces = "application/json")
    public String deletePet(@RequestBody Map<String, Integer>id) {
        petModel.delete(id.get("id"));
        String result = "Вы удалили своего домашнего питомца :'(";
        return result;
    }

    @PutMapping(value = "/deletePet", consumes = "application/json", produces = "application/json")
    public String putPet(@RequestBody Pet pet, Map<String, Integer>id) {
        petModel.put(pet, id.get("id"));
        String result = "Вы обновили своего домашнего питомца!";
        return result;
    }
}
