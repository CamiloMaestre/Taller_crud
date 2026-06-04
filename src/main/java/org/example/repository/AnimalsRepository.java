package org.example.repository;

import org.example.entity.Animals;

import java.util.ArrayList;

public class AnimalsRespository {

    private ArrayList<Animals> list = new ArrayList<>();

    //save animal
    public void save(Animals a){
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
