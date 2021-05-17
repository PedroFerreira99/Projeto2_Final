/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import DAL.Cliente;
import DAL.Funcionario;
import DAL.Plano;
import com.github.fxrouter.FXRouter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Pedro Ferreira
 */
public class LoginController implements Initializable {
    
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    
    @FXML
    private TextField LoginEmail;
    
    @FXML
    private PasswordField LoginPassword;
    
    @FXML
    private Text emailVazio;
    
    @FXML
    private Text passwordVazio;
    
    @FXML
    private Text loginErrado;
    
    @FXML
    private void LoginBotao(ActionEvent event) throws IOException {
        emailVazio.setText("");
        passwordVazio.setText("");
        loginErrado.setText("");
        
    
        if (LoginEmail.getText().isEmpty() && LoginPassword.getText().isEmpty()) {
                emailVazio.setText("Preencha o email");
                passwordVazio.setText("Preencha a password");
            }
        else if (LoginEmail.getText().isEmpty()) {
                emailVazio.setText("Preencha o email");
            }
        else if(LoginPassword.getText().isEmpty()){
               passwordVazio.setText("Preencha a password");
           }
            
        else{
                 
            factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
            EntityManager em = factory.createEntityManager();
            Query q = em.createNamedQuery("Cliente.findAll");
            //Query q = em.createNamedQuery("Cliente.findByEmail");
           // q.setParameter("email", LoginEmail.getText());
            for (Object d : q.getResultList()) {
           
                String email = ((Cliente) d).getEmail();     
                String pass = ((Cliente) d).getPassword();
                   
                if (email.equals(LoginEmail.getText()) && pass.equals(LoginPassword.getText())) {
                   System.out.println(" \n - " + ((Cliente) d).getNome() );
               /*    Plano idplano = (((Cliente) d).getIdplano());
                   System.out.println("login certo");
                   System.out.println(idplano);
                   */
                   
                   Cliente c = new Cliente();
                   c =  (Cliente) d;
                   
                   
               /*   Parent page_parent = FXMLLoader.load(getClass().getResource("MenuCliente.fxml"));
                   Scene page_scene = new Scene(page_parent);
                   Stage a = (Stage) ((Node) event.getSource()).getScene().getWindow();
                   a.setScene(page_scene);
                   a.setUserData(c);
                   a.show();*/
               
                   FXRouter.when("MenuCliente", "MenuCliente.fxml");     
                   FXRouter.goTo("MenuCliente",c);
                 //FXRouter.when("login", "MenuCliente.fxml");
              /*   FXRouter.goTo("MenuAdmin.fxml"); 
                   System.out.println(c.getRua());
                   */
                }else{
                    loginErrado.setText("login errado");
                }
            }
         
                
                
                
            }
        
        
   /*     
         factory= Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        
        String x = LoginEmail.getText();
         System.out.println(x);
    //     ((Node) (event.getSource())).getScene().getWindow().hide();
    
    
        Query q = em.createNamedQuery("Tipofuncionario.findByIdTipofuncionario");
            q.setParameter("idTipofuncionario", "2");
            
        //    Object obj = q.getSingleResult();
            
        
        
        
        
        
        
             for(Object obj : q.getResultList()){
            System.out.println( ((Tipofuncionario)obj).getNome());
            
        }*/
          
   /*     Parent root = FXMLLoader.load(getClass().getResource("MenuAdmin.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
     
        stage.setScene(scene);
        stage.show();
               */
       
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
