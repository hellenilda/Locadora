package locadora.model;

public class Carro extends Veiculo {
	
	public Carro(String placa, String modelo, int ano) {
		super(placa, modelo, ano);
	}

	public double calcularCustoLocacao(int dias) {
		return dias * 100;
	}
}