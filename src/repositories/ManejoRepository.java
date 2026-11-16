package repositories;// Repository não conhece quem o chama

import entities.Manejo;
import entities.Pasto;
import entities.Vaca;

import java.util.ArrayList;

public class ManejoRepository {
    private ArrayList<Manejo> manejos = new ArrayList<>();
    private int nextId = 1;

    public void insert(Vaca vaca, Pasto pasto) {
        int id = nextId++;
        Manejo manejo = new Manejo(id, vaca, pasto);
        manejos.add(manejo);
    }

    public Manejo findById(Integer id) {
        for (Manejo manejo: manejos) {
            if (manejo.getId() == id) {
                return manejo;
            }
        }

        return null;
    }


    public ArrayList<Manejo> findAll() {
        return new ArrayList<>(manejos);
    }

    public void remove(Integer id) {
        for (Manejo manejo: manejos) {
            if (manejo.getId() == id) {
                manejos.remove(manejo);
                return;
            }
        }
        return;
    }
}
