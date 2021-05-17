/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import DAL.Aula;
import DAL.Cliente;
import DAL.Funcionario;
import DAL.Plano;
import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
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
public class CriarAulaController implements Initializable {
  
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    
    @FXML
    private TextField aulaNome;
    
    @FXML
    private TextField aulaDuracao;
    
    @FXML
    private ChoiceBox aulaFuncionario;
    
    @FXML
    private DatePicker aulaData;
    
    @FXML
    private JFXTimePicker aulaInicio;
    
    @FXML
    private JFXTimePicker aulaFim;
    
    @FXML
    private Text criarVazio;
    
    ObservableList<String> funcionarioList = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*clienteNome.setText("");
        clienteUsername.setText("");
        clienteMorada.setText("");
        clienteEmail.setText("");
        clienteNcc.setText("");
        clienteTelemovel.setText("");
        clientePassword.setText("");
        */
        
        
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Funcionario.findAll");
        for (Object d : q.getResultList()) {
                    
            if(((Funcionario) d).getTipofuncionario().getIdTipofuncionario().intValue() == 2){
                
                String nome = ((Funcionario) d).getNome();

                funcionarioList.addAll(nome);
                aulaFuncionario.getItems().setAll(funcionarioList);

            }   
        }
    }    
    
    
        public void criarAula(ActionEvent event) throws IOException {
        
      //  clienteData.setText("");
    
        if (aulaNome.getText().isEmpty() ) {
                criarVazio.setText("Preencha os campos");   
                
         }else{
            String nomeInput = aulaNome.getText();
            String funcionarioInput = aulaFuncionario.getValue().toString();
            String dataInput = aulaData.getValue().toString();
            String horaInicioInput = aulaInicio.getValue().toString();
            String horaFimInput = aulaFim.getValue().toString();
            
            Funcionario fid = descobrirId(funcionarioInput);
           
            factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
            EntityManager em = factory.createEntityManager();
            
            Aula a = new Aula();
            
            a.setNome(nomeInput);
            a.setIdfuncionario(fid);
            a.setDiasemana(dataInput);
            a.setHorarioinicial(horaInicioInput);
            a.setHorariofinal(horaFimInput);
          
            
         //   System.out.println("valor nome:" +nomeInput);
           // System.out.println("valor data:" +dataInput);
           // System.out.println("valor funcio:" +funcionarioInput);
           // System.out.println("valor horainicio:" +horaInicioInput);
          //  System.out.println("valor horafim:" +fid);
            //System.out.println("valor recebido idf:" +fid.getIdfuncionario());

           
           
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
            
            FXRouter.when("ConsultarAulas", "ConsultarAulas.fxml");     
            FXRouter.goTo("ConsultarAulas");
        }
    }
        
    private Funcionario descobrirId (String nomeF) {

        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Funcionario.findAll");   
        Funcionario f = null;

        for (Object d : q.getResultList()) {
            
            String nome = ((Funcionario) d).getNome(); 
            
            if (nome.equals(nomeF)) {
               System.out.println("valor id:" +((Funcionario) d).getIdfuncionario()); 
               f = (Funcionario) d;
            }
        }
        
        return f;
    }
    
    public void voltarMenu(ActionEvent event) throws IOException {
        
        FXRouter.when("MenuAdmin", "MenuAdmin.fxml");     
        FXRouter.goTo("MenuAdmin");
    }
    
}