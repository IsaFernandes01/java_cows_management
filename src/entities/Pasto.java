package entities;

public class Pasto {
    protected int id;
    protected String local;
    protected int capacidade;
    protected int rebanhoAtual;

    public Pasto(int id, String local, int capacidade) {
        this.id = id;
        this.local = local;
        this.capacidade = capacidade;
        this.rebanhoAtual = 0; // Inicia-se 0
    }

    public Pasto(int id, String local, int capacidade, int rebanhoAtual) {
        this.id = id;
        this.local = local;
        this.capacidade = capacidade;
        this.rebanhoAtual = rebanhoAtual; // Inicia-se 0
    }

    public Pasto(Pasto pasto) {
        this.id = pasto.id;
        this.local = pasto.local;
        this.capacidade = pasto.capacidade;
        this.rebanhoAtual = pasto.rebanhoAtual;
    }

    public String pastoInfoFormated() {
        return "[ID: " + this.id + "] Local: " + this.local + " - Capacidade: " + this.capacidade + " - Rebanho: " + this.rebanhoAtual;
    }

    public Pasto pastoInfo() {
        return new Pasto(this);
    }

    public void addVaquinha() {
        this.rebanhoAtual ++;
    }

    public void removeVaquinha() {
        if (this.rebanhoAtual != 0) {
            this.rebanhoAtual --;
        }
    }

    public boolean isFull() {
        return this.capacidade == this.rebanhoAtual;
    }

    // Getters
    public int getId() {
        return this.id;
    }

    public String getLocal() {
        return this.local;
    }

    public int getCapacidade() {
        return this.capacidade;
    }

    public int getRebanhoAtual() {
        return this.rebanhoAtual;
    }

    // Setters
    public void setLocal(String local) {
        this.local = local;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public void setRebanhoAtual(int rebanhoAtual) {
        this.rebanhoAtual = rebanhoAtual;
    }
}
