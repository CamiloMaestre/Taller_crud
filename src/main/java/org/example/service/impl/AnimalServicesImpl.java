package org.example.service.impl;

import org.example.entity.Animals;
import org.example.repository.AnimalsRepository;
import org.example.service.interfaces.IAnimalServices;

import java.util.ArrayList;

public class AnimalServicesImpl implements IAnimalServices {

    private final AnimalsRepository animalsRepository = new AnimalsRepository();

    @Override
    public void saveAnimal(Animals animal) {
        if (animal.getAge() < 0 || animal.getAge() > 500) {
            System.out.println("La edad debe estar en un rango aceptable");
            return;
        }
        animalsRepository.save(animal);
    }

    @Override
    public ArrayList<Animals> listAll() {
        return animalsRepository.listAll();
    }


    @Override
    public boolean deleteAnimal(int id) {
        return animalsRepository.delete(id);
    }
}
