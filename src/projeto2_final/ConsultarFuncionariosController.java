/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import DAL.Cliente;
import DAL.Funcionario;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;
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
public class ConsultarFuncionariosController implements Initializable {
    
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    
    @FXML
    private TableView<Funcionario> tableFuncionarios;
    
    @FXML
    private TableColumn<Funcionario, Integer> col_id;
    
    @FXML
    private TableColumn<Funcionario, String> col_nome;
    
    @FXML
    private TableColumn<Funcionario, Integer> col_telemovel;
    
    @FXML
    private TableColumn<Funcionario, Integer> col_nif;
   
    @FXML
    private TableColumn<Funcionario, Integer> col_ncc;
    
    @FXML
    private TableColumn<Funcionario, String> col_morada;
    
    @FXML
    private TableColumn<Funcionario, String> col_email;
    
    @FXML
    private TableColumn<Funcionario, String> col_data;
  

    ObservableList<Funcionario> funcionarioList = FXCollections.observableArrayList();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Funcionario.findAll");
        for (Object d : q.getResultList()) {
            
            if(((Funcionario) d).getTipofuncionario().getIdTipofuncionario().intValue() == 2){
           
//            System.out.println(((Funcionario) d).getTipofuncionario().getIdTipofuncionario().intValue());
            BigDecimal id = ((Funcionario) d).getIdfuncionario();
            String nome = ((Funcionario) d).getNome();
            BigInteger ncc = ((Funcionario) d).getNcc();
            BigInteger nif = ((Funcionario) d).getNif();
            String data = ((Funcionario) d).getDataNascimento();
            BigInteger telemovel = ((Funcionario) d).getTelemovel();
            String rua = ((Funcionario) d).getRua();
            String email = ((Funcionario) d).getEmail();  
            String password = ((Funcionario) d).getPassword();

            
            funcionarioList.add(new Funcionario(id, nome, telemovel, nif, ncc, rua, email, data)  );
            
            tableFuncionarios.setItems(funcionarioList);
            
            
            
            col_id.setCellValueFactory(new PropertyValueFactory<>("idfuncionario"));
            col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            col_telemovel.setCellValueFactory(new PropertyValueFactory<>("telemovel"));
            col_nif.setCellValueFactory(new PropertyValueFactory<>("nif"));
            col_ncc.setCellValueFactory(new PropertyValueFactory<>("ncc"));
            col_morada.setCellValueFactory(new PropertyValueFactory<>("rua"));  
            col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
            col_data.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));  
            
            }
        }   
    }
    
    
    public void voltarMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MenuAdmin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    public void paginaCriarFuncionarios(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CriarFuncionario.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
