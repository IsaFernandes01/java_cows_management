package entities;

import java.util.Date;

public class Manejo {
    protected int id;
    protected int idVaca;
    protected int idPasto;
    protected Date dataManejo;

    // Extra fields
    protected String vaca;
    protected String pasto;

    // Insertion Constructor
    public Manejo(int idVaca, int idPasto) {
        this.idVaca = idVaca;
        this.idPasto = idPasto;
    }

    public Manejo(int id, int idVaca, int idPasto) {
        this.id = id;
        this.idVaca = idVaca;
        this.idPasto = idPasto;
    }

    public Manejo(int id, int idVaca, int idPasto, Date dataManejo, String vaca, String pasto) {
        this.id = id;
        this.idVaca = idVaca;
        this.idPasto = idPasto;
        this.dataManejo = dataManejo;
        this.vaca = vaca;
        this.pasto = pasto;
    }

    public Manejo(Manejo manejo) {
        this.id = manejo.id;
        this.vaca = manejo.vaca;
        this.pasto = manejo.pasto;
        this.dataManejo = manejo.dataManejo;
    }

    public String manejoInfoFormated() {
        return "[ID: " + this.id + "] Vaquinha: " + this.vaca + " - Pasto: " + this.pasto + " - Data do manejo: " + this.dataManejo;
    }

    public Manejo manejoInfo() {
        return new Manejo(this);
    }

    // Getters
    public int getId() {
        return this.id;
    }

    public int getIdVaca() {
        return this.idVaca;
    }

    public int getIdPasto() {
        return this.idPasto;
    }

    public Date getDataManejo() {
        return this.dataManejo;
    }

    public String getPasto() {
        return this.pasto;
    }

    public String getVaca() {
        return this.vaca;
    }
}
