/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import DAL.Aula;
import DAL.Cliente;
import DAL.Funcionario;
import com.github.fxrouter.FXRouter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class ConsultarAulasController implements Initializable {
    
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    
    
    @FXML
    private TableView<Aula> tableAulas;
    
    @FXML
    private TableColumn<Aula, Integer> col_id;
    
    @FXML
    private TableColumn<Aula, String> col_nome;
    
    @FXML
    private TableColumn<Aula, String> col_data;
    
    @FXML
    private TableColumn<Aula, Integer> col_horaInicio;
    
    @FXML
    private TableColumn<Aula, Integer> col_horaFim;
    
    @FXML
    private TableColumn<Aula, String> col_funcionario;
        
    ObservableList<Aula> aulaList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Aula.findAll");
        for (Object d : q.getResultList()) {
           
            
            BigInteger id = ((Aula) d).getIdaula();
            String nome = ((Aula) d).getNome();
            String data = ((Aula) d).getDiasemana();
            String horainicio = ((Aula) d).getHorarioinicial();
            String horafim = ((Aula) d).getHorariofinal();
            
            String nomeF =  ((Aula) d).getIdfuncionario().getNome().toString();
            
            System.out.println("nome:" +nomeF);
//            BigDecimal idFunc = new BigDecimal(  ((Aula) d).getIdfuncionario().toString());
//            String nomeFunc = ((Aula) d).getIdfuncionario().getNome();
            
          //  Funcionario ff = new Funcionario( new BigDecimal( ((Aula) d).getIdfuncionario() ) );
          //  
            //Funcionario ff = new Funcionario(  );
            //ff.setIdfuncionario(new BigDecimal(  id ) );
            //ff.setNome(nomeF);
            
            Funcionario funcionario = ((Aula) d).getIdfuncionario();
            funcionario.setNome(nomeF);

            
            aulaList.add(new Aula(id, nome, data, horainicio,horafim, funcionario)  );
            
            tableAulas.setItems(aulaList);

                }
            //}
        Funcionario sss = new Funcionario();
        
        
        
        col_id.setCellValueFactory(new PropertyValueFactory<>("idaula"));
        col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_data.setCellValueFactory(new PropertyValueFactory<>("diasemana"));
        col_horaInicio.setCellValueFactory(new PropertyValueFactory<>("horarioinicial"));  
        col_horaFim.setCellValueFactory(new PropertyValueFactory<>("horariofinal"));
        col_funcionario.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getIdfuncionario().getNome()));
    }   
    
    
    public void paginaCriarAulas(ActionEvent event) throws IOException {
        
        FXRouter.when("CriarAula", "CriarAula.fxml");     
        FXRouter.goTo("CriarAula");
    }
    
        public void voltarMenu(ActionEvent event) throws IOException {
        
        FXRouter.when("MenuAdmin", "MenuAdmin.fxml");     
        FXRouter.goTo("MenuAdmin");
    }
    
}