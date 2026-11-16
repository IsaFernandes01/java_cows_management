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
        this.pastoRepository.insert(local, capacidade);
        System.out.println("Pasto registrado com sucesso!");
    }

    public void remove() {
        Scanner input = new Scanner(System.in);

        // Input
        System.out.print("ID do pasto: ");
        Integer idPasto = input.nextInt();

        // Find
        Pasto pasto = this.pastoRepository.findById(idPasto);
        if (pasto == null) {
            System.out.println("Não foi possível encontrar pasto");
            return;
        }

        // Validation
        if (pasto.getRebanhoAtual() > 0) {
            System.out.println("O pasto contém animais vinculados");
            return;
        }

        // Remove
        this.pastoRepository.remove(idPasto);
        System.out.println("Pasto removido com sucesso!");
    }
}
