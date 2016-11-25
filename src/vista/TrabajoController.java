package vista;

import Control.ConfiguracionControl;
import Control.ControlVentana;
import HibernateControls.MaterialControl;
import HibernateControls.TrabajadorControl;
import HibernateControls.TrabajoControl;
import HibernateControls.UnidadesControl;
import Modelo.Material;
import Modelo.Trabajador;
import Modelo.Trabajo;
import Modelo.Unidad;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TrabajoController implements Initializable {
    
     @FXML
    private Button BtnCancelar;

    @FXML
    private TextField TxtDuracion;

    @FXML
    private TableView<Material> TblMateriales;

    @FXML
    private TextArea TxtDescripcion;

    @FXML
    private ComboBox<Trabajador> CmbTrabajador;
    
    @FXML
    private TableView<Unidad> TblUsuarios;

    @FXML
    private DatePicker CmbFecha;

    @FXML
    private ComboBox<Material> CmbMaterial;
    
    @FXML
    private Label LblNombre;

    @FXML
    private Label LblApto;
    
    @FXML
    private Label LblBlock;
    
    @FXML
    private Label LblTorre;
    
    @FXML
    private TextField TxtCantidad;
    
    @FXML
    private ComboBox<String> CmbBlock;
    
    @FXML
    private ComboBox<Integer> CmbTorre;
    
    List<Material> listaMateriales=new ArrayList<>();
    List<Material> remanente=new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        creaTablaUnidades();
        creaTablaMateriales();
        cargarComboBlock();
        cargarComboTorre();
        cargaTrabajador();
        cargaMateriales();
        
    }   
    
    public void cancelar(){
        Stage stage = (Stage) BtnCancelar.getScene().getWindow();
        stage.close();
    }
    
    public void guardar(){
        ControlVentana cv=new ControlVentana();
         try {
             Trabajo trabajo=new Trabajo();
             trabajo.setIdTrabajo(ConfiguracionControl.traeUltimoId("Trabajo"));
             trabajo.setCalificacion(-1);
             trabajo.setDescripcion(TxtDescripcion.getText());
             trabajo.setDuracionEstimada(Integer.valueOf(TxtDuracion.getText()));
             trabajo.setDuracionReal(-1);
             trabajo.setEstado(1);
             trabajo.setFecha(ConfiguracionControl.TraeFecha(CmbFecha.getValue()));
             trabajo.setTrabajador(CmbTrabajador.getValue());
             trabajo.setUnidad(TblUsuarios.getSelectionModel().getSelectedItem());
             Set<Material> setMateriales = new HashSet<>(listaMateriales);
             trabajo.setMaterials(setMateriales);
             TrabajoControl tc=new TrabajoControl();
             tc.guardarTrabajo(trabajo);
             cv.creaVentanaNotificacionCorrecto();
         } catch (Exception ex) {
             cv.creaVentanaNotificacionError(ex.getMessage());
         }
    }
    
    public void cargarComboBlock(){
       ObservableList<String> options = 
    FXCollections.observableArrayList("A","B","C","D","E");
       CmbBlock.setItems(options);
    }
    
    public void cargarComboTorre(){
        ObservableList<Integer> listaTorres;
        listaTorres=FXCollections.observableArrayList(1,2,3,4,5,6);
        CmbTorre.setItems(listaTorres);
    }
    
     public void Mostrar(ActionEvent event) { 
        ControlVentana cv=new ControlVentana();
        try{    
            UnidadesControl uc=new UnidadesControl();
            ObservableList<Unidad> listaO;
            List<Unidad> listaTorreBlock=uc.TraeUnidadesXBlockTorre(CmbBlock.getValue(), CmbTorre.getValue());              
            listaO = FXCollections.observableList(listaTorreBlock);
            cargaTabla(listaO);        
        }
        catch(Exception ex){
           cv.creaVentanaNotificacionError("Debe seleccionar valores de Block y Torre para buscar");
        }
    }
     
    public void cargaTabla(ObservableList<Unidad> retorno){       
        TblUsuarios.setItems(retorno);     
    }
    
    public void cargaLbl(){
        Unidad u=TblUsuarios.getSelectionModel().getSelectedItem();
        LblNombre.setText(u.getNombre() + " " + u.getApellido());
        LblBlock.setText(u.getBlock());
        LblTorre.setText(u.getTorre().toString());
        LblApto.setText(u.getPuerta().toString());
    }
    
    public void creaTablaUnidades(){
       TableColumn Nombre = new TableColumn("Nombre");
       TableColumn Apellido = new TableColumn("Apellido");
       TableColumn Block = new TableColumn("Block");
       TableColumn Torre = new TableColumn("Torre");
       TableColumn Puerta= new TableColumn("Puerta");
        
       Nombre.setMinWidth(100);
       Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

       Apellido.setMinWidth(100);
       Apellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));

       Block.setMinWidth(50);
       Block.setCellValueFactory(new PropertyValueFactory<>("Block"));

       Torre.setMinWidth(50);
       Torre.setCellValueFactory(new PropertyValueFactory<>("Torre"));

       Puerta.setMinWidth(60);
       Puerta.setCellValueFactory(new PropertyValueFactory<>("Puerta"));
        
       TblUsuarios.getColumns().addAll(Nombre, Apellido, Block,Torre,Puerta);
    }
    
    
    public void creaTablaMateriales(){
       TableColumn Nombre = new TableColumn("Nombre");
       TableColumn Descripcion = new TableColumn("Descripcion");
       TableColumn FechaCompra = new TableColumn("Fecha Compra");
       TableColumn Cantidad = new TableColumn("Cantidad");
       
        
       Nombre.setMinWidth(100);
       Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

       Descripcion.setMinWidth(200);
       Descripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));

       FechaCompra.setMinWidth(100);
       FechaCompra.setCellValueFactory(new PropertyValueFactory<>("FechaCompra"));

       Cantidad.setMinWidth(50);
       Cantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));     
        
       TblMateriales.getColumns().addAll(Nombre, Descripcion, FechaCompra,Cantidad);
    }
    
    
    public void cargaMateriales(){
        MaterialControl mc=new MaterialControl();
        remanente=mc.TraeMateriales();
        ObservableList<Material> listaO=FXCollections.observableArrayList(remanente);
        CmbMaterial.setItems(listaO);
    }
    
    public void cargaTrabajador(){
        TrabajadorControl tc=new TrabajadorControl();
        ObservableList<Trabajador> listaO=tc.TraeTrabajadoresActivosO();
        CmbTrabajador.setItems(listaO);
    }
    
    public void agregarMaterial(){
     ControlVentana cv=new ControlVentana();
     if(CmbMaterial.getValue()!=null){
        if(!TxtCantidad.getText().equals("")){
            Material material=CmbMaterial.getValue();
            material.setSalida(Integer.valueOf(TxtCantidad.getText()));
            material.setCantidad(material.getEntrada() - material.getSalida());
            listaMateriales.add(CmbMaterial.getValue());
            ObservableList<Material> listaO=FXCollections.observableArrayList(listaMateriales);
            TblMateriales.setItems(listaO);
            remanente.remove(material);
            ObservableList<Material> materialesO=FXCollections.observableArrayList(remanente);
            CmbMaterial.setItems(materialesO);
        }
        else{
           cv.creaVentanaNotificacionError("Debe ingresar la cantidad"); 
        }
     }
     else{
         cv.creaVentanaNotificacionError("Debe seleccionar un materilal");
     }
    }
}
