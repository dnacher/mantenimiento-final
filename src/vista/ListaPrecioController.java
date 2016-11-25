package vista;

import Control.ConfiguracionControl;
import Control.ControlVentana;
import HibernateControls.ListaPrecioControl;
import HibernateControls.MaterialControl;
import Modelo.Listaprecios;
import Modelo.ListapreciosId;
import Modelo.Material;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ListaPrecioController implements Initializable {
    
    @FXML
    private Button BtnCancelar;

    @FXML
    private ComboBox<Material> CmbMateriales;

    @FXML
    private TextField TxtCantidad;

    @FXML
    private DatePicker CmbFecha;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarComboMateriales();
    }    
    
    public void cargarComboMateriales(){
        MaterialControl mc=new MaterialControl();        
        CmbMateriales.setItems(mc.TraeMaterialesO());                
    }
    
    public void guardar(){
        ControlVentana cv=new ControlVentana();
        try{
            Material material=CmbMateriales.getValue();
            material.setEntrada(material.getEntrada()+ Integer.valueOf(TxtCantidad.getText()));
            material.setCantidad(material.getEntrada() - material.getSalida());
            MaterialControl mc=new MaterialControl();            
            mc.actualizarMaterial(material);
            Listaprecios listaPrecio=new Listaprecios();
            ListapreciosId lId=new ListapreciosId();
            lId.setIdlistaPrecios(ConfiguracionControl.traeUltimoId("ListaPrecios"));
            lId.setMaterialIdmaterial(material.getIdmaterial());
            listaPrecio.setId(lId);
            listaPrecio.setFecha(ConfiguracionControl.TraeFecha(CmbFecha.getValue()));
            listaPrecio.setPrecio(Integer.valueOf(TxtCantidad.getText()));
            listaPrecio.setMaterial(material);
            ListaPrecioControl lpc=new ListaPrecioControl();
            lpc.guardarListaPrecio(listaPrecio);
            cv.creaVentanaNotificacionCorrecto();
        }
        catch(Exception ex){
            cv.creaVentanaNotificacionError(ex.getMessage());
        }
    }
    
    public void cancelar(){
        Stage stage = (Stage) BtnCancelar.getScene().getWindow();
        stage.close();
    }
    
}
