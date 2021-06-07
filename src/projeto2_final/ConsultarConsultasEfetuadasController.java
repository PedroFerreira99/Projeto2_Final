/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import DAL.Avaliacao;
import DAL.Cliente;
import DAL.Funcionario;
import DAL.Horario;
import DAL.Marcacao;
import DAL.Tipomarcacao;
import com.github.fxrouter.FXRouter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class ConsultarConsultasEfetuadasController implements Initializable {
    
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    
    Funcionario f = (Funcionario) FXRouter.getData(); 
      
    @FXML
    private TableView<Avaliacao> tableMarcacoes;
    
    @FXML
    private TableColumn<Avaliacao, Integer> col_id;
    
    @FXML
    private TableColumn<Avaliacao, String> col_cliente;
    
    @FXML
    private TableColumn<Avaliacao, String> col_emailcliente;
    
    @FXML
    private TableColumn<Avaliacao, String> col_data;
    
    @FXML
    private TableColumn<Avaliacao, String> col_horario;
    
    @FXML
    private TableColumn<Avaliacao, String> col_funcionario;
    
    @FXML
    private Text editarVazio;
    
    @FXML
    private TextField nomeProcurar;
    
    @FXML
    private Text nomeUtilizador;
        
    ObservableList<Avaliacao> aulaList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        nomeUtilizador.setText(f.getNome());
        
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Avaliacao.findAll");
        for (Object d : q.getResultList()) {
            
            if(((Avaliacao) d).getIdmarcacao().getIdfuncionario().getIdfuncionario().intValue() == f.getIdfuncionario().intValue()){
           
            
            BigDecimal id = ((Avaliacao) d).getIdavaliacao();
            Marcacao marcacao = ((Avaliacao) d).getIdmarcacao();
            
            aulaList.add(new Avaliacao(id , marcacao)  );
            tableMarcacoes.setItems(aulaList);

                }
            }

        col_id.setCellValueFactory(new PropertyValueFactory<>("idavaliacao"));
        
        col_cliente.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getIdmarcacao().getIdcliente().getNome()));
        col_emailcliente.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getIdmarcacao().getIdcliente().getEmail()));
        
        col_data.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getIdmarcacao().getData()));
        col_horario.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getIdmarcacao().getHorario())); 

        col_funcionario.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getIdmarcacao().getIdfuncionario().getNome()));

    } 
    
    public void verConsulta(ActionEvent event) throws IOException {
        
    if (tableMarcacoes.getSelectionModel().getSelectedItem() != null) {
        Avaliacao avaliacao = tableMarcacoes.getSelectionModel().getSelectedItem();

        FXRouter.when("VisualizarConsulta", "VisualizarConsulta.fxml");     
        FXRouter.goTo("VisualizarConsulta", avaliacao);
        
     //   System.out.println("cliente:" +h.getIdfuncionario().getIdfuncionario());
    }else{
        editarVazio.setText("Selecione uma consulta");
    }
    }

    
   // nomeProcurar.textProperty().addListener(new ChangeListener<String>() {
  /*  @Override
    public void changed(ObservableValue<? extends String> observable,
            String oldValue, String newValue) {

        System.out.println(" Text Changed to  " + newValue + ")\n");
    }
    });*/
  /* public void procurarNome(){
    
    nomeProcurar.textProperty().addListener((obs, oldText, newText) -> {
    //System.out.println("Text changed from "+oldText+" to "+newText);
    // ...
    tableMarcacoes.getItems().clear();
            
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Avaliacao.findLikeNome");
        q.setParameter("idcliente", new BigDecimal(newText) );
        for (Object d : q.getResultList()) {
    
            
            BigDecimal id = ((Avaliacao) d).getIdavaliacao();
            Marcacao marcacao = ((Avaliacao) d).getIdmarcacao();

            
            aulaList.add(new Avaliacao(id , marcacao)  );
            
            tableMarcacoes.setItems(aulaList);

                }
            //}
      //  Funcionario sss = new Funcionario();
        
        
        
        col_id.setCellValueFactory(new PropertyValueFactory<>("idavaliacao"));
        
        col_cliente.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getIdmarcacao().getIdcliente().getNome()));
        
        col_emailcliente.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getIdmarcacao().getIdcliente().getEmail()));
        
        col_data.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getIdmarcacao().getData()));
        col_horario.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getIdmarcacao().getHorario())); 
       // col_horaFim.setCellValueFactory(new PropertyValueFactory<>("horariofinal"));
        col_funcionario.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getIdmarcacao().getIdfuncionario().getNome()));
        
      //  procurarNome();
    });
    
    
    
    }*/
    
    public void voltarMenu(ActionEvent event) throws IOException {    
        FXRouter.when("MenuFuncionario", "MenuFuncionario.fxml");     
        FXRouter.goTo("MenuFuncionario" , f);
    }
}
