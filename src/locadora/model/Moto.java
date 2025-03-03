package locadora.model;

public class Moto extends Veiculo {

	public Moto(String placa, String modelo, int ano) {
		super(placa, modelo, ano);
	}
	
	public double calcularCustoLocacao(int dias) {
		return dias * 100;
	}
}