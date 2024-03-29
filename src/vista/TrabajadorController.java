package vista;

import Control.ConfiguracionControl;
import Control.ControlVentana;
import HibernateControls.TrabajadorControl;
import Modelo.Trabajador;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TrabajadorController implements Initializable {
    
      @FXML
    private TextField TxtApellido;

    @FXML
    private TextField TxtNombre;

    @FXML
    private TextField TxtTelefono;

    @FXML
    private TextField TxtMail;
    
    @FXML
    private Button BtnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    
    
    public void guardarTrabajador(){
        ControlVentana cv= new ControlVentana();
        Trabajador trabajador= new Trabajador();
        trabajador.setNombre(TxtNombre.getText());
        trabajador.setApellido(TxtApellido.getText());
        trabajador.setTelefono(ConfiguracionControl.StringAInt(TxtTelefono.getText()));
        trabajador.setMail(TxtMail.getText());
        trabajador.setCalificacion(-1);
        trabajador.setEstado(1);
        trabajador.setIdTrabajador(ConfiguracionControl.traeUltimoId("Trabajador"));
        TrabajadorControl tc= new TrabajadorControl();
          try {
              tc.guardarTrabajador(trabajador);
              cv.creaVentanaNotificacionCorrecto();
          } catch (Exception ex) {
              cv.creaVentanaNotificacionError(ex.getMessage());
          }
    }
    
     public void cancelar(){
        Stage stage = (Stage) BtnCancelar.getScene().getWindow();
        stage.close();
    }
    
    public void Baja(){
        ControlVentana cv=new ControlVentana();
          try {
              cv.crearVentanasinCSS("BajaTrabajador", "Baja");
          } catch (IOException ex) {
              cv.creaVentanaNotificacionError(ex.getMessage());
          }
    }   
     
    
}
