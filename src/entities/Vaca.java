package entities;

public class Vaca {
    protected int id;
    protected String nome;
    protected String brinco;
    protected boolean isManejada;

    public Vaca(int id, String nome, String brinco) {
        this.id = id;
        this.nome = nome;
        this.brinco = brinco;
        this.isManejada = false;
    }

    public Vaca(Vaca vaca) {
        this.id = vaca.id;
        this.nome = vaca.nome;
        this.brinco = vaca.brinco;
        this.isManejada = vaca.isManejada;
    }

    public String vacaInfoFormated() {
        return "[ID: " + this.id + "] Nome: " + this.nome + " - Brinco: " + this.brinco;
    }

    public Vaca vacaInfo() {
        return new Vaca(this);
    }

    public int getId() {
        return this.id;
    }

    public boolean getIsManejada() {
        return this.isManejada;
    }

    public void setIsManejada(boolean isManejada) {
        this.isManejada = isManejada;
    }
}
