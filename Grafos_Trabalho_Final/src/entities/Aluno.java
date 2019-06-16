package entities;

public class Aluno {

	int codigoAluno;
	int areaPesquisa;

	public Aluno(int codigoAluno, int areaPesquisa) {
		this.codigoAluno = codigoAluno;
		this.areaPesquisa = areaPesquisa;
	}

	public int getCodigoAluno() {
		return codigoAluno;
	}

	public void setCodigoAluno(int codigoAluno) {
		this.codigoAluno = codigoAluno;
	}

	public int getAreaPesquisa() {
		return areaPesquisa;
	}

	public void setAreaPesquisa(int areaPesquisa) {
		this.areaPesquisa = areaPesquisa;
	}

}
