package vista;

import Control.ConfiguracionControl;
import Control.ControlVentana;
import HibernateControls.TrabajadorControl;
import Modelo.Trabajador;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class BajaTrabajadorController implements Initializable {

    @FXML
    private ComboBox<Trabajador> CmbTrabajadores;

    @FXML
    private DatePicker CmbFechaBaja;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualizaLista();
    }  
    
    public void guardar(){
        ControlVentana cv=new ControlVentana();
        Trabajador trabajador=CmbTrabajadores.getValue();
        TrabajadorControl tc=new TrabajadorControl();        
        trabajador.setFechaFinEstado(ConfiguracionControl.TraeFecha(CmbFechaBaja.getValue()));
        trabajador.setEstado(3);
        try {
            tc.actualizarTrabajador(trabajador);
            cv.creaVentanaNotificacion("Baja", "Se ha dado de baja Correctamente", 2, "tick");
            actualizaLista();
        } catch (Exception ex) {
            cv.creaVentanaNotificacionError(ex.getMessage());
        }  
    }
    
    public void cancelar(){}
    
    public void actualizaLista(){
        CmbTrabajadores.setItems(null);
        TrabajadorControl tc=new TrabajadorControl();
        List<Trabajador> lista=tc.TraeTrabajadoresActivosNoBaja();
        ObservableList<Trabajador> Trabajadores = FXCollections.observableArrayList(lista);       
        CmbTrabajadores.setItems(Trabajadores);
    }
}
