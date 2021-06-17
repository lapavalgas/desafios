import dao.AluguelDAO;
import dao.ClienteDAO;
import dao.jdbc.AluguelDAOimpl;
import dao.jdbc.ClienteDAOImpl;
import dao.jdbc.FilmeDAOimpl;
import entidades.Aluguel;
import entidades.Cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
	
	//url, user e password para o jdbc:postgresql://localhost/banco_de_dados
	private final static String url = "jdbc:postgresql://localhost/banco_de_dados";
	private final static String user = "postgres";
	private final static String password = "entrar";
	
    public static void main(String[] args) throws Exception {
        Connection conn = null;
        try {
        	try {
        		Class.forName("org.postgresql.Driver");
        	} catch (ClassNotFoundException e) {
        		e.printStackTrace();
        		System.out.println("Error - Driver: "+e.getMessage());       		
        	}
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            
            //Demonstrar o funcionamento aqui
            ClienteDAO clienteDAO = new ClienteDAOImpl();
            AluguelDAOimpl aluguelDAO = new AluguelDAOimpl();
            FilmeDAOimpl filmeDAO = new FilmeDAOimpl();
            
            Cliente clnt = new Cliente();
                      
            System.out.println(clienteDAO.list(conn));
            System.out.println(aluguelDAO.list(conn));
            System.out.println(filmeDAO.list(conn));
            
            //clnt.setNome("Rafael");
            //clienteDAO.insert(conn, clnt);
            
            System.out.println(clienteDAO.list(conn));
            
            //clienteDAO.delete(conn, 12); //5
            
            //System.out.println(clienteDAO.list(conn));
            
            //FALHEI NA PARTE DE SQL em que deveria me atentar para a chave estrangeira. 
            //aluguelDAO.delete(conn, aluguelDAO.find(conn, 1));  
            
            //System.out.println(aluguelDAO.list(conn));
            
            
            //SQL
            //1
            //1 FILME MAIS ANTIGO
            //SELECT *
            //FROM en_filme
            //ORDER BY data_lancamento
            //LIMIT 1;
            //
                  
            //2
            //2 JOIN
            // Compreendo a utilização do JOIN no SQL, mas a integração com banco de dados por si só já foi um desafio para mim.
            //SELECT 
            //FROM 
            //INNER JOIN
            // No entanto não tenho pratica iniciei o estudo de SQL esse semestre, e o estudo de integração com base de dados semana passada

            //3
            //3 DESAFIO
            // idem
            
            
  
        } catch (SQLException e) {
        	e.printStackTrace();
        	System.out.println("Error - Abrir Conexão: "+e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
            	e.printStackTrace();
            	System.out.println("Error - Fechar Conexão: "+e.getMessage());
            }
        }
        System.out.println("Fim do teste.");
    }
    
    

}