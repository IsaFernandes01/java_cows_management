package entities;

import java.util.Date;

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

    public Vaca(int id, String nome, String brinco, boolean isManejada) {
        this.id = id;
        this.nome = nome;
        this.brinco = brinco;
        this.isManejada = isManejada;
    }

    public Vaca(Vaca vaca) {
        this.id = vaca.id;
        this.nome = vaca.nome;
        this.brinco = vaca.brinco;
        this.isManejada = vaca.isManejada;
    }

    public String vacaInfoFormated() {
        String manejadaRep = "";
        if (this.isManejada){
            manejadaRep = "Sim";
        } else {
            manejadaRep = "Não";
        }
        return "[ID: " + this.id + "] Nome: " + this.nome + " - Brinco: " + this.brinco + " - Manejada: " + manejadaRep;
    }

    public Vaca vacaInfo() {
        return new Vaca(this);
    }

    // Getters
    public int getId() {
        return this.id;
    }

    public String getNome() {return this.nome; }

    public String getBrinco() {return this.brinco; }

    public boolean getIsManejada() {
        return this.isManejada;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setBrinco(String brinco) {
        this.brinco = brinco;
    }
    public void setIsManejada(boolean isManejada) {
        this.isManejada = isManejada;
    }
}
