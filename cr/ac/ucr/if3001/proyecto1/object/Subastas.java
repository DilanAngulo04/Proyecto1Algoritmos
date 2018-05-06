package cr.ac.ucr.if3001.proyecto1.object;

import java.io.Serializable;

public class Subastas implements Serializable{
    //atributos

    //control de subastas
    private int cantidadConsecutivos;
    private int cantidadArticulos;
    private int articulosSubastados;
    private int cantidadParticipantes;
    private int montosAdjudicados;
    private int cantidadesadquiridas;
    private int ranking;
    private String ganadores;

    
    //constructores
    public Subastas() {
        this.cantidadConsecutivos = 0;
        this.cantidadArticulos = 0;
        this.articulosSubastados = 0;
        this.cantidadParticipantes = 0;
        this.montosAdjudicados = 0;
        this.cantidadesadquiridas = 0;
        this.ranking = 0;
        this.ganadores = "";
    }

    public Subastas(int cantidadConsecutivos, int cantidadArticulos, int articulosSubastados, int cantidadParticipantes, int montosAdjudicados, int cantidadesadquiridas, int ranking, String ganadores) {
        this.cantidadConsecutivos = cantidadConsecutivos;
        this.cantidadArticulos = cantidadArticulos;
        this.articulosSubastados = articulosSubastados;
        this.cantidadParticipantes = cantidadParticipantes;
        this.montosAdjudicados = montosAdjudicados;
        this.cantidadesadquiridas = cantidadesadquiridas;
        this.ranking = ranking;
        this.ganadores = ganadores;
    }
    
    //setters and getters

    public int getCantidadConsecutivos() {
        return cantidadConsecutivos;
    }

    public void setCantidadConsecutivos(int cantidadConsecutivos) {
        this.cantidadConsecutivos = cantidadConsecutivos;
    }

    public int getCantidadArticulos() {
        return cantidadArticulos;
    }

    public void setCantidadArticulos(int cantidadArticulos) {
        this.cantidadArticulos = cantidadArticulos;
    }

    public int getArticulosSubastados() {
        return articulosSubastados;
    }

    public void setArticulosSubastados(int articulosSubastados) {
        this.articulosSubastados = articulosSubastados;
    }

    public int getCantidadParticipantes() {
        return cantidadParticipantes;
    }

    public void setCantidadParticipantes(int cantidadParticipantes) {
        this.cantidadParticipantes = cantidadParticipantes;
    }

    public int getMontosAdjudicados() {
        return montosAdjudicados;
    }

    public void setMontosAdjudicados(int montosAdjudicados) {
        this.montosAdjudicados = montosAdjudicados;
    }

    public int getCantidadesadquiridas() {
        return cantidadesadquiridas;
    }

    public void setCantidadesadquiridas(int cantidadesadquiridas) {
        this.cantidadesadquiridas = cantidadesadquiridas;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getGanadores() {
        return ganadores;
    }

    public void setGanadores(String ganadores) {
        this.ganadores = ganadores;
    }
    
   //fin setters and getters

    @Override
    public String toString() {
        return "Subastas{" + "cantidadConsecutivos=" + cantidadConsecutivos + ", cantidadArticulos=" + cantidadArticulos + ", articulosSubastados=" + articulosSubastados + ", cantidadParticipantes=" + cantidadParticipantes + ", montosAdjudicados=" + montosAdjudicados + ", cantidadesadquiridas=" + cantidadesadquiridas + ", ranking=" + ranking + ", ganadores=" + ganadores + '}';
    }    
    //fin toString
}//fin class
