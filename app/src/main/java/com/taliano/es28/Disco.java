package com.taliano.es28;

import java.io.Serializable;

public class Disco implements Serializable {

    private String title;
    private String descrizione;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Disco(String title, String descrizione) {
        this.title = title;
        this.descrizione = descrizione;
    }

    public Disco() {
    }

    @Override
    public String toString() {
        return "Disco{" +
                "title='" + title + '\'' +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }
}
