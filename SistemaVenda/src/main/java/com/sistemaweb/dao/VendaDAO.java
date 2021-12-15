package com.sistemaweb.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sistemaweb.model.ItemVenda;
import com.sistemaweb.model.Produto;
import com.sistemaweb.model.Venda;

public class VendaDAO {
	
	public int adicionar(Venda v) {
		
		String sql = "insert into venda (data, total) values (?, 0)";
		var conn = Conexao.getConexao();
		int idVenda = 0;

		
		PreparedStatement ps;
		
		try {
			
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setDate(1, Date.valueOf(v.getData()));
			
			ps.executeUpdate();
			
			var rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				
				idVenda = rs.getInt(1);
				
			}
			
			ps.close();


		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return idVenda;
	
	}
	
	public boolean addItemVenda(int idVenda, ItemVenda item) {
		
		return false;
		
	}
	
	public Venda getVendaId(int id) {
		
		String sql = "select * from venda where id = ?";
		var conn = Conexao.getConexao();
		Venda v = null;
		
		PreparedStatement ps;
		
		try {
			
			ps = conn.prepareStatement(sql);

			ps.setInt(1, id);
			
			var rs = ps.executeQuery();
			
			if(rs.next()) {
				
				// obtendo os itens da venda
				List<ItemVenda> itens = new ArrayList<ItemVenda>();
				String sqlItens = "select * from itensvenda where id_venda = ?";
				PreparedStatement psItens;
				
				psItens = conn.prepareStatement(sqlItens);
				psItens.setInt(1, id);
				var rsItens = psItens.executeQuery();
				
				while (rsItens.next()) {
					
					Produto p = new Produto(rsItens.getInt(2), "", 0, 0);
					ItemVenda item = new ItemVenda(rsItens.getInt(1), p, rsItens.getDouble(4), rsItens.getDouble(5), rsItens.getDouble(6));
					itens.add(item);
				}
				
				v = new Venda(rs.getInt(1), rs.getDate(2).toLocalDate(), rs.getDouble(3), itens);
				
			}
			
			ps.close();


		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return v;
		
	}

}
