package utilities;

import java.util.Scanner;

import entities.Aluno;

public class Algoritmo {

	public static void rodar(String alunoPesquisaPath, String matrizDissimilaridadePath) {

		Aluno alunoPesquisa[] = LeitorArquivo.getAlunoPesquisa(alunoPesquisaPath);
		int dissimilaridadePesquisa[][] = LeitorArquivo.getMatrizDissimilaridade(matrizDissimilaridadePath);
		int qntAlunos = alunoPesquisa.length;
		int qntProfessores = 0;

		do {
			System.out.println("Insira a quantidade de professores: ");
			Scanner leitor = new Scanner(System.in);
			qntProfessores = leitor.nextInt();
		} while (qntProfessores > qntAlunos || qntProfessores <= 0);
		
		
		if (qntProfessores == 1) {
			System.out.print("Grupo 1: ");
			for (int i = 1; i <= qntAlunos; i++) {
				System.out.print("Aluno " + i + ", ");
			}
			System.exit(1);
		}

		int[][] grafoAlunos = new int[qntAlunos][qntAlunos];

		/*
		 * Neste grafo, os vértices são os alunos e as arestas são a
		 * dissimilaridade entre as areas de pesquisa dos alunos conectados
		 * 
		 * Eh necessario decrementar 1 no getAreaPesquisa pois a pesquisa retornada no
		 * get ï¿½ correspondente ao valor do get - 1 na matriz de dissimilaridade.
		 * EXEMPLO: aluno.getAreaPesquisa[0] = 1, porem na matriz, este valor 1 ï¿½
		 * representado na posicao 1-1 que ï¿½ igual a 0;
		 */

		for (int i = 0; i < grafoAlunos.length; i++) {
			for (int j = 0; j < grafoAlunos.length; j++) {
				grafoAlunos[i][j] = dissimilaridadePesquisa[alunoPesquisa[i].getAreaPesquisa() - 1][alunoPesquisa[j]
						.getAreaPesquisa() - 1];
			}
		}

		printMatrizes(alunoPesquisa, dissimilaridadePesquisa, grafoAlunos);

		// executar o algoritmo de kruskal para obter a AGN
		int[][] matrizResultado = Prim.prim(grafoAlunos);

		/**
		 * ImpressÃ£o da matrizResultado com a resposta do Algoritmo de Prim
		 */
		
		System.out.println("\n");
		System.out.println("------------ AGM ------------");
		for (int contadorHorizontal = 0; contadorHorizontal < matrizResultado[0].length; contadorHorizontal++) {
			for (int contadorVertical = 0; contadorVertical < matrizResultado[0].length; contadorVertical++) {

				System.out.print(matrizResultado[contadorHorizontal][contadorVertical] + "\t");
			}

			System.out.println();
		}

		// para k-1 professores, retirar a maior aresta existente (com maior
		// dissimilaridade)
		int[][] matrizFinal = retirarAresta(qntProfessores, matrizResultado);

		System.out.println("\n\n");
		// mostrar os grupos de pesquisa formados
		Graph.addMatrixToEdge(matrizFinal);
	}

	private static int[][] retirarAresta(int qntProfessores, int[][] matrizResultado) {
		int maiorAresta = 0;
		int x = 0;
		int y = 0;

		// quantidade do k - 1
		for (int k = 1; k < qntProfessores; k++) {
			// encontrar maior aresta
			for (int i = 0; i < matrizResultado.length; i++) {
				for (int j = 0; j < matrizResultado.length; j++) {
					if (maiorAresta <= matrizResultado[i][j]) {
						maiorAresta = matrizResultado[i][j];
						x = i;
						y = j;
					}
				}
			}
			// remover maior aresta
			matrizResultado[x][y] = -1 - k;
			matrizResultado[y][x] = -1 - k;

			// zerar maior aresta antes de nova execucao
			maiorAresta = 0;
		}

		System.out.println("\n\n");
		System.out.println("------------ Componentes ------------");
		for (int contadorHorizontal = 0; contadorHorizontal < matrizResultado[0].length; contadorHorizontal++) {
			for (int contadorVertical = 0; contadorVertical < matrizResultado[0].length; contadorVertical++) {

				System.out.print(matrizResultado[contadorHorizontal][contadorVertical] + "\t");
			}

			System.out.println();
		}
		return matrizResultado;

	}

	private static void printMatrizes(Aluno[] alunoPesquisa, int[][] dissimilaridadePesquisa, int[][] grafoAlunos) {

		System.out.println("Vetor aluno-pesquisa:");
		for (Aluno aluno : alunoPesquisa) {
			System.out.println(aluno.getCodigoAluno() + " " + aluno.getAreaPesquisa());
		}

		System.out.println("\n");

		System.out.println("Matriz de dissimilaridades entre pesquisas:");
		for (int i = 0; i < dissimilaridadePesquisa.length; i++) {
			for (int j = 0; j < dissimilaridadePesquisa.length; j++) {
				System.out.print(dissimilaridadePesquisa[i][j] + "\t");
			}
			System.out.println();
		}

		System.out.println("\n");

		System.out.println("Grafo final (V = Alunos e E = dissimilaridade entre areas de pesquisas entre os alunos):");
		for (int i = 0; i < grafoAlunos.length; i++) {
			for (int j = 0; j < grafoAlunos.length; j++) {
				System.out.print(grafoAlunos[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public static boolean contains(final int[] array, final int v) {

		boolean result = false;
		for (int i : array) {
			if (i == v && v != 0) {
				result = true;
				break;
			}
		}

		return result;
	}

}
