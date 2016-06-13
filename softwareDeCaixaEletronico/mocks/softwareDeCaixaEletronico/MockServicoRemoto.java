package softwareDeCaixaEletronico;

import java.util.ArrayList;
import java.util.List;

public class MockServicoRemoto implements ServicoRemoto {

	private List<ContaCorrente> listasDeContas;
	private boolean erroDeServico = false;

	public void darErroDeServico(boolean erroDeServico) {
		this.erroDeServico = erroDeServico;
	}

	public MockServicoRemoto() {

		listasDeContas = new ArrayList<ContaCorrente>();
	}

	@Override
	public ContaCorrente recuperarConta(String numeroDoConta) {
		ContaCorrente conta = null;

		for (ContaCorrente contaCadastrada : listasDeContas) {
			if (contaCadastrada.getNumeroDaConta().equals(numeroDoConta)) {
				conta = contaCadastrada;
				break;
			}
		}

		if (conta == null || erroDeServico == true)
			throw new FalhaServicoRemotoException("Erro de Serviço Remoto");

		return conta;
	}

	@Override
	public void persistirConta(ContaCorrente conta) {
		try {
		ContaCorrente contaRecuperdada = recuperarConta(conta.getNumeroDaConta());
		contaRecuperdada = conta;
		} catch (FalhaServicoRemotoException e){
			listasDeContas.add(conta);
		}
	}
	
	
}
