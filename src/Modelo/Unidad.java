package Modelo;
// Generated 22-nov-2016 12:23:04 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Unidad generated by hbm2java
 */
public class Unidad  implements java.io.Serializable {


     private int idUnidad;
     private String block;
     private Integer torre;
     private Integer puerta;
     private String nombre;
     private String apellido;
     private String telefono;
     private String mail;
     private Boolean propietarioInquilino;
     private Date fechaIngreso;
     private Boolean activo;
     private Set trabajos = new HashSet(0);

    public Unidad() {
    }

	
    public Unidad(int idUnidad) {
        this.idUnidad = idUnidad;
    }
    
    public Unidad(int idUnidad, String block, Integer torre, Integer puerta, String nombre, String apellido, String telefono, String mail, Boolean propietarioInquilino, Date fechaIngreso, Boolean activo) {
       this.idUnidad = idUnidad;
       this.block = block;
       this.torre = torre;
       this.puerta = puerta;
       this.nombre = nombre;
       this.apellido = apellido;
       this.telefono = telefono;
       this.mail = mail;
       this.propietarioInquilino = propietarioInquilino;
       this.fechaIngreso = fechaIngreso;
       this.activo = activo;       
    }
    
    public Unidad(int idUnidad, String block, Integer torre, Integer puerta, String nombre, String apellido, String telefono, String mail, Boolean propietarioInquilino, Date fechaIngreso, Boolean activo, Set trabajos) {
       this.idUnidad = idUnidad;
       this.block = block;
       this.torre = torre;
       this.puerta = puerta;
       this.nombre = nombre;
       this.apellido = apellido;
       this.telefono = telefono;
       this.mail = mail;
       this.propietarioInquilino = propietarioInquilino;
       this.fechaIngreso = fechaIngreso;
       this.activo = activo;
       this.trabajos = trabajos;
    }
   
    public int getIdUnidad() {
        return this.idUnidad;
    }
    
    public void setIdUnidad(int idUnidad) {
        this.idUnidad = idUnidad;
    }
    public String getBlock() {
        return this.block;
    }
    
    public void setBlock(String block) {
        this.block = block;
    }
    public Integer getTorre() {
        return this.torre;
    }
    
    public void setTorre(Integer torre) {
        this.torre = torre;
    }
    public Integer getPuerta() {
        return this.puerta;
    }
    
    public void setPuerta(Integer puerta) {
        this.puerta = puerta;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getMail() {
        return this.mail;
    }
    
    public void setMail(String mail) {
        this.mail = mail;
    }
    public Boolean getPropietarioInquilino() {
        return this.propietarioInquilino;
    }
    
    public void setPropietarioInquilino(Boolean propietarioInquilino) {
        this.propietarioInquilino = propietarioInquilino;
    }
    public Date getFechaIngreso() {
        return this.fechaIngreso;
    }
    
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    public Boolean getActivo() {
        return this.activo;
    }
    
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    public Set getTrabajos() {
        return this.trabajos;
    }
    
    public void setTrabajos(Set trabajos) {
        this.trabajos = trabajos;
    }




}


