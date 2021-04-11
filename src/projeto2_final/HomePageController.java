/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import DAL.Plano;
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
import javafx.scene.control.Label;
import javafx.scene.text.Text;
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
    
   
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
         System.out.println("You clicked me!");
  
        int c=2;
       
        
        
        factory= Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        
        //Query q = em.createNamedQuery("Tipofuncionario.findAll");
     
        
        
            Query q = em.createNamedQuery("Tipofuncionario.findByIdTipofuncionario");
            q.setParameter("idTipofuncionario", c);
            
        //    Object obj = q.getSingleResult();
            
             for(Object obj : q.getResultList()){
            System.out.println( ((Tipofuncionario)obj).getNome());
            
        }
          //  tp = ((Tipofuncionario)obj)
        //  for(Object uti : q.getSingleResult()){
      //  System.out.println(obj );
            
    //    }
          
      
          
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
