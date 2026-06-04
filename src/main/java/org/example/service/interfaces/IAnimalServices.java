package org.example.service.interfaces;

import org.example.entity.Animals;

import java.util.ArrayList;

public interface IAnimalServices {

        void saveAnimal(Animals animal);
        ArrayList<Animals> listAll();
        boolean deleteAnimal(int id);
}
