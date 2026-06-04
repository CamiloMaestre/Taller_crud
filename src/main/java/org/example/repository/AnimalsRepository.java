package org.example.repository;

import org.example.entity.Animals;

import java.util.ArrayList;

public class AnimalsRepository {

    private ArrayList<Animals> list = new ArrayList<>();
    private int nextId = 1;

    //save animal
    public void save(Animals a){
        a.setId(nextId++);
        list.add(a);
    }

    //list all animals
    public ArrayList<Animals> listAll() {
        return list;
    }

    //delete animal
    public boolean delete(int id){
        return list.removeIf(a -> a.getId() == id);
    }

}
