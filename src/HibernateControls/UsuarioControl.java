package HibernateControls;

import Control.ConfiguracionControl;
import Modelo.Usuario;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UsuarioControl {
    
    public void guardarUsuario(Usuario usuario)throws Exception{        
        Session session = SessionConnection.getConnection().useSession();
        try{                             
                Transaction tx=session.beginTransaction(); 
                session.save(usuario); 
                tx.commit();
                ConfiguracionControl.ActualizaId("Usuario");           
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
    
    public void actualizarUsuario(Usuario usuario)throws Exception{        
        Session session = SessionConnection.getConnection().useSession();
        try{                             
                Transaction tx=session.beginTransaction(); 
                session.update(usuario); 
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
    
    public void EliminarUsuario(Usuario usuario)throws Exception{        
        Session session = SessionConnection.getConnection().useSession();
        try{                             
                Transaction tx=session.beginTransaction(); 
                session.delete(usuario); 
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
    
     public List<Usuario> TraeUsuarios(){     
        Session session = SessionConnection.getConnection().useSession();
        Query query= session.createQuery("from Usuario");           
        List<Usuario> lista= query.list();
        session.close();        
        return lista;
    }
}
