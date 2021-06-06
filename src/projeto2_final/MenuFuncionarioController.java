/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import DAL.Cliente;
import DAL.Funcionario;
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
public class MenuFuncionarioController implements Initializable {
    
    Funcionario f = (Funcionario) FXRouter.getData(); 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    public void consultClientes(ActionEvent event) throws IOException {
       
        FXRouter.when("ConsultarClientesFuncionario", "ConsultarClientesFuncionario.fxml");     
        FXRouter.goTo("ConsultarClientesFuncionario" , f);
    }
    
    public void meuHorario(ActionEvent event) throws IOException {
       
        FXRouter.when("MeuHorario", "MeuHorario.fxml");     
        FXRouter.goTo("MeuHorario" , f);
    }
    
    public void minhasAulasFuncionario(ActionEvent event) throws IOException {
       
        FXRouter.when("ConsultarMinhasAulasFuncionario", "ConsultarMinhasAulasFuncionario.fxml");     
        FXRouter.goTo("ConsultarMinhasAulasFuncionario" , f);
    }
    
    public void consultarRegistosMarcacoes(ActionEvent event) throws IOException {
       
        FXRouter.when("ConsultarRegistosMarcacoes", "ConsultarRegistosMarcacoes.fxml");     
        FXRouter.goTo("ConsultarRegistosMarcacoes" , f);
    }
    
    public void consultarConsultasEfetuadas(ActionEvent event) throws IOException {
       
        FXRouter.when("ConsultarConsultasEfetuadas", "ConsultarConsultasEfetuadas.fxml");     
        FXRouter.goTo("ConsultarConsultasEfetuadas" , f);
    }
    
    public void efetuarPagamentoCliente(ActionEvent event) throws IOException {
       
        FXRouter.when("EfetuarPagamentosFuncionario", "EfetuarPagamentosFuncionario.fxml");     
        FXRouter.goTo("EfetuarPagamentosFuncionario" , f);
    }
}
