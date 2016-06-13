package softwareDeCaixaEletronico;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testeSoftwareDeCaixaEletronico {

	CaixaEletronico caixa;
	ContaCorrente conta;
	Cartao cartao;
	MockServicoRemoto servicoRemoto;
	MockSimuladorHardware hardware;
	
	@Before
	public void iniciarVariaveis() {
		this.caixa = new CaixaEletronico();
		this.conta = new ContaCorrente("numeroDaAgencia", "numeroDaConta");
		this.cartao = new Cartao("numeroDoCartao", "senha", conta);
		this.servicoRemoto = new MockServicoRemoto();
		servicoRemoto.persistirConta(conta);
		this.hardware = new MockSimuladorHardware();
		caixa.adicionaHardware(hardware);
		caixa.adicionaServicoRemoto(servicoRemoto);
	}
	
	//------ CAMINHO FELIZ        =)

	@Test
	public void testLoginExecutadoComSucesso() {
		assertEquals("Usuário Autenticado", caixa.logar(cartao, "senha"));
	}


	@Test
	public void testDepositarSucesso() {
		assertEquals("Depósito recebido com sucesso", caixa.depositar(conta, 100));
	}

	@Test
	public void testSaldoSucesso() {
		caixa.depositar(conta, 100); 
		caixa.logar(cartao, "senha"); 
		assertEquals("O saldo é R$100,00", caixa.saldo(cartao));
	}

	@Test
	public void testSacarComSucesso() {
		caixa.depositar(conta, 100); 
		caixa.logar(cartao, "senha"); 
		assertEquals("Retire seu dinheiro", caixa.sacar(cartao,100));
	}
	
	@Test
	public void testDepositarDinheiroSacarDuasVezes() {
		caixa.depositar(conta, 100);
		caixa.logar(cartao, "senha");
		caixa.sacar(cartao, 10);
		caixa.sacar(cartao, 90);
		assertEquals("O saldo é R$0,00", caixa.saldo(cartao));
	}
	
	@Test
	public void testSacarEmDuasContas() {
		caixa.depositar(conta, 100); 
		caixa.logar(cartao, "senha"); 
		caixa.sacar(cartao,30);
		assertEquals("O saldo é R$70,00", caixa.saldo(cartao));

		ContaCorrente conta2 = new ContaCorrente("numeroDaAgencia2", "numeroDaConta2");
		Cartao cartao2 = new Cartao("numeroDoCartao2", "senha", conta2);
		servicoRemoto.persistirConta(conta2);
		caixa.logar(cartao2, "senha");
		caixa.depositar(conta2, 140);
		caixa.sacar(cartao2, 30.8);

		assertEquals("O saldo é R$109,20", caixa.saldo(cartao2));
	}
	
	


	//------ CAMINHO TRISTE        =(

	@Test
	public void testDepositoNegativo() {
		assertEquals("Operação não Realizada.", caixa.depositar(conta, -90));
	}
	
	
	@Test
	public void testSacarSaldoInsuficiente() {
		caixa.depositar(conta, 90); 
		caixa.logar(cartao, "senha"); 
		assertEquals("Saldo insuficiente", caixa.sacar(cartao,100));
	}
	
	@Test(expected = FalhaLoginException.class)
	public void testSaldoInvalidoSenhaErrada() {
		caixa.depositar(conta, 100); 
		caixa.logar(cartao, "senhaErrada"); 
		caixa.saldo(cartao);
	}
	
	@Test(expected = FalhaHardwareException.class)
	public void testFalhaHardware() {
		hardware.darErroDeHardware(true);
		caixa.depositar(conta, 100); 
		caixa.logar(cartao, "senha"); 
		caixa.saldo(cartao);
	}
	
	
	@Test(expected = FalhaServicoRemotoException.class)
	public void testFalhaServicoRemoto() {
		servicoRemoto.darErroDeServico(true);
		caixa.depositar(conta, 100); 
		caixa.logar(cartao, "senha"); 
		caixa.saldo(cartao);
	}

	@Test
	public void testLoginExecutadoComFalhaSenhaErrada() {
		assertEquals("Não foi possível autenticar o usuário", caixa.logar(cartao, "senhaErrada"));
	}
	@Test
	public void testDepositarFalhaNumeroDaContaIncorreto() {
		this.conta = new ContaCorrente("numeroDaAgencia", "numeroDaContaIncorreto");
		assertEquals("Operação não Realizada.", caixa.depositar(conta, 100));
	}
}
