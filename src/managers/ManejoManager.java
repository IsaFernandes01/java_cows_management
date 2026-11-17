package managers;

import entities.Manejo;
import entities.Pasto;
import entities.Vaca;
import repositories.ManejoRepository;
import repositories.PastoRepository;
import repositories.VacaRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class ManejoManager {
    public ManejoRepository manejoRepository;
    public VacaRepository vacaRepository;
    public PastoRepository pastoRepository;

    public ManejoManager(ManejoRepository manejoRepository,VacaRepository vacaRepository, PastoRepository pastoRepository) {
        this.manejoRepository = manejoRepository;
        this.vacaRepository = vacaRepository;
        this.pastoRepository = pastoRepository;
    }

    // List manejos
    public void list() {
        ArrayList<Manejo> manejos = this.manejoRepository.findAll();
        System.out.println("Listando manejos...");

        if (manejos.size() <= 0) {
            System.out.println("Nenhuma vaquinha encontrada");
        }
        for (Manejo manejo: manejos) {
            System.out.println(manejo.manejoInfoFormated());
        }
    }

    // Create manejo
    public void create() {
        Scanner input = new Scanner(System.in);

        // Inputs
        System.out.print("ID do animal: ");
        int idVaca = input.nextInt();

        // Find
        Vaca vaca = this.vacaRepository.findById(idVaca);
        if (vaca.getIsManejada()) {
            System.out.println("A vaca já está vinculada a um pasto");
            return;
        }

        // Inputs
        System.out.print("ID do pasto: ");
        int idPasto = input.nextInt();

        // Find
        Pasto pasto = this.pastoRepository.findById(idPasto);
        if (pasto.isFull()) {
            System.out.println("Capacidade máxima do pasto atingida");
            return;
        }

        // Create
        this.manejoRepository.insert(idVaca, idPasto);

        // Results
        pasto.addVaquinha();
        this.pastoRepository.update(pasto);

        vaca.setIsManejada(true);
        this.vacaRepository.update(vaca);
    }

    public void remove() {
        Scanner input = new Scanner(System.in);

        // Input
        System.out.print("ID do manejo: ");
        Integer idManejo = input.nextInt();

        // Find
        Manejo manejo = this.manejoRepository.findById(idManejo);
        if (manejo == null) {
            System.out.println("Não foi possível encontrar manejo");
            return;
        }

        Vaca vaca = this.vacaRepository.findById(manejo.getIdVaca());
        Pasto pasto = this.pastoRepository.findById(manejo.getIdPasto());

        // Remove
        this.manejoRepository.remove(idManejo);

        // Results
        pasto.removeVaquinha();
        this.pastoRepository.update(pasto);

        vaca.setIsManejada(false);
        this.vacaRepository.update(vaca);
    }
}
