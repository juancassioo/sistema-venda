package com.sistemaweb;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.sistemaweb.dao.Conexao;
import com.sistemaweb.dao.ProdutoDAO;
import com.sistemaweb.dao.VendaDAO;
import com.sistemaweb.model.ItemVenda;
import com.sistemaweb.model.Produto;
import com.sistemaweb.model.Venda;

public class Principal {
	
	public static void main(String[] args) {
		
		/* Lembrar de alterar configurações do build path:
		 * menu botão direito no projeto > 
		 * Properties > Java Build Path > 
		 * Libraries e a JRE VINCULAR JDK(unbound) 
		 * Altere e salve a modificação.
		 * 
		 * Esse erro pode acontecer mais de uma vez:
		 * Escolhe a opção: 
		 * JRE System Library [CDC - 1.0/Foundation-1.0]
		 */
		
		Conexao.getConexao();
		VendaDAO vendaDAO = new VendaDAO();
		
		Venda v = vendaDAO.getVendaId(1);
		
		System.out.println("Id " + v.getId() + " Data: " + v.getData().toString());
		System.out.println("Itens da venda: ");
	
		for (ItemVenda item : v.getItens()) {
		System.out.println("Id Item: " + item.getId());
		
		}

	}
}
