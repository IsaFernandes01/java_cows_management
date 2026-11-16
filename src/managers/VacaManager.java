package managers;

import entities.Vaca;
import repositories.ManejoRepository;
import repositories.VacaRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class VacaManager {
    public VacaRepository vacaRepository;
    public ManejoRepository manejoRepository;

    public VacaManager(VacaRepository vacaRepository, ManejoRepository manejoRepository) {
        this.vacaRepository = vacaRepository;
        this.manejoRepository = manejoRepository;
    }

    public void list() {
        ArrayList<Vaca> vaquinhas = this.vacaRepository.findAll();

        System.out.println("Listando vaquinhas...");
        for (Vaca vaca: vaquinhas) {
            System.out.println(vaca.vacaInfoFormated());
        }
    }

    public void create() {
        Scanner input = new Scanner(System.in);

        // Inputs
        System.out.print("Nome da vaquinha: ");
        String nome = input.nextLine();

        System.out.print("Brinco da vaquinha: ");
        String brinco = input.nextLine();

        // Create
        this.vacaRepository.insert(nome, brinco);
        System.out.println("Vaca registrada com sucesso!");
    }

    public void remove() {
        Scanner input = new Scanner(System.in);

        // Input
        System.out.print("ID da animal: ");
        Integer idVaca = input.nextInt();

        // Find
        Vaca vaca = this.vacaRepository.findById(idVaca);
        if (vaca == null) {
            System.out.println("Não foi possível encontrar vaca");
        }

        if (vaca.getIsManejada()) {
            System.out.println("A vaca já está vinculada a um pasto");
            return;
        }

        // Remove
        this.vacaRepository.remove(idVaca);
        System.out.println("Vaca removida com sucesso!");
    }
}
