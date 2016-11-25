package HibernateControls;

import Control.ConfiguracionControl;
import Modelo.Material;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MaterialControl {
    
    
    public void guardarMaterial(Material material)throws Exception{        
        Session session = SessionConnection.getConnection().useSession();
        try{                             
                Transaction tx=session.beginTransaction(); 
                session.save(material); 
                tx.commit();
                ConfiguracionControl.ActualizaId("Material");           
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
    
    public void actualizarMaterial(Material material)throws Exception{        
        Session session = SessionConnection.getConnection().useSession();
        try{                             
                Transaction tx=session.beginTransaction(); 
                session.update(material); 
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
    
     public List<Material> TraeMateriales(){     
        Session session = SessionConnection.getConnection().useSession();
        Query query= session.createQuery("from Material");           
        List<Material> lista= query.list();
        session.close();        
        return lista;
    }
     
     public ObservableList<Material> TraeMaterialesO(){     
         ObservableList<Material> list;
         list=FXCollections.observableArrayList(TraeMateriales());
         return list;
     }  
     
}
