package HibernateControls;

import Control.ConfiguracionControl;
import Modelo.Trabajador;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TrabajadorControl {
    
     public void guardarTrabajador(Trabajador trabajador)throws Exception{        
        Session session = SessionConnection.getConnection().useSession();
        try{                            
                Transaction tx=session.beginTransaction(); 
                session.save(trabajador); 
                tx.commit();
                ConfiguracionControl.ActualizaId("trabajador");          
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            if(session.isOpen()){
            session.close();            
            }
        }
    }
     
    public void actualizarTrabajador(Trabajador trabajador)throws Exception{        
        Session session = SessionConnection.getConnection().useSession();
        try{                            
                Transaction tx=session.beginTransaction(); 
                session.update(trabajador); 
                tx.commit();                          
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            if(session.isOpen()){
            session.close();            
            }
        }
    }
     
       public List<Trabajador> TraeTrabajadoresActivos(){  
           try{
        Session session = SessionConnection.getConnection().useSession();
        Query query= session.createQuery("from Trabajador trabajador where (trabajador.estado=1) OR (trabajador.estado=3 AND trabajador.fechaFinEstado>:fechaHoy)");
        Date hoy=new Date();
        query.setParameter("fechaHoy", hoy);
        List<Trabajador> lista=query.list();
        session.close();
        return lista;
           }
           catch(Exception ex){
               System.out.println(ex.getMessage());
               return null;
           }
        
    }
       
       public List<Trabajador> TraeTrabajadoresActivosNoBaja(){     
        Session session = SessionConnection.getConnection().useSession();
        Query query= session.createQuery("from Trabajador where estado=1 OR estado=2");                
        List<Trabajador> lista=query.list();
        session.close();        
        return lista;
    }
}
