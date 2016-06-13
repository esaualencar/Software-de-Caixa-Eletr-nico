package softwareDeCaixaEletronico;

public class ContaCorrente {
	
	private String numeroDaAgencia;
	private String numeroDaConta;
	private double saldoTotal;
	
	
	public ContaCorrente(String numeroDaAgencia, String numeroDaConta) {
		this.numeroDaAgencia = numeroDaAgencia;
		this.numeroDaConta = numeroDaConta;
		this.saldoTotal = 0;
	}

	public String getNumeroDaAgencia() {
		return numeroDaAgencia;
	}

	public String getNumeroDaConta() {
		return numeroDaConta;
	}

	public double getSaldoTotal() {
		return saldoTotal;
	}

	public void deposita(double deposito) {
		if(deposito <0 )
			throw new FalhaServicoRemotoException("Valor Inválido");
		this.saldoTotal += deposito;
	}

	public double sacar(double saque) {
		if(saque>getSaldoTotal())
			throw new FalhaSaldoInsuficienteExcetion("Saldo insuficiente");
		saldoTotal = saldoTotal - saque;
		return saldoTotal;
	}

	
}
