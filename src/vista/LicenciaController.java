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

public class LicenciaController implements Initializable {
    
    @FXML
    private DatePicker CmbDesde;

    @FXML
    private DatePicker CmbHasta;

    @FXML
    private ComboBox<Trabajador> CmbTrabajador;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualizaLista();
    }    
    
    public void guardar(){
        ControlVentana cv=new ControlVentana();
        Trabajador trabajador=CmbTrabajador.getValue();
        TrabajadorControl tc=new TrabajadorControl();
        trabajador.setFechaInicioEstado(ConfiguracionControl.TraeFecha(CmbDesde.getValue()));
        trabajador.setFechaFinEstado(ConfiguracionControl.TraeFecha(CmbHasta.getValue()));
        trabajador.setEstado(2);
        try {
            tc.actualizarTrabajador(trabajador);
            cv.creaVentanaNotificacion("Licencia", "Disfrute su Licencia", 2, "tick");
            actualizaLista();
        } catch (Exception ex) {
            cv.creaVentanaNotificacionError(ex.getMessage());
        }        
    }
    
    public void cancelar(){
    
    }
    
    public void actualizaLista(){
        CmbTrabajador.setItems(null);
        TrabajadorControl tc=new TrabajadorControl();
        List<Trabajador> lista=tc.TraeTrabajadoresActivos();
        ObservableList<Trabajador> Trabajadores = FXCollections.observableArrayList(lista);       
        CmbTrabajador.setItems(Trabajadores);
    }
}
