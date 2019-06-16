package utilities;

//Java program to print connected components in  
//an undirected graph  
import java.util.LinkedList;

class Graph {
	// A user define class to represent a graph.
	// A graph is an array of adjacency lists.
	// Size of array will be V (number of vertices
	// in graph)
	int V;
	LinkedList<Integer>[] adjListArray;

	// constructor
	Graph(int V) {
		this.V = V;
		// define the size of array as
		// number of vertices
		adjListArray = new LinkedList[V];

		// Create a new list for each vertex
		// such that adjacent nodes can be stored

		for (int i = 0; i < V; i++) {
			adjListArray[i] = new LinkedList<Integer>();
		}
	}

	// Adds an edge to an undirected graph
	void addEdge(int src, int dest) {
		// Add an edge from src to dest.
		adjListArray[src].add(dest);

		// Since graph is undirected, add an edge from dest
		// to src also
		adjListArray[dest].add(src);
	}

	void DFSUtil(int v, boolean[] visited) {
		// Mark the current node as visited and print it
		visited[v] = true;
		System.out.print("Aluno " + (v + 1) + ", ");
		// Recur for all the vertices
		// adjacent to this vertex
		for (int x : adjListArray[v]) {
			if (!visited[x])
				DFSUtil(x, visited);
		}

	}

	void connectedComponents() {
		// Mark all the vertices as not visited
		boolean[] visited = new boolean[V];
		int x = 1;
		for (int v = 0; v < V; ++v) {
			if (!visited[v]) {
				// print all reachable vertices
				// from v
				System.out.print("Grupo " + x + ": ");
				x++;
				DFSUtil(v, visited);
				System.out.println();
			}
		}
	}

	public static void addMatrixToEdge(int[][] matrix) {
		Graph g = new Graph(matrix.length);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if (matrix[i][j] > -1) {
					g.addEdge(i, j);
				}
			}
		}
		g.connectedComponents();
	}
}
