package com.sistemaweb.controllers;

import java.util.List;


import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.sistemaweb.dao.ProdutoDAO;
import com.sistemaweb.model.Produto;

@Named @RequestScoped

public class ProdutoBean {
	
	private Produto produto;
	private List<Produto> produtos;
	
	public ProdutoBean() {
		
		this.produto = new Produto(); // construtor vazio
		
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public List<Produto> getProdutos() {
			
			if(produtos == null) { // tá dando erro
				
				ProdutoDAO produtoDAO = new ProdutoDAO();
				produtos = produtoDAO.getProdutoTodos();
			
			}

		
		return produtos;
	}
	
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public String salvar() {
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		
		if(produto.getId() == 0) {
			
			produtoDAO.adicionar(produto);
			
		} else {
			produtoDAO.editar(produto);
		}
		
		return "produtos?faces-redirect=true";
	}
	
	public String excluir(Produto p) {
		
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.remover(p);
		
		return "produtos?faces-redirect=true";
		
	}
	
	

}
