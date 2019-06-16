package application;

import java.util.Scanner;

import utilities.Algoritmo;

public class Principal {

	public static void main(String[] args) {

		/*
		 * Comentadas estão as formas de leitura manuais das PATHs dos arquivos a serem
		 * lidos. Não comentadas e defaults estão a leitura dos arquivos
		 * entradaAlunoPesquisa e entradaMatrizDissimilaridade
		 */

		Scanner reader = new Scanner(System.in);
		// System.out.println("Qual diretorio do arquivo que relaciona Aluno com
		// Pesquisa?");
		// String alunoPesquisaPath = reader.nextLine();
		String alunoPesquisaPath = "entradaAlunoPesquisa.txt";

		// System.out.println(
		// "Qual o diretorio do arquivo que fornece a matriz de dissimilaridade entre as
		// ï¿½reas de pesquisa");
		// String matrizDissimilaridadePath = reader.nextLine();
		String matrizDissimilaridadePath = "entradaMatrizDissimilaridade.txt";

		Algoritmo.rodar(alunoPesquisaPath, matrizDissimilaridadePath);

		reader.close();

	}

}