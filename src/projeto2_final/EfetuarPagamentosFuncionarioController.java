/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2_final;

import DAL.Cliente;
import DAL.Funcionario;
import DAL.Marcacao;
import DAL.Pagamento;
import DAL.Plano;
import DAL.Tipomarcacao;
import DAL.Tipopagamento;
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
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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
public class EfetuarPagamentosFuncionarioController implements Initializable {
    
    private static final String Persistence_UNIT_NAME ="Projeto2_FinalPU";
    private static EntityManagerFactory factory;
    
    
    @FXML
    private TableView<Cliente> tableClientes;
    
    @FXML
    private TableColumn<Cliente, String> col_nome;
    
    @FXML
    private TableColumn<Cliente, String> col_email;
    
    @FXML
    private TableColumn<Cliente, String> col_plano;
    
    @FXML
    private TableColumn<Cliente, String> col_preco;
    
    @FXML
    private ComboBox metodoPaga;
   

    @FXML
    private Text editarVazio;

    ObservableList<Cliente> clienteList = FXCollections.observableArrayList();
    
    ObservableList<String> metodoList = FXCollections.observableArrayList();
    
    ObservableList<Pagamento> pagamentoList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Cliente.findAll");
        for (Object d : q.getResultList()) {
                    
           // if(((Funcionario) d).getTipofuncionario().getIdTipofuncionario().intValue() == 2){
                BigDecimal id = ((Cliente) d).getIdcliente();
                String nome = ((Cliente) d).getNome();
                String email = ((Cliente) d).getEmail();
              //  String planoNome = ((Cliente) d).getIdplano().getNome();
             //   Double preco = ((Cliente) d).getIdplano().getPreco();
                
            //BigDecimal id = ((Cliente) d).getIdcliente();
         //   String nome = ((Cliente) d).getNome();
            BigInteger ncc = ((Cliente) d).getNcc();
            String data = ((Cliente) d).getDataNascimento();
            BigInteger telemovel = ((Cliente) d).getTelemovel();
            String rua = ((Cliente) d).getRua();
          //  String email = ((Cliente) d).getEmail();  
            String password = ((Cliente) d).getPassword();
            String sexo = ((Cliente) d).getSexo();
            String username = ((Cliente) d).getUsername();
            
            Plano pp = ((Cliente) d).getIdplano();
                

            clienteList.add(new Cliente(id, nome, username, telemovel, ncc, rua, email, data, sexo, pp));
            
            tableClientes.setItems(clienteList);
            
            
            
            //col_id.setCellValueFactory(new PropertyValueFactory<>("idfuncionario"));
            col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
          //  col_telemovel.setCellValueFactory(new PropertyValueFactory<>("telemovel"));
          //  col_nif.setCellValueFactory(new PropertyValueFactory<>("nif"));
         //   col_ncc.setCellValueFactory(new PropertyValueFactory<>("ncc"));
          //  col_morada.setCellValueFactory(new PropertyValueFactory<>("rua")); 
            col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
           // col_data.setCellValueFactory(new PropertyValueFactory<>("dataNascimento")); 
            col_plano.setCellValueFactory(cellData -> 
                new SimpleStringProperty(cellData.getValue().getIdplano().getNome()));
            col_preco.setCellValueFactory(cellData -> 
                new SimpleStringProperty(cellData.getValue().getIdplano().getPreco().toString()));
 
            }
        
        
        //factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
    //    EntityManager em = factory.createEntityManager();
        Query qq = em.createNamedQuery("Tipopagamento.findAll");
        for (Object dd : qq.getResultList()) {
                      
            String tipo = ((Tipopagamento) dd).getNome();
            
            
            
            //planoList.add(new Plano(nome));
           // planoList.removeAll(planoList);
            metodoList.addAll(tipo);
            
            System.out.println(tipo);
            //clientePlano.setItems(planoList);
            metodoPaga.getItems().setAll(metodoList);

            }   
        
    }

    public void tabelaClientes (ActionEvent event) throws IOException {

      //  horariosList.clear();
      //  consultaHorario.getItems().clear();
      //  consultaTabela.getItems().clear();
        
        
   //   if(consultaData.getValue() == null){
     //     dataVazia.setText("Preencha a data");
    //  }else{
        if(tableClientes.getSelectionModel().getSelectedItem() != null){
            
            Cliente c = tableClientes.getSelectionModel().getSelectedItem();
          
         // dataVazia.setText("");
        //  String nomeFuncionario = consultaFuncionario.getValue().toString();
          
       /*     for(String hora : horarios){

                horariosList.addAll(hora);
                consultaHorario.getItems().setAll(horariosList);
            }*/
          
      
        
        factory = Persistence.createEntityManagerFactory(Persistence_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Query q = em.createNamedQuery("Pagamento.findByCliente");
        q.setParameter("idcliente", c.getIdcliente());
        for (Object d : q.getResultList()) {
                    
           // if(((Marcacao) d).getData().equals(consultaData.getValue().toString())){
                BigDecimal id = ((Pagamento) d).getIdpagamento();
                Cliente idCliente = ((Pagamento) d).getIdcliente();
                String data = ((Pagamento) d).getData();
                Double preco = ((Pagamento) d).getPreco();

                Tipopagamento tipoPaga = ((Pagamento) d).getTipopagamento();

         
         pagamentoList.add(new Pagamento(id, preco, data, idCliente, tipoPaga)  );
            
    //    consultaTabela.setItems(marcacaoList);
                
         //   }      
       }
      }
   // }
        
    
    //    col_funcionario.setCellValueFactory(cellData -> 
      //      new SimpleStringProperty(cellData.getValue().getIdfuncionario().getNome()));
      //  col_data.setCellValueFactory(new PropertyValueFactory<>("data"));
      //  col_hora.setCellValueFactory(new PropertyValueFactory<>("horario"));
    }      
    
}
