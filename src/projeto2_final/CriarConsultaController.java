/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import DAL.Aula;
import DAL.Cliente;
import DAL.Funcionario;
import DAL.Marcacao;
import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
public class CriarConsultaController implements Initializable {
    
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    private Funcionario ff ;
    
    String[] horarios= {"02:00", "02:30", "03:00", "03:30", "04:00" };
    
    Cliente c = (Cliente) FXRouter.getData(); 
    
    @FXML
    private JFXComboBox consultaFuncionario;
    
 //   @FXML
   // private JFXComboBox consultaHora;
    
    @FXML
    private Text dataVazia;
    
    @FXML
    private JFXComboBox consultaHorario;
    
    @FXML
    private TableView consultaTabela;
    
    @FXML
    private DatePicker consultaData;
    
    @FXML
    private TableColumn<Marcacao, String> col_data;
    
    @FXML
    private TableColumn<Marcacao, String> col_funcionario;
        
    @FXML
    private TableColumn<Marcacao, String> col_hora;
    
    ObservableList<String> funcionarioList = FXCollections.observableArrayList();
    
    ObservableList<String> horariosList = FXCollections.observableArrayList();
    
     ObservableList<Marcacao> marcacaoList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Funcionario.findAll");
        for (Object d : q.getResultList()) {
                    
            if(((Funcionario) d).getTipofuncionario().getIdTipofuncionario().intValue() == 2){
                
                String nome = ((Funcionario) d).getNome();
                String email = ((Funcionario) d).getEmail();
                

                funcionarioList.addAll(nome);
                consultaFuncionario.getItems().setAll(funcionarioList);

            }   
        }
        
    /*    for(String hora : horarios){
            horariosList.addAll(hora);
            consultaHorario.getItems().setAll(horariosList);
            //System.out.println(hora);
        }*/
    }    
    
    
    public void tabelaHorarios (ActionEvent event) throws IOException {
        
        
       // System.out.println("NOME:" +consultaFuncionario.getValue().toString());
        
        
        //String dataFuncionario = consultaData.getValue().toString();
        //String nomeFuncionario = consulta.getValue().toString();
        horariosList.clear();
        consultaHorario.getItems().clear();
        consultaTabela.getItems().clear();
        
        
      if(consultaData.getValue() == null){
          dataVazia.setText("Preencha a data");
      }else{
          if(consultaFuncionario.getValue() != null ){
          
          dataVazia.setText("");
          String nomeFuncionario = consultaFuncionario.getValue().toString();
          
      
        
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Marcacao.findByFuncionarioHora");
        q.setParameter("idfuncionario", nomeFuncionario);
        for (Object d : q.getResultList()) {
                    
            if(((Marcacao) d).getData().equals(consultaData.getValue().toString())){
                BigDecimal id = ((Marcacao) d).getIdmarcacao();
                String data = ((Marcacao) d).getData();
                String horario = ((Marcacao) d).getHorario();
                Cliente idCliente = ((Marcacao) d).getIdcliente();
                Funcionario idFuncionario = ((Marcacao) d).getIdfuncionario();

              //  marcacaoList.addAll(nome);
              //  consultaTabela.getItems().setAll(marcacaoList);

           
         
         marcacaoList.add(new Marcacao(id,data, horario,idCliente, idFuncionario)  );
            
        consultaTabela.setItems(marcacaoList);
        
        
            for(String hora : horarios){
            //horariosList.addAll(hora);
            //consultaHorario.getItems().setAll(horariosList);
            //System.out.println(hora);
              //  System.out.println("tttttt" +((Marcacao) d).getHorario());
                if(!((Marcacao) d).getHorario().equals(hora) ){
                    //System.out.println("horasss:" +hora);
                    horariosList.addAll(hora);
                    consultaHorario.getItems().setAll(horariosList);
                
                }
            }
        
            
        
            }else{
                
                for(String hora : horarios){
                    
                    horariosList.addAll(hora);
                    consultaHorario.getItems().setAll(horariosList);
                }
                
            }
       }
      }
    }
        
       // col_funcionario.setCellValueFactory(new PropertyValueFactory<>("idfuncionario"));
        col_funcionario.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getIdfuncionario().getNome()));
        col_data.setCellValueFactory(new PropertyValueFactory<>("data"));
        col_hora.setCellValueFactory(new PropertyValueFactory<>("horario"));
    }  
    
    public void marcarConsulta (ActionEvent event) throws IOException {
        
        String nomeFuncionario = consultaFuncionario.getValue().toString();
        String data = consultaData.getValue().toString();
        String horario = consultaHorario.getValue().toString();
        Funcionario fid = descobrirId(nomeFuncionario);
        
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
            
        Marcacao marca = new Marcacao();
            
            marca.setIdfuncionario(fid);
            marca.setIdcliente(c);
            marca.setData(data);
            marca.setHorario(horario);
            
        
        em.getTransaction().begin();
        em.persist(marca);
        em.getTransaction().commit();

        FXRouter.when("MenuCliente", "MenuCliente.fxml");     
        FXRouter.goTo("MenuCliente", c);
           
                
    
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
    
}
