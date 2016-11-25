
package Control;

import HibernateControls.SessionConnection;
import java.sql.Connection;
import java.sql.SQLException;
import Modelo.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;

public class LoginControl {
    
    public Usuario Login(String nombreusuario, String Password){
        Usuario usuario;        
        Session session=SessionConnection.getConnection().useSession();
        Query query= session.createQuery("from Usuario where upper(nombre)=:name and password=:pass");            
        query.setParameter("name", nombreusuario.toUpperCase());
        query.setParameter("pass", Password);              
        usuario=(Usuario)query.uniqueResult();           
        //session.close();
        return usuario;
    }
    
    public Connection conectar() throws Exception{
        Connection conexion= null;         
        try {           
            Session session = SessionConnection.getConnection().useSession();              
            SessionFactoryImplementor sessionFactoryImplementation = (SessionFactoryImplementor) session.getSessionFactory();
            ConnectionProvider connectionProvider = sessionFactoryImplementation.getConnectionProvider();            
            conexion=connectionProvider.getConnection();
        } catch (SQLException ex) {
           throw new Exception(ex);
        }
        return conexion;
    }
}
