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
import java.awt.event.MouseEvent;
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
import javafx.scene.control.Label;
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
public class ConsultarClientesController implements Initializable {
    
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    
    
    @FXML
    private TableView<Cliente> tableClientes;
    
    @FXML
    private TableColumn<Cliente, Integer> col_id;
    
    @FXML
    private TableColumn<Cliente, String> col_nome;
    
    @FXML
    private TableColumn<Cliente, String> col_username;
    
    @FXML
    private TableColumn<Cliente, Integer> col_telemovel;
    
    @FXML
    private TableColumn<Cliente, Integer> col_ncc;
    
    @FXML
    private TableColumn<Cliente, String> col_morada;
    
    @FXML
    private TableColumn<Cliente, String> col_email;
    
    @FXML
    private TableColumn<Cliente, String> col_data;
    
    @FXML
    private TableColumn<Cliente, String> col_sexo;
    
    
    ObservableList<Cliente> clienteList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Cliente.findAll");
        for (Object d : q.getResultList()) {
           
            
            BigDecimal id = ((Cliente) d).getIdcliente();
            String nome = ((Cliente) d).getNome();
            BigInteger ncc = ((Cliente) d).getNcc();
            String data = ((Cliente) d).getDataNascimento();
            BigInteger telemovel = ((Cliente) d).getTelemovel();
            String rua = ((Cliente) d).getRua();
            String email = ((Cliente) d).getEmail();  
            String password = ((Cliente) d).getPassword();
            String sexo = ((Cliente) d).getSexo();
            String username = ((Cliente) d).getUsername();
            
            Plano pp = ((Cliente) d).getIdplano();
            
            
            clienteList.add(new Cliente(id, nome, username, telemovel, ncc,rua, email, data, sexo, pp)  );
            
            tableClientes.setItems(clienteList);
           //  clienteList.add(new Cliente( id, nome,ncc,data,telemovel,rua,email,password,sexo,username)  );
            
        //    clienteList.add(new Cliente( ((Cliente) d).getIdcliente(), ((Cliente) d).getNome()    )  );
                    
           //     if (email.equals(LoginEmail.getText()) && pass.equals(LoginPassword.getText())) {
             //      System.out.println(" \n - " + ((Cliente) d).getNome() );
               /*    Plano idplano = (((Cliente) d).getIdplano());
                   System.out.println("login certo");
                   System.out.println(idplano);
                   */
                   
               //    Cliente c = new Cliente();
                 //  c =  (Cliente) d;
                   
                   
               /*   Parent page_parent = FXMLLoader.load(getClass().getResource("MenuCliente.fxml"));
                   Scene page_scene = new Scene(page_parent);
                   Stage a = (Stage) ((Node) event.getSource()).getScene().getWindow();
                   a.setScene(page_scene);
                   a.setUserData(c);
                   a.show();*/
               
             //      FXRouter.when("MenuCliente", "MenuCliente.fxml");     
              //     FXRouter.goTo("MenuCliente",c);
                 //FXRouter.when("login", "MenuCliente.fxml");
              /*   FXRouter.goTo("MenuAdmin.fxml"); 
                   System.out.println(c.getRua());
                   */
               // }else{
                   // loginErrado.setText("login errado");
                }
            //}
        
        
        
        
        col_id.setCellValueFactory(new PropertyValueFactory<>("idcliente"));
        col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_telemovel.setCellValueFactory(new PropertyValueFactory<>("telemovel"));
        col_ncc.setCellValueFactory(new PropertyValueFactory<>("ncc"));
        col_morada.setCellValueFactory(new PropertyValueFactory<>("rua"));  
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_data.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));  
        col_sexo.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getIdplano().getNome()));
        
        
        
        
        
        
        
        // TODO
    }   
    
    
    
    public void voltarMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MenuAdmin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    public void paginaCriarClientes(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CriarCliente.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
