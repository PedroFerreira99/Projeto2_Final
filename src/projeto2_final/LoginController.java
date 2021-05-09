/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import DAL.Tipofuncionario;
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
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
    private void LoginBotao(ActionEvent event) throws IOException {
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
          
        Parent root = FXMLLoader.load(getClass().getResource("MenuAdmin.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
     
        stage.setScene(scene);
        stage.show();
               
       
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
