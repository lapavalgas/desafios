package dao.jdbc;

import dao.FilmeDAO;
import entidades.Filme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class FilmeDAOimpl implements FilmeDAO {

	@Override
	public void insert(Connection conn, Filme filme) throws Exception {
		PreparedStatement myStmt = conn.prepareStatement("INSERT INTO en_filme (data_lancamento, nome, descrição) VALUES (?, ?, ?)");
		
		Integer idFilme = this.getNextId(conn);	    
		
	    myStmt.setObject(1, filme.getDataLancamento());
	    myStmt.setString(2, filme.getNome());
	    myStmt.setString(3, filme.getDescricao());
    
	    myStmt.execute();
	    conn.commit();
	        
	    filme.setIdFilme(idFilme);
	}


	@Override
	public void edit(Connection conn, Filme filme) throws Exception {
		PreparedStatement myStmt = conn.prepareStatement("UPDATE en_filme SET data_lancamento = (?), nome = (?), descricao = (?) WHERE id_filme = (?)");
		
		myStmt.setObject(1, filme.getDataLancamento());
		myStmt.setString(2, filme.getNome());
		myStmt.setString(3, filme.getDescricao());
		myStmt.setInt(4,  filme.getIdFilme());
		
		myStmt.execute();
		conn.commit();
	}
	
	@Override
	public void delete(Connection conn, Integer idFilme) throws Exception {
        PreparedStatement myStmt = conn.prepareStatement("DELETE FROM en_filme where id_filme = (?)");
        
        myStmt.setInt(1, idFilme);

        myStmt.execute();
        conn.commit();	
	}
	
	@Override
	public Filme find(Connection conn, Integer idFilme) throws Exception {
		PreparedStatement myStmt = conn.prepareStatement("SELECT * FROM en_filme WHERE id_filme = (?)");
		
		myStmt.setInt(1, idFilme);
		ResultSet myRs = myStmt.executeQuery();

        if (!myRs.next()) {
            return null;
        }
       	Filme filme = new Filme();
       	
       	filme.setIdFilme(myRs.getInt("id_filme"));
		String dataLancamentoStr = myRs.getString("data_lancamento");
		LocalDate dataLancamentoLD = LocalDate.now();
		dataLancamentoLD = LocalDate.parse(dataLancamentoStr);
		Instant instant = Instant.from(dataLancamentoLD.atStartOfDay(ZoneId.of("GMT")));
		Date dataLancamento = Date.from(instant);
		filme.setDataLancamento(dataLancamento);
		
		filme.setNome(myRs.getString("nome"));
		filme.setDescricao(myRs.getString("descricao"));
        
        return filme;
    }
	

	@Override
	public Collection<Filme> list(Connection conn) throws Exception {
		
		PreparedStatement myStmt = conn.prepareStatement("SELECT * FROM en_filme ORDER BY id_filme");
		ResultSet myRs = myStmt.executeQuery();
		
		Collection<Filme> items = new ArrayList<>();	

        while (myRs.next()) {
        	
        	Filme filme = new Filme();
        	
        	filme.setIdFilme(myRs.getInt("id_filme"));
    		String dataLancamentoStr = myRs.getString("data_lancamento");
    		LocalDate dataLancamentoLD = LocalDate.now();
    		dataLancamentoLD = LocalDate.parse(dataLancamentoStr);
    		Instant instant = Instant.from(dataLancamentoLD.atStartOfDay(ZoneId.of("GMT")));
    		Date dataLancamento = Date.from(instant);
    		filme.setDataLancamento(dataLancamento);
    		
    		filme.setNome(myRs.getString("nome"));
    		filme.setDescricao(myRs.getString("descricao"));
        	
    		items.add(filme);
        }
        return items;
	}
	
	@Override
	public Integer getNextId(Connection conn) throws Exception {
        PreparedStatement myStmt = conn.prepareStatement("SELECT nextval('seq_en_filme')");
        ResultSet rs = myStmt.executeQuery();
        rs.next();
        return rs.getInt(1);	
	}


}