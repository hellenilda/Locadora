package locadora.model;

public class Carro extends Veiculo {

	public Carro(String placa,String tipo, String modelo, int ano) {
		super(placa, tipo+": "+modelo, ano);
	}

	@Override
	public double calcularCustoLocacao(int dias) {
		return dias * 100;
	}

	@Override
	public String toString() {
		return "Carro [placa=" + getPlaca() + ", modelo=" + getModelo() + ", ano=" + getAno() + ", disponivel="
				+ isDisponivel() + "]";
	}

}