package HibernateControls;

import Control.ConfiguracionControl;
import Modelo.Listaprecios;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ListaPrecioControl {
    
    public void guardarListaPrecio(Listaprecios listaprecio)throws Exception{        
        Session session = SessionConnection.getConnection().useSession();
        try{                             
                Transaction tx=session.beginTransaction(); 
                session.save(listaprecio); 
                tx.commit();
                ConfiguracionControl.ActualizaId("ListaPrecios");           
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
