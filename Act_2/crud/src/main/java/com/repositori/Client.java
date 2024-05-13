package com.repositori;

public class Client {
    public int codi_cli;
    public String nom_cli;
    public String nif;
    public String adreca;
    public String ciutat;
    public int telefon;
    
    public Client(int codi_cli, String nom_cli, String nif, String adreca, String ciutat, int telefon) {
        this.codi_cli = codi_cli;
        this.nom_cli = nom_cli;
        this.nif = nif;
        this.adreca = adreca;
        this.ciutat = ciutat;
        this.telefon = telefon;
    }
    public Client() {
        this.codi_cli = codi_cli;
        this.nom_cli = nom_cli;
        this.nif = nif;
        this.adreca = adreca;
        this.ciutat = ciutat;
        this.telefon = telefon;
    }

    public int getCodi_cli() {
        return codi_cli;
    }

    public void setCodi_cli(int codi_cli) {
        this.codi_cli = codi_cli;
    }

    public String getNom_cli() {
        return nom_cli;
    }

    public void setNom_cli(String nom_cli) {
        this.nom_cli = nom_cli;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getAdreca() {
        return adreca;
    }

    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    public String getCiutat() {
        return ciutat;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public String toString() {
        return "Cliente [ID: " + codi_cli + ", Nombre: " + nom_cli + ", NIF: " + nif 
               + ", Dirección: " + adreca + ", Ciudad: " + ciutat + ", Teléfono: " + telefon + "]\n";
    }
}
