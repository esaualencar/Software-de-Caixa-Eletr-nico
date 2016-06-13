package softwareDeCaixaEletronico;

public interface Hardware {

	public String NumeroDaContaCartao( Cartao cartao);
	public void entregarDinheiro();
	public void lerEnvelope();
}

/*
 * Existe uma interface chamada Hardware que possui os métodos
 * pegarNumeroDaContaCartao() para ler o número da conta do cartão para o login
 * (retorna uma String com o número da conta), entregarDinheiro() que entrega o
 * dinheiro no caso do saque (retorna void) e lerEnvelope() que recebe o
 * envelope com dinheiro na operação de depósito (retorna void). Não tem nenhuma
 * implementação disponível da interface Hardware e deve ser utilizado um Mock
 * Object para ela durante os testes. Todos os metodos da interface Hardware
 * podem lançar uma exceção dizendo que houve uma falha de funcionamento do
 * hardware.
 * 
 * 
 * Deve-se criar testes também para os casos de falha, principalmente na classe
 * Hardware que pode falhar a qualquer momento devido a um mau funcionamento.
 */