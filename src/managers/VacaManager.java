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
        ArrayList<Vaca> vacas = this.vacaRepository.findAll();
        System.out.println("Listando vaquinhas...");

        if (vacas.size() <= 0) {
            System.out.println("Nenhuma vaquinha encontrada");
        }
        for (Vaca vaca: vacas) {
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
    }

    public void remove() {
        Scanner input = new Scanner(System.in);

        // Input
        System.out.print("ID da animal: ");
        Integer idVaca = input.nextInt();

        // Remove
        this.vacaRepository.remove(idVaca);
    }
}
