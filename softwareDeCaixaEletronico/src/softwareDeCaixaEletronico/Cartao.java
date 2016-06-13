package softwareDeCaixaEletronico;

public class Cartao {
	private ContaCorrente conta; 
	private String numeroDoCartao;
	private String senha;
	private String bandeira = "Visa";
	
	public Cartao(String numeroDoCartao, String senha, ContaCorrente conta) {
		this.numeroDoCartao = numeroDoCartao;
		this.senha = senha;
		this.conta = conta;
	}

	public ContaCorrente getConta() {
		return conta;
	}

	public String getNumeroDoCartao() {
		return numeroDoCartao;
	}

	public String getSenha() {
		return senha;
	}

	public String getBandeira() {
		return bandeira;
	}
}
