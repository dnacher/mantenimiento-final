package vista;

import Control.ConfiguracionControl;
import Control.ControlVentana;
import HibernateControls.UsuarioControl;
import Modelo.Usuario;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UsuariosController implements Initializable {

    @FXML
    private Label LblNombre;

    @FXML
    private Label LblPass2;

    @FXML
    private Label LblTipoUsuario;
    
    @FXML
    private Button BtnGuardar;

    @FXML
    private Label Lblpass;

    @FXML
    private Label LblUsuarios;
    
    @FXML
    private ComboBox<String> CmbTipoUsuario;

    @FXML
    private TextField TxtNombre;

    @FXML
    private PasswordField TxtPass2;

    @FXML
    private ComboBox<String> CmbAccion;

    @FXML
    private PasswordField TxtPass;

    @FXML
    private ComboBox<Usuario> CmbUsuarios;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargaCmbAccion();
        cargaCmbTipoUsuario();
        cargaCmbUsuarios();
    }    
    
    public void guardar(){
        opcionCmb();
    }
    
    public void cancelar(){
    
    }
    
    public void cargaCmbAccion(){
        ObservableList<String> list;
        list=FXCollections.observableArrayList("Crear Usuario","Modificar Usuario","Eliminar Usuario");
        CmbAccion.setItems(list);
    }
    
    public void cargaCmbUsuarios(){
        UsuarioControl cu=new UsuarioControl();
        List<Usuario>lista=cu.TraeUsuarios();
        ObservableList<Usuario> list;
        list=FXCollections.observableArrayList(lista);
        CmbUsuarios.setItems(list);
    }
    
    public void cargaCmbTipoUsuario(){
        ObservableList<String> list;
        list=FXCollections.observableArrayList("Usuario","Supervisor","Super Usuario");
        CmbTipoUsuario.setItems(list);
    }
    
    public void opcionCmb(){
        UsuarioControl uc=new UsuarioControl();
        ControlVentana cv=new ControlVentana();
        switch(CmbAccion.getValue()){
            case "Crear Usuario":
                if(verificaCampos()==0){
                    if(TxtPass.getText().equals(TxtPass2.getText())){
                    Usuario usuario=new Usuario();
                    usuario.setIdUsuario(ConfiguracionControl.traeUltimoId("Usuario"));
                    usuario.setNombre(TxtNombre.getText());
                    usuario.setPassword(TxtPass.getText());
                    usuario.setTipoUsuario(traeTipoUsuario(CmbTipoUsuario.getValue()));
                        try {
                            uc.guardarUsuario(usuario);
                            cv.creaVentanaNotificacionCorrecto();
                        } catch (Exception ex) {
                            cv.creaVentanaNotificacionError(ex.getMessage());
                        }
                    }
                    else{
                        cv.creaVentanaNotificacionError("Las contraseñas no coinciden");
                    }
                }
                else{
                    mensajeVerificacion(verificaCampos());
                }
                break;
            case "Modificar Usuario":
                if(TxtPass.getText().equals("")){
                    cv.creaVentanaNotificacionError("Debe ingresar una contraseña");
                }
                else if(TxtPass2.getText().equals("")){
                    cv.creaVentanaNotificacionError("Debe repetir la contraseña en el campo correspondiente");
                }
                else if(TxtPass.getText().equals(TxtPass2.getText())){
                    Usuario usu=CmbUsuarios.getValue();
                    usu.setPassword(TxtPass.getText());
                    try {
                        uc.actualizarUsuario(usu);
                        cv.creaVentanaNotificacionCorrecto();
                    } catch (Exception ex) {
                        cv.creaVentanaNotificacionError(ex.getMessage());
                    }                    
                }
                else{
                    cv.creaVentanaNotificacionError("Las contraseñas no coinciden");
                }
                break;
            case "Eliminar Usuario":
                if(CmbUsuarios.getValue()!=null){                    
                    try {
                        uc.EliminarUsuario(CmbUsuarios.getValue());
                        cv.creaVentanaNotificacion("Correcto", "Se ha borrado correctamente", 2, "tick");
                    } catch (Exception ex) {
                        cv.creaVentanaNotificacionError(ex.getMessage());
                    }
                }
                break;
        }    
    }
    
    public int verificaCampos(){
        if(TxtNombre.getText().equals("")){
            return -1;
        }
        else if(TxtPass.getText().equals("")){
            return -2;
        }
        else if(TxtPass2.getText().equals("")){
            return -3;
        }
        else if(CmbTipoUsuario.getValue()==null){
            return -4;
        }
        return 0;
    }
    
    public void mensajeVerificacion(int numero){
        ControlVentana cv=new ControlVentana();
        switch(numero){
            case -1:
                cv.creaVentanaNotificacionError("Debe ingresar un nombre de Usuario");
                break;
            case -2:
                cv.creaVentanaNotificacionError("Debe ingresar una contraseña");
                break;
            case -3:
                cv.creaVentanaNotificacionError("Debe repetir la contraseña en el campo correspondiente");
                break;
            case -4:
                cv.creaVentanaNotificacionError("Debe Elegir un Tipo se Usuario");
                break;
        }
    }
    
    public int traeTipoUsuario(String tipoUsuario){
        int numero=0;
        switch(tipoUsuario){
            case "Usuario":
                numero=1;
                break;
            case "Supervisor":
                numero=2;
                break;
            case "Super Usuario":
                numero=3;
                break;
        }
        return numero;
    }
    
    public void cambioCmb(){
        switch(CmbAccion.getValue()){
            case "Crear Usuario":
                TxtNombre.setVisible(true);
                LblNombre.setVisible(true);
                passtrue();
                CmbTipoUsuario.setVisible(true);
                LblTipoUsuario.setVisible(true);
                CmbUsuarios.setVisible(false);
                LblUsuarios.setVisible(false);
                BtnGuardar.setText("Guardar");
                break;
            case "Modificar Usuario":
                invisible();
                passtrue();
                BtnGuardar.setText("Modificar");
                break;
            case "Eliminar Usuario":
                invisible();
                TxtPass.setVisible(false);
                Lblpass.setVisible(false);
                TxtPass2.setVisible(false);
                LblPass2.setVisible(false);              
                BtnGuardar.setText("Eliminar");
                break;
        }    
    }
    
    public void invisible(){
        TxtNombre.setVisible(false);
        LblNombre.setVisible(false);
        CmbTipoUsuario.setVisible(false);
        LblTipoUsuario.setVisible(false);
        CmbUsuarios.setVisible(true);
        LblUsuarios.setVisible(true);
    }
    
    public void passtrue(){
        TxtPass.setVisible(true);
        Lblpass.setVisible(true);
        TxtPass2.setVisible(true);
        LblPass2.setVisible(true);
    }
}
