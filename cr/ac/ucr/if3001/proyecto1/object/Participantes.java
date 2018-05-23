package cr.ac.ucr.if3001.proyecto1.object;

import java.io.Serializable;

public class Participantes extends Seguridad implements Serializable{
    
    //atributos
    private int montosAdjudicados;
    private int ranking;

    public Participantes(String nombre, String correo, String nombreUsuario, String contraseña, 
            int numeroTelefono, int montosAdjudicados, int ranking) {
        super(nombre, correo, nombreUsuario, contraseña, numeroTelefono);
        this.montosAdjudicados = montosAdjudicados;
        this.ranking = ranking;
    }   

    public Participantes() {
        super();
        this.montosAdjudicados = 0;
        this.ranking = 0;
        
    }    
    //fin contructores

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }    

    public int getMontosAdjudicados() {
        return montosAdjudicados;
    }

    public void setMontosAdjudicados(int montosAdjudicados) {
        this.montosAdjudicados = montosAdjudicados;
    }        
    //Fin setters and getters   

    @Override
    public String toString() {
        return "Participantes {" + ", Montos Adjudicados = " + montosAdjudicados + ", Ranking = " + ranking + '}';
    }
    //fin toString

}//fin class
