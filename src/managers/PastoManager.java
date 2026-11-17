package managers;

import entities.Pasto;
import repositories.ManejoRepository;
import repositories.PastoRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class PastoManager {
    public PastoRepository pastoRepository;
    public ManejoRepository manejoRepository;

    public PastoManager(PastoRepository pastoRepository, ManejoRepository manejoRepository) {
        this.pastoRepository = pastoRepository;
        this.manejoRepository = manejoRepository;
    }

    public void list() {
        ArrayList<Pasto> pastos = this.pastoRepository.findAll();
        System.out.println("Listando pastos...");

        if (pastos.size() <= 0) {
            System.out.println("Nenhum pasto encontrado");
        }
        for (Pasto pasto: pastos) {
            System.out.println(pasto.pastoInfoFormated());
        }
    }

    public void create() {
        Scanner input = new Scanner(System.in);

        // Inputs
        System.out.print("Localização do pasto: ");
        String local = input.nextLine();

        System.out.print("Capacidade: ");
        int capacidade = input.nextInt();
        if (capacidade <= 0) {
            System.out.println("A capacidade deve ser maior que zero");
            return;
        }

        // Create
        Pasto pasto = new Pasto(local, capacidade);
        this.pastoRepository.insert(pasto);
    }

    public void remove() {
        Scanner input = new Scanner(System.in);

        // Input
        System.out.print("ID do pasto: ");
        Integer idPasto = input.nextInt();

        // Remove
        this.pastoRepository.remove(idPasto);
    }
}
