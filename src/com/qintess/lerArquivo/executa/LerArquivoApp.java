package com.qintess.lerArquivo.executa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.qintess.lerArquivo.Modelos.DadosMercado;
import com.qintess.lerArquivo.Modelos.Operacoes;
import com.qintess.lerArquivo.Modelos.Produto;

public class LerArquivoApp {

	public static void main(String[] args) {
		
		//Medir o tempo
		long inicio = System.currentTimeMillis();
		
		List<Produto> listaProdutos = new ArrayList<>();
		List<DadosMercado> listaDadosMercado = new ArrayList<DadosMercado>();
		
		File fileOperacoes = new File("C://Operacoes.csv");
		File fileDadosMercado = new File("C://DadosMercado.csv");
		
		String[] linhaOperacao = new String[14];
		String[] linhaDadosMercado = new String[3];
		String[] datas = new String[3];
		
		try(BufferedReader brOperacoes = new BufferedReader(new FileReader(fileOperacoes))) {
			
			brOperacoes.readLine();
			
			String s;
			while( (s = brOperacoes.readLine()) != null) {
				
				linhaOperacao = s.split(";");
				s = null;
				
				//Pegando dias corridos das operações
				datas = linhaOperacao[1].split("/");
				LocalDate dataInicio = LocalDate.of(Integer.parseInt(datas[2]), Integer.parseInt(datas[1]), Integer.parseInt(datas[0]));
				datas = linhaOperacao[2].split("/");
				LocalDate dataFinal = LocalDate.of(Integer.parseInt(datas[2]), Integer.parseInt(datas[1]), Integer.parseInt(datas[0]));
				long diasCorridosOperacoes = ChronoUnit.DAYS.between(dataInicio, dataFinal);
				
				
				//Pegando ID_PREÇO
				int idPrecoOperacoes = Integer.parseInt(linhaOperacao[13]);
				
				//Pegando Quantidade
				double quantidade = Double.parseDouble(linhaOperacao[12].replace(",", "."));
				
				//Pegando nome do produto
				String nome = linhaOperacao[9];
				
				Produto produtoCadastrar = new Produto(nome);
				Operacoes operacoesCadastrar = new Operacoes(idPrecoOperacoes, diasCorridosOperacoes, quantidade);
				
				if(listaProdutos.isEmpty()) {
					listaProdutos.add(produtoCadastrar);
					produtoCadastrar.cadastrarOperacoes(operacoesCadastrar);
				}else if(!listaProdutos.contains(produtoCadastrar)) {
					listaProdutos.add(produtoCadastrar);	
					produtoCadastrar.cadastrarOperacoes(operacoesCadastrar);
				}else if(listaProdutos.contains(produtoCadastrar)) {
					for(Produto pro : listaProdutos) {
						if(pro.equals(produtoCadastrar)) {
							pro.cadastrarOperacoes(operacoesCadastrar);	
						}
					}
				}
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		//PEGAR IDPREÇO DIAS CORRIDOS E PREÇO DA DADOS MERCADO
		
		try(BufferedReader brDadosMercado = new BufferedReader(new FileReader(fileDadosMercado))) {
			brDadosMercado.readLine(); // Pular título
			
			String s;
			while( (s = brDadosMercado.readLine()) != null) {
				
				linhaDadosMercado = s.split(";");
				s = null;
				
				//Pegando ID_PREÇO
				int idPrecoDadosMercado = Integer.parseInt(linhaDadosMercado[0]);
				
				//Pegar Dias corridos
				long diasCorridosDadosMercado = Long.parseLong(linhaDadosMercado[1]);
				
				//Pegar preço
				double preco = Double.parseDouble(linhaDadosMercado[2].replace(",", "."));
				
				DadosMercado dm = new DadosMercado(idPrecoDadosMercado, diasCorridosDadosMercado, preco);
				
				listaDadosMercado.add(dm);
				
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		for(Produto p : listaProdutos) {
			for(Operacoes op : p.getListaOperacoes()) {
				for(DadosMercado dm : listaDadosMercado) {
					if((op.getDiasCorridos() == dm.getDiasCorridos()) && (op.getIdPreco() == dm.getIdPreco())) {
						p.cadastrarResultado(op.getQuantidade(), dm.getPreco());
						break;
					}
				}
			}
		}
		
		try(FileWriter escrever = new FileWriter("resultado.txt")) {
			escrever.append("SubProduto");
			escrever.append(";");
			escrever.append("Resultado");
			escrever.append("\n");
			for(Produto p : listaProdutos) {
				escrever.append(p.getNome());
				escrever.append(";");
				escrever.append(String.valueOf(p.getResultado()));
				escrever.append("\n");
				System.out.println(p.getNome() + " | " + p.getResultado());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		long fim = System.currentTimeMillis();
		System.out.println("Tempo: " + (fim - inicio) + " ms");
	}

}
