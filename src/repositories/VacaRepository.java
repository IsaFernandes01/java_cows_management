package repositories;// Repository não conhece quem o chama
import entities.Vaca;

import java.util.ArrayList;

public class VacaRepository {
    private ArrayList<Vaca> vaquinhas = new ArrayList<>();
    private int nextId = 1;

    public void insert(String nome, String brinco) {
        int id = nextId++;
        Vaca vaca = new Vaca(id, nome, brinco);
        vaquinhas.add(vaca);
    }

    public Vaca findById(Integer id) {
        for (Vaca vaca: vaquinhas) {
            if (vaca.getId() == id) {
                return vaca;
            }
        }

        return null;
    }

    public ArrayList<Vaca> findAll() {
        return new ArrayList<>(vaquinhas);
    }

    public void remove(Integer id) {
        for (Vaca vaca: vaquinhas) {
            if (vaca.getId() == id) {
                vaquinhas.remove(vaca);
                return;
            }
        }
        return;
    }
}