package repositories;// Repository não conhece quem o chama

import entities.Pasto;
import entities.Vaca;

import java.util.ArrayList;

public class PastoRepository {
    private ArrayList<Pasto> pastos = new ArrayList<>();
    private int nextId = 1;

    public void insert(String local, int capacidade) {
        int id = nextId++;
        Pasto pasto = new Pasto(id, local, capacidade);
        pastos.add(pasto);
    }

    public Pasto findById(Integer id) {
        for (Pasto pasto: pastos) {
            if (pasto.getId() == id) {
                return pasto;
            }
        }

        return null;
    }

    public ArrayList<Pasto> findAll() {
        return new ArrayList<>(pastos);
    }

    public void remove(Integer id) {
        for (Pasto pasto: pastos) {
            if (pasto.getId() == id) {
                pastos.remove(pasto);
                return;
            }
        }
        return;
    }
}
