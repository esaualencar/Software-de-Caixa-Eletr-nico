package softwareDeCaixaEletronico;

public class MockSimuladorHardware implements Hardware {


	private boolean erroDeHardware = false;
	
	
	@Override
	public String NumeroDaContaCartao(Cartao cartao) {
		if(erroDeHardware)
			throw new FalhaHardwareException("Erro na Leitura do Cartao");
		return cartao.getConta().getNumeroDaConta();
	}

	@Override
	public void entregarDinheiro() {
		if(erroDeHardware)
			throw new FalhaHardwareException("Erro na Entrega de Dinheiro");
		// TODO Auto-generated method stub

	}

	@Override
	public void lerEnvelope() {
		if(erroDeHardware)
			throw new FalhaHardwareException("Erro na Leitura do Envelope");
		// TODO Auto-generated method stub

	}
	

	public void darErroDeHardware(boolean status) {
		this.erroDeHardware = status;
		
	}

}
