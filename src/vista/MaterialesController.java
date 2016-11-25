package vista;

import Control.ConfiguracionControl;
import Control.ControlVentana;
import HibernateControls.MaterialControl;
import Modelo.Material;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MaterialesController implements Initializable {
    
    @FXML
    private TextField TxtNombre;

    @FXML
    private TextField TxtCantidad;

    @FXML
    private TextArea TxtDescripcion;

    @FXML
    private DatePicker CmbFecha;
    
    @FXML
    private Button BtnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    public void guardar(){
        ControlVentana cv=new ControlVentana();
        try {
            Material material=new Material();
            material.setIdmaterial(ConfiguracionControl.traeUltimoId("Material"));
            material.setNombre(TxtNombre.getText());
            material.setDescripcion(TxtDescripcion.getText());
            material.setFechaCompra(ConfiguracionControl.TraeFecha(CmbFecha.getValue()));
            material.setCantidad(Integer.valueOf(TxtCantidad.getText()));
            material.setEntrada(material.getCantidad());
            material.setSalida(0);
            MaterialControl mc=new MaterialControl();
            mc.guardarMaterial(material);
            cv.creaVentanaNotificacionCorrecto();
        } catch (Exception ex) {
            cv.creaVentanaNotificacionError(ex.getMessage());
        }
    }
    
    public void cancelar(){
        Stage stage = (Stage) BtnCancelar.getScene().getWindow();
        stage.close();
    }
    
     public void agregarMateriales(){
         ControlVentana cv=new ControlVentana();
        try {
            cv.crearVentanasinCSS("ListaPrecio", "Agregar Materiales");
            cancelar();
        } catch (IOException ex) {
            cv.creaVentanaNotificacionError(ex.getMessage());
        }
    }
    
}
