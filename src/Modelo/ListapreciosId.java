package Modelo;
// Generated 22-nov-2016 12:23:04 by Hibernate Tools 4.3.1



/**
 * ListapreciosId generated by hbm2java
 */
public class ListapreciosId  implements java.io.Serializable {


     private int idlistaPrecios;
     private int materialIdmaterial;

    public ListapreciosId() {
    }

    public ListapreciosId(int idlistaPrecios, int materialIdmaterial) {
       this.idlistaPrecios = idlistaPrecios;
       this.materialIdmaterial = materialIdmaterial;
    }
   
    public int getIdlistaPrecios() {
        return this.idlistaPrecios;
    }
    
    public void setIdlistaPrecios(int idlistaPrecios) {
        this.idlistaPrecios = idlistaPrecios;
    }
    public int getMaterialIdmaterial() {
        return this.materialIdmaterial;
    }
    
    public void setMaterialIdmaterial(int materialIdmaterial) {
        this.materialIdmaterial = materialIdmaterial;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ListapreciosId) ) return false;
		 ListapreciosId castOther = ( ListapreciosId ) other; 
         
		 return (this.getIdlistaPrecios()==castOther.getIdlistaPrecios())
 && (this.getMaterialIdmaterial()==castOther.getMaterialIdmaterial());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdlistaPrecios();
         result = 37 * result + this.getMaterialIdmaterial();
         return result;
   }   


}


