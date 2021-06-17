package dao.jdbc;


import dao.AluguelDAO;
import entidades.Aluguel;
import entidades.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class AluguelDAOimpl implements AluguelDAO {

	@Override
	public void insert(Connection conn, Aluguel aluguel) throws Exception {	   	
		PreparedStatement myStmt = conn.prepareStatement("INSERT INTO en_aluguel (id_aluguel, id_cliente, data_aluguel, valor) VALUES (?, ?, ?, ?)");
		
		Integer idAluguel = this.getNextId(conn);

	    myStmt.setInt(1, idAluguel);
	    myStmt.setInt(2, aluguel.getCliente().getIdCliente());
	    myStmt.setObject(3, aluguel.getDataAluguel());
	    myStmt.setFloat(4, aluguel.getValor());
	    
	    myStmt.execute();
	    conn.commit();
	        
	    aluguel.setIdAluguel(idAluguel);
	}


	@Override
	public void edit(Connection conn, Aluguel aluguel) throws Exception {
		PreparedStatement myStmt = conn.prepareStatement("UPDATE en_aluguel SET data_aluguel = CURRENT_DATE, valor = (?) WHERE id_aluguel = (?)");
		
		myStmt.setFloat(1, aluguel.getValor());
		myStmt.setInt(2,  aluguel.getIdAluguel());
		
		myStmt.execute();
		conn.commit();
		
		// Considerando o ClienteDAOimpl.java não possibilitei alterar o ID do Cliente e Aluguel.
		// Sendo uma alteração no tempo considerei alterar a data e o valor
		// Com mais tempo consideraria criar uma nova coluna/tabela (para fazer referencia as alterações) e um novo registro; mantendo registro de todas as alterações.
	}
	

	@Override
	public void delete(Connection conn, Aluguel aluguel) throws Exception {
        PreparedStatement myStmt = conn.prepareStatement("DELETE FROM en_aluguel where id_cliente = ?");
        
        myStmt.setInt(1, aluguel.getCliente().getIdCliente()); 

        myStmt.execute();
        conn.commit();
		
	}

	@Override
	public Aluguel find(Connection conn, Integer idAluguel) throws Exception {
		PreparedStatement myStmt = conn.prepareStatement("SELECT * FROM en_aluguel WHERE id_cliente = ?");
		
		myStmt.setInt(1, idAluguel);
		ResultSet myRs = myStmt.executeQuery();

        if (!myRs.next()) {
            return null;
        }    	
    	//objetos
    	Aluguel aluguel = new Aluguel();
    	aluguel.setIdAluguel(myRs.getInt("id_aluguel"));
    	
    	//List<Filme> filmes = myRs.getInt(filmes);   
    	
    	//objeto cliente
    	Cliente cliente = new Cliente();
    	ClienteDAOImpl getCliente = new ClienteDAOImpl();
		cliente.setIdCliente(myRs.getInt("id_cliente"));
		cliente = getCliente.find(conn, cliente.getIdCliente());
		aluguel.setCliente(cliente);
		
		//data -- primeira vez que utilizo datas, ainda estou confuso!  :(
		String dataAluguelStr = myRs.getString("data_aluguel");
		LocalDate dataAluguelLD = LocalDate.now();
		dataAluguelLD = LocalDate.parse(dataAluguelStr);
		Instant instant = Instant.from(dataAluguelLD.atStartOfDay(ZoneId.of("GMT")));
		Date dataAluguel = Date.from(instant);
		aluguel.setDataAluguel(dataAluguel);
		//atributos
		aluguel.setValor(myRs.getFloat("valor"));   	           		   		  
		
        return aluguel;
    }

	@Override
	public Collection<Aluguel> list(Connection conn) throws Exception {
		
		PreparedStatement myStmt = conn.prepareStatement("SELECT * FROM en_aluguel ORDER BY id_cliente");
		ResultSet myRs = myStmt.executeQuery();
		
		Collection<Aluguel> items = new ArrayList<>();	

        while (myRs.next()) {
        	
        	//objetos
        	Aluguel aluguel = new Aluguel();
        	aluguel.setIdAluguel(myRs.getInt("id_aluguel"));
        	
        	//List<Filme> filmes = myRs.getInt(filmes);   
        	
        	//objeto cliente
        	Cliente cliente = new Cliente();
        	ClienteDAOImpl getCliente = new ClienteDAOImpl();
    		cliente.setIdCliente(myRs.getInt("id_cliente"));
    		cliente = getCliente.find(conn, cliente.getIdCliente());
    		aluguel.setCliente(cliente);
    		
    		//data -- primeira vez que utilizo datas, ainda estou confuso!  :(
    		String dataAluguelStr = myRs.getString("data_aluguel");
    		LocalDate dataAluguelLD = LocalDate.now();
    		dataAluguelLD = LocalDate.parse(dataAluguelStr);
    		Instant instant = Instant.from(dataAluguelLD.atStartOfDay(ZoneId.of("GMT")));
    		Date dataAluguel = Date.from(instant);
    		aluguel.setDataAluguel(dataAluguel);
    		//atributos
    		aluguel.setValor(myRs.getFloat("valor"));   	           		   		
            
    		//alimenta lista
    		items.add(aluguel);
        }
        return items;
	}
	
	@Override
	public Integer getNextId(Connection conn) throws Exception {
        PreparedStatement myStmt = conn.prepareStatement("select nextval('seq_en_aluguel')");
        ResultSet rs = myStmt.executeQuery();
        rs.next();
        return rs.getInt(1);		
	}	
	
}
