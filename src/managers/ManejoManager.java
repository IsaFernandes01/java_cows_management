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
        for (Manejo manejo: manejos) {
            System.out.println(manejo.manejoInfoFormated());
        }
    }

    // Create manejo
    public void create() {
        Scanner input = new Scanner(System.in);

        // Inputs
        System.out.print("ID do animal: ");
        Integer idVaca = input.nextInt();

        System.out.print("ID do pasto: ");
        Integer idPasto = input.nextInt();

        // Find
        Vaca vaca = this.vacaRepository.findById(idVaca);
        Pasto pasto = this.pastoRepository.findById(idPasto);

        // ID validation
        if (vaca == null) {
            System.out.println("Não foi possível encontrar vaca");
            return;
        }
        if (pasto == null) {
            System.out.println("Não foi possível encontrar pasto");
            return;
        }

        // Manejada?
        if (vaca.getIsManejada()) {
            System.out.println("A vaca já está vinculada a um pasto");
            return;
        }
        // Pasto cheio?
        if (pasto.isFull()) {
            System.out.println("Capacidade máxima do pasto atingida");
            return;
        }

        // Create
        this.manejoRepository.insert(vaca, pasto);
        System.out.println("Manejo registrado com sucesso!");

        // Results
        pasto.addVaquinha();
        vaca.setIsManejada(true);
    }

    public void remove() {
        Scanner input = new Scanner(System.in);

        // Input
        System.out.print("ID do manejo: ");
        Integer idManejo = input.nextInt();

        // Find
        Manejo manejo = this.manejoRepository.findById(idManejo);
        Pasto pasto = this.pastoRepository.findById(manejo.getPasto().getId());
        if (pasto == null) {
            System.out.println("Não foi possível encontrar pasto");
            return;
        }
        Vaca vaca = this.vacaRepository.findById(manejo.getVaca().getId());
        if (vaca == null) {
            System.out.println("Não foi possível encontrar vaca");
            return;
        }

        // Remove
        this.manejoRepository.remove(idManejo);
        System.out.println("Manejo removido com sucesso!");

        // Results
        pasto.removeVaquinha();
        vaca.setIsManejada(false);
    }
}
