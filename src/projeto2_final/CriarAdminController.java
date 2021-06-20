/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import BCrypt.BCrypt;
import DAL.Funcionario;
import DAL.Tipofuncionario;
import com.github.fxrouter.FXRouter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author Pedro Ferreira
 */
public class CriarAdminController implements Initializable {
    
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    
    @FXML
    private TextField adminNome;
    
    @FXML
    private TextField adminPassword;
    
    @FXML
    private TextField adminMorada;
    
    @FXML
    private TextField adminEmail;
    
    @FXML
    private TextField adminNcc;
    
    @FXML
    private TextField adminNif;
    
    @FXML
    private DatePicker adminData;
    
    @FXML
    private TextField adminTelemovel;
    
    @FXML
    private Text criarVazio;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    public void criarAdmin(ActionEvent event) throws IOException {
        
      //  funcionarioData.setText("");
    
        if (adminNome.getText().isEmpty() || adminPassword.getText().isEmpty() || adminMorada.getText().isEmpty() || adminEmail.getText().isEmpty() || adminNcc.getText().isEmpty() || adminNif.getText().isEmpty() || adminData.getValue()==null || adminTelemovel.getText().isEmpty() ) {
                criarVazio.setText("Preencha os campos");   
                
         }else{
            String nomeInput = adminNome.getText();
            String passwordInput = BCrypt.hashpw(adminPassword.getText(), BCrypt.gensalt());
            String moradaInput = adminMorada.getText();
            String emailInput = adminEmail.getText();
            BigInteger nccInput = new BigInteger(adminNcc.getText());
            BigInteger nifInput = new BigInteger(adminNif.getText());
            BigInteger telemovelInput = new BigInteger(adminTelemovel.getText());
            
            Tipofuncionario tp;
            //System.out.println(plano);
            tp =new Tipofuncionario (new BigDecimal("1"));
       // (new BigInteger("1"));
            

            factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
            EntityManager em = factory.createEntityManager();
            
            Funcionario f = new Funcionario();
            f.setNome(nomeInput);
            f.setPassword(passwordInput);
            f.setRua(moradaInput);
            f.setEmail(emailInput);
            f.setNcc(nccInput);
            f.setNif(nifInput);
            f.setDataNascimento(adminData.getValue().toString());
            f.setTelemovel(telemovelInput);
            f.setTipofuncionario(tp);
            
          
           
           
            em.getTransaction().begin();
            em.persist(f);
            em.getTransaction().commit();
            
            FXRouter.when("ConsultarAdmins", "ConsultarAdmins.fxml");     
            FXRouter.goTo("ConsultarAdmins");
        }
    }
    
    public void voltarMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MenuAdmin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void close(ActionEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    
}
