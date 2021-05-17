/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;


import DAL.Tipofuncionario;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Pedro Ferreira
 */
public class HomePageController implements Initializable {
    
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    
    @FXML
    private Label label;
    
    
   
    
 //   String username = (String) FXRouter.getData(); 
    
   
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
  
        
       // Stage sss = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
       // Parent page_parent = FXMLLoader.load(getClass().getResource("EscolherLogin.fxml"));
       // Scene page_scene = new Scene(page_parent);
       // Stage a = (Stage) ((Node) event.getSource()).getScene().getWindow();
       // a.setScene(page_scene);
       // a.show();
        
        
       
       
     //  Parent root = FXMLLoader.load(getClass().getResource("MenuCliente.fxml"));

     //Stage stage = new Stage();
     
     //   Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       // Scene scene = new Scene(FXMLLoader.load(getClass().getResource("EscolherLogin.fxml")));
     //   stage.setScene(scene);
      //  stage.show();
        
        //FXRouter.bind(this, stage,1100,800);
       // FXRouter.bind(this, stage ,1100,800);
      
        FXRouter.when("EscolherLogin", "EscolherLogin.fxml");     
        FXRouter.goTo("EscolherLogin");
        
        
        

        
        
        
        
      /*  int c=2;
        
        factory= Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        
        //Query q = em.createNamedQuery("Tipofuncionario.findAll");
     
        
        
        Query q = em.createNamedQuery("Tipofuncionario.findAll");
        //q.setParameter("idTipofuncionario", c);
            
        // Object obj = q.getSingleResult();
            
        for(Object obj : q.getResultList()){
            System.out.println( ((Tipofuncionario)obj).getNome());
        }
            */
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
