package entities;

import java.util.Date;

public class Manejo {
    protected int id;
    protected Vaca vaca;
    protected Pasto pasto;
    protected Date dataManejo;

    public Manejo(int id, Vaca vaca, Pasto pasto) {
        this.id = id;
        this.vaca = vaca;
        this.pasto = pasto;
        this.dataManejo = new Date(); // Automatico
    }

    public Manejo(Manejo manejo) {
        this.id = manejo.id;
        this.vaca = manejo.vaca;
        this.pasto = manejo.pasto;
        this.dataManejo = manejo.dataManejo;
    }

    public String manejoInfoFormated() {
        Vaca vaca = this.vaca.vacaInfo();
        Pasto pasto = this.pasto.pastoInfo();
        return "[ID: " + this.id + "] Vaquinha: " + vaca.nome + " - Pasto: " + pasto.local + " - Data do manejo: " + this.dataManejo;
    }

    public Manejo manejoInfo() {
        return new Manejo(this);
    }

    public int getId() {
        return this.id;
    }

    public Pasto getPasto() {
        return this.pasto;
    }

    public Vaca getVaca() {
        return this.vaca;
    }
}
