package Modelo;
// Generated 22-nov-2016 12:23:04 by Hibernate Tools 4.3.1



/**
 * HistorialtrabajoId generated by hbm2java
 */
public class HistorialtrabajoId  implements java.io.Serializable {


     private int idHistorialTrabajo;
     private int trabajoIdTrabajo;

    public HistorialtrabajoId() {
    }

    public HistorialtrabajoId(int idHistorialTrabajo, int trabajoIdTrabajo) {
       this.idHistorialTrabajo = idHistorialTrabajo;
       this.trabajoIdTrabajo = trabajoIdTrabajo;
    }
   
    public int getIdHistorialTrabajo() {
        return this.idHistorialTrabajo;
    }
    
    public void setIdHistorialTrabajo(int idHistorialTrabajo) {
        this.idHistorialTrabajo = idHistorialTrabajo;
    }
    public int getTrabajoIdTrabajo() {
        return this.trabajoIdTrabajo;
    }
    
    public void setTrabajoIdTrabajo(int trabajoIdTrabajo) {
        this.trabajoIdTrabajo = trabajoIdTrabajo;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof HistorialtrabajoId) ) return false;
		 HistorialtrabajoId castOther = ( HistorialtrabajoId ) other; 
         
		 return (this.getIdHistorialTrabajo()==castOther.getIdHistorialTrabajo())
 && (this.getTrabajoIdTrabajo()==castOther.getTrabajoIdTrabajo());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdHistorialTrabajo();
         result = 37 * result + this.getTrabajoIdTrabajo();
         return result;
   }   


}

