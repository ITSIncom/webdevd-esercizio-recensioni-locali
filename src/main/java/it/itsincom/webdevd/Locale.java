package it.itsincom.webdevd;

public class Locale {
    private String nome;
    private String indirizzo;

    public String getNome() {
        return nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public Locale(String nome, String indirizzo) {
        this.nome = nome;
        this.indirizzo = indirizzo;
    }
}
