package DAL;

import DAL.Aula;
import DAL.Cliente;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-03T03:06:50")
@StaticMetamodel(ClienteAula.class)
public class ClienteAula_ { 

    public static volatile SingularAttribute<ClienteAula, BigDecimal> idClienteaula;
    public static volatile SingularAttribute<ClienteAula, Cliente> idcliente;
    public static volatile SingularAttribute<ClienteAula, Aula> idaula;

}