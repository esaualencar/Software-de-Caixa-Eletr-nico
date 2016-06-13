package softwareDeCaixaEletronico;

public interface ServicoRemoto {
	public ContaCorrente recuperarConta(String numeroDoConta);
	public void persistirConta(ContaCorrente conta);

}

/*
 * As informações da classe ContaCorrente podem ser obtidas utilizando os
 * métodos de uma interface chamada ServicoRemoto. Essa interface possui o
 * método recuperarConta() que recupera uma conta baseada no seu número e o
 * método persistirConta() que grava alterações, como uma mudança no saldo
 * devido a um saque ou depósito. Não tem nenhuma implementação disponível da
 * interface ServicoRemoto e deve ser utilizado um Mock Object para ela durante
 * os testes.
 */