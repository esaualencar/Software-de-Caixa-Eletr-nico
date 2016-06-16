package softwareDeCaixaEletronico;

import java.text.DecimalFormat;

//

public class CaixaEletronico {

	private Hardware hardware;
	private ServicoRemoto servicoRemoto;
	private boolean isLogado = false;
	private DecimalFormat df = new DecimalFormat("###,##0.00");

	public String logar(Cartao cartao, String senha) {
		if (cartao.getSenha().equals(senha)) {
			isLogado = true;
			return "Usuário Autenticado";
		} else {
			return "Não foi possível autenticar o usuário";
		}
	}

	public String depositar(ContaCorrente conta, double valor) {

		
		try {
			ContaCorrente contaCadastrada = servicoRemoto.recuperarConta(conta.getNumeroDaConta());
			conta.deposita(valor);//Se algo ocorrer errado será lançada uma FalhaServicoRemotoException
			servicoRemoto.persistirConta(conta);
			return "Depósito recebido com sucesso";
		} catch (FalhaServicoRemotoException e) {
			return "Operação não Realizada.";
		}

	}

	public void adicionaHardware(Hardware hd) {
		this.hardware = hd;
	}

	public void adicionaServicoRemoto(ServicoRemoto sr) {
		this.servicoRemoto = sr;
	}

	public String saldo(Cartao cartao) {
		if (isLogado) { // Verifica se ta logado primeiro
			String numeroDaConta = hardware.NumeroDaContaCartao(cartao);
			ContaCorrente conta = servicoRemoto.recuperarConta(numeroDaConta);
			String resposta ="O saldo é R$"+ df.format(conta.getSaldoTotal());
			return resposta.replace('.', ',');
		} else {
			throw new FalhaLoginException("Usuário não está logado");
		}
	}

	public String sacar(Cartao cartao, double saque) {
		if (isLogado) { 
			try {
			String numeroDaConta = hardware.NumeroDaContaCartao(cartao);
			ContaCorrente conta = servicoRemoto.recuperarConta(numeroDaConta);
			conta.sacar(saque); //Se algo ocorrer errado será lançada uma FalhaSaldoInsuficienteExcetion
			servicoRemoto.persistirConta(conta);
			return "Retire seu dinheiro";
			}catch (FalhaSaldoInsuficienteExcetion e){
				return "Saldo insuficiente";
			}
		} else {
			throw new FalhaLoginException("Usuário não está logado");
		}
	}
}
