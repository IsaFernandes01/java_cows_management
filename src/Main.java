import managers.ManejoManager;
import managers.PastoManager;
import managers.VacaManager;
import repositories.ManejoRepository;
import repositories.PastoRepository;
import repositories.VacaRepository;

import java.util.Scanner;

public class Main {
     public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean executando = true;

        // Boot/DI
        VacaRepository vacaRepository = new VacaRepository();
        PastoRepository pastoRepository = new PastoRepository();
        ManejoRepository manejoRepository = new ManejoRepository();

        VacaManager vacaManager = new VacaManager(vacaRepository, manejoRepository);
        PastoManager pastoManager = new PastoManager(pastoRepository, manejoRepository);
        ManejoManager manejoManager = new ManejoManager(manejoRepository, vacaRepository, pastoRepository);

        // Seleção de opções
        while (executando) {
            mostrarMenu();

            byte opcao = input.nextByte();
            switch (opcao) {
                case 1 -> vacaManager.create();
                case 2 -> vacaManager.list();
                case 3 -> pastoManager.create();
                case 4 -> pastoManager.list();
                case 5 -> manejoManager.create();
                case 6 -> manejoManager.list();
                case 7 -> manejoManager.remove();
                case 8 -> pastoManager.remove();
                case 9 -> vacaManager.remove();
                case 0 -> executando = false;
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    static void mostrarMenu() {
        System.out.println("--- OPÇÕES ---");
        System.out.println("1 -> Criar vaca");
        System.out.println("2 -> Listar vacas");
        System.out.println("3 -> Criar pasto");
        System.out.println("4 -> Listar pastos");
        System.out.println("5 -> Criar manejo");
        System.out.println("6 -> Listar manejos");
        System.out.println("7 -> Remover manejo");
        System.out.println("8 -> Remover pasto");
        System.out.println("9 -> Remover vaca");
        System.out.print("Escolha uma opção (digite \"0\" para sair): ");
    }
}