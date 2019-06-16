package utilities;
/*
Algoritmo de Prim
Autor:
    Vojtěch Jarník(1930) e Robert C. Prim(1957)
Colaborador:
    Filipe Saraiva (filip.saraiva@gmail.com)
Tipo:
    graph
Descri��oo:
    O Algoritmo de Prim � um algoritmo em grafos cl�ssico que determina a
    �rvore geradora m�nima de um grafo conectado n�o-orientado.
Complexidade:
    O(n²)
Dificuldade:
    medio
Refer�ncias:
    [1] http://en.wikipedia.org/wiki/Prim%27s_algorithm
    [2] http://pt.wikipedia.org/wiki/Algoritmo_de_Prim
    [3] http://www-b2.is.tokushima-u.ac.jp/~ikeda/suuri/dijkstra/Prim.shtml
Licenca:
    GPLv3
    
---------- Pequenas modifica��es realizadas por: Yan Max ----------
*/

import java.util.ArrayList;

public class Prim {

	/**
	 * Implementação do Algoritmo de Prim
	 */
	public static int[][] prim(int[][] matrizSistema) {

		/**
		 * ArrayList para guardar os vértices já verificados pelo Algoritmo de Prim
		 */
		ArrayList<Boolean> verticesVerificados = new ArrayList<Boolean>();

		/**
		 * ArrayList para guardar as distâncias relativas para cada vértice em cada
		 * iteração do Algoritmo de Prim
		 */
		ArrayList<Integer> distanciaRelativa = new ArrayList<Integer>();

		/**
		 * ArrayList unidimensional que guarda os nós vizinhos de cada nó do grafo da
		 * árvore final produzida pelo Algoritmo de Prim
		 */
		ArrayList<Integer> nosVizinhos = new ArrayList<Integer>();

		/**
		 * Inicialização de variáveis
		 */
		for (Integer contador = 0; contador < matrizSistema[0].length; contador++) {
			verticesVerificados.add(false);
			nosVizinhos.add(0);
			distanciaRelativa.add(Integer.MAX_VALUE);
		}

		distanciaRelativa.set(0, new Integer(0));

		/**
		 * Definição do ponto que será a raiz da árvore resultante
		 */
		Integer pontoAvaliado = 0;

		/**
		 * Estrutura para execução das iterações do Algoritmo de Prim
		 */
		for (Integer contadorPontosAvaliados = 0; contadorPontosAvaliados < matrizSistema[0].length; contadorPontosAvaliados++) {
			for (Integer contadorVizinhos = 0; contadorVizinhos < matrizSistema[0].length; contadorVizinhos++) {

				/**
				 * Verifica se o nó a ser avaliado nesta iteração já foi avaliado
				 * anteriormente; se sim, passa para a próxima iteração
				 */
				if ((verticesVerificados.get(contadorVizinhos)) || (contadorVizinhos == pontoAvaliado)) {
					continue;
				}

				/**
				 * Duas comparações aqui:
				 * 
				 * 1ª - Verifica se na matrizSistema há algum valor na coluna que seja >= 0.
				 * Caso afirmativo, significa que há uma aresta entre estes dois pontos do
				 * grafo.
				 * 
				 * 2ª - Verifica se o peso da aresta entre os dois nós é menor que a atual
				 * distanciaRelativa do nó vizinho.
				 * 
				 * Caso correto, a distanciaRelativa do nó vizinho ao que está sendo avaliado
				 * no momento será atualizada pelo valor dessa nova distancia avaliada até o
				 * pontoAvaliado.
				 */

				if ((matrizSistema[pontoAvaliado][contadorVizinhos] >= 0)
						&& ((matrizSistema[pontoAvaliado][contadorVizinhos] < distanciaRelativa
								.get(contadorVizinhos)))) {

					if (matrizSistema[pontoAvaliado][contadorVizinhos] == 0) {
						matrizSistema[pontoAvaliado][contadorVizinhos] = 1;
					}

					distanciaRelativa.set(contadorVizinhos, matrizSistema[pontoAvaliado][contadorVizinhos]);

					nosVizinhos.set(contadorVizinhos, pontoAvaliado);

				}
			}

			/**
			 * Marca o vértice de pontoAvaliado como um vértice já verificado
			 */
			verticesVerificados.set(pontoAvaliado, true);

			/**
			 * Preparação para seleção do próximo vértice a ser avaliado
			 */
			pontoAvaliado = new Integer(0);
			Integer distanciaComparada = new Integer(Integer.MAX_VALUE);

			/**
			 * Seleção do próximo vértice a ser avaliado
			 */
			for (Integer contador = 1; contador < verticesVerificados.size(); contador++) {

				/**
				 * Se o vertice a ser verificado já foi verificado anteriormente (true) passa
				 * à próxima iteração.
				 */
				if (verticesVerificados.get(contador)) {
					continue;
				}

				/**
				 * Se a distância relativa desse ponto for menor que a do ponto avaliado
				 * assume-se esse novo ponto como o ponto avaliado.
				 * 
				 * Ao final da iteração, será selecionado o ponto com menor distância
				 * relativa.
				 */
				if (distanciaRelativa.get(contador) < distanciaComparada) {
					distanciaComparada = distanciaRelativa.get(contador);
					pontoAvaliado = contador;
				}

			}

		}

		int[][] matrizResposta = new int[matrizSistema[0].length][matrizSistema[0].length];

		/*
		 * Inicializar matrizResposta com -1
		 */
		for (int i = 0; i < matrizSistema[0].length; i++) {
			for (int j = 0; j < matrizSistema[0].length; j++) {
				matrizResposta[i][j] = -1;
			}
		}
		/**
		 * Criação da matrizResposta com a árvore resultante do Algoritmo de Prim
		 */
		for (int contador = 1; contador < nosVizinhos.size(); contador++) {
			matrizResposta[contador][nosVizinhos.get(contador)] = matrizSistema[contador][nosVizinhos.get(contador)];
			matrizResposta[nosVizinhos.get(contador)][contador] = matrizResposta[contador][nosVizinhos.get(contador)];
		}

		return matrizResposta;
	}
}