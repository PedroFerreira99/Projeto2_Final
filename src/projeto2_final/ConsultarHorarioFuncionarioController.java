/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import com.github.fxrouter.FXRouter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Pedro Ferreira
 */
public class ConsultarHorarioFuncionarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public void criarHorario(ActionEvent event) throws IOException {
            
        FXRouter.when("CriarHorarioFuncionario", "CriarHorarioFuncionario.fxml");     
        FXRouter.goTo("CriarHorarioFuncionario");
    }
    
}
