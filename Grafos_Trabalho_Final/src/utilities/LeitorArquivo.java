package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import entities.Aluno;

public class LeitorArquivo {

	public static Aluno[] getAlunoPesquisa(String alunoPesquisaPath) {
		int qntLinhas = getQuantidadeLinhas(alunoPesquisaPath);
		Scanner leitor = null;
		Aluno[] vetorAlunoPesquisa = new Aluno[qntLinhas];

		int i = 0;
		try {
			leitor = new Scanner(new File(alunoPesquisaPath));
		} catch (FileNotFoundException e) {
			e.getStackTrace();
		}

		/*
		 * Para cada linha do arquivo, cria um aluno x com seu codigo e disciplina e
		 * coloca no vetorALunoPesquisa
		 */
		while (leitor.hasNextLine()) {
			String[] linhaAtual = leitor.nextLine().split(" ");
			Aluno x = new Aluno(Integer.parseInt(linhaAtual[0]), Integer.parseInt(linhaAtual[1]));
			vetorAlunoPesquisa[i] = x;
			i++;
		}

		return vetorAlunoPesquisa;
	}

	public static int[][] getMatrizDissimilaridade(String matrizDissimilaridadePath) {
		int qntLinhas = getQuantidadeLinhas(matrizDissimilaridadePath);
		int matrizAdjascencia[][] = new int[qntLinhas][qntLinhas];

		int i = 0, j = 0;
		Scanner leitor = null;

		try {
			leitor = new Scanner(new File(matrizDissimilaridadePath));
		} catch (FileNotFoundException e) {
			e.getStackTrace();
		}
		while (leitor.hasNextLine()) {
			String[] linhaAtual = leitor.nextLine().split(" ");
			while (j < qntLinhas) {
				matrizAdjascencia[i][j] = Integer.parseInt(linhaAtual[j]);
				j++;
			}
			i++;
			j = 0;
		}

		return matrizAdjascencia;
	}

	public static int getQuantidadeLinhas(String path) {
		int qntDeLinhas = 0;
		Scanner leitor = null;

		try {
			leitor = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			e.getStackTrace();
		}
		while (leitor.hasNextLine()) {
			qntDeLinhas++;
			leitor.nextLine();
		}
		return qntDeLinhas;
	}

}
