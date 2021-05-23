/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import DAL.Cliente;
import DAL.Funcionario;
import DAL.Tipofuncionario;
import com.github.fxrouter.FXRouter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author Pedro Ferreira
 */
public class EditarFuncionarioController implements Initializable {
    
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    
    Funcionario f = (Funcionario) FXRouter.getData();
    
    @FXML
    private TextField funcionarioNome;
    
    @FXML
    private TextField funcionarioPassword;
    
    @FXML
    private TextField funcionarioMorada;
    
    @FXML
    private TextField funcionarioEmail;
    
    @FXML
    private TextField funcionarioNcc;
    
    @FXML
    private TextField funcionarioNif;
    
    @FXML
    private DatePicker funcionarioData;
    
    @FXML
    private TextField funcionarioTelemovel;
    
    @FXML
    private Text criarVazio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Funcionario.findByIdfuncionario");
        q.setParameter("idfuncionario", f.getIdfuncionario());
      
            Funcionario func = new Funcionario();
            func = (Funcionario) q.getSingleResult();
        
            BigDecimal id = ((Funcionario) func).getIdfuncionario();
            String nome = ((Funcionario) func).getNome();
            String password = ((Funcionario) func).getPassword();
            String morada = ((Funcionario) func).getRua();
            String email = ((Funcionario) func).getEmail();
            BigInteger ncc = ((Funcionario) func).getNcc();
            BigInteger nif = ((Funcionario) func).getNif();
            String data = ((Funcionario) func).getDataNascimento();
            BigInteger telemovel = ((Funcionario) func).getTelemovel();
           
           
            

            
            
       funcionarioNome.setText(nome);
       funcionarioPassword.setText(password);
       funcionarioMorada.setText(morada);
       funcionarioEmail.setText(email);
       funcionarioNcc.setText(ncc.toString() );
       funcionarioNif.setText(nif.toString());
       funcionarioData.setValue(LocalDate.parse(data));
       funcionarioTelemovel.setText(telemovel.toString());
    }    
    
    
    
    public void editarFuncionario(ActionEvent event) throws IOException {
        
      //  funcionarioData.setText("");
    
        if (funcionarioNome.getText().isEmpty() || funcionarioPassword.getText().isEmpty() || funcionarioMorada.getText().isEmpty() || funcionarioEmail.getText().isEmpty() || funcionarioNcc.getText().isEmpty() || funcionarioNif.getText().isEmpty() || funcionarioData.getValue()==null || funcionarioTelemovel.getText().isEmpty() ) {
                criarVazio.setText("Preencha os campos");   
                
         }else{
            String nomeInput = funcionarioNome.getText();
            String passwordInput = funcionarioPassword.getText();
            String moradaInput = funcionarioMorada.getText();
            String emailInput = funcionarioEmail.getText();
            BigInteger nccInput = new BigInteger(funcionarioNcc.getText());
            BigInteger nifInput = new BigInteger(funcionarioNif.getText());
            BigInteger telemovelInput = new BigInteger(funcionarioTelemovel.getText());
            
            Tipofuncionario tp;
            //System.out.println(plano);
            tp =new Tipofuncionario (new BigDecimal("2"));

            

            factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
            EntityManager em = factory.createEntityManager();
            
            Funcionario func = new Funcionario();
            func.setIdfuncionario(f.getIdfuncionario());
            func.setNome(nomeInput);
            func.setPassword(passwordInput);
            func.setRua(moradaInput);
            func.setEmail(emailInput);
            func.setNcc(nccInput);
            func.setNif(nifInput);
            func.setDataNascimento(funcionarioData.getValue().toString());
            func.setTelemovel(telemovelInput);
            func.setTipofuncionario(tp);
            
          
           
            em.getTransaction().begin();
            em.merge(func);
            em.getTransaction().commit();
            
            
            FXRouter.when("ConsultarFuncionarios", "ConsultarFuncionarios.fxml");     
            FXRouter.goTo("ConsultarFuncionarios");
        }
    }
    
    
}
