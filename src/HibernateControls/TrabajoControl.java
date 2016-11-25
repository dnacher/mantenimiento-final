package HibernateControls;

import Control.ConfiguracionControl;
import Modelo.Trabajo;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TrabajoControl {
    
     public void guardarTrabajo(Trabajo trabajo)throws Exception{        
        Session session = SessionConnection.getConnection().useSession();
        try{                             
                Transaction tx=session.beginTransaction(); 
                session.save(trabajo); 
                tx.commit();
                ConfiguracionControl.ActualizaId("Trabajo");           
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
}
