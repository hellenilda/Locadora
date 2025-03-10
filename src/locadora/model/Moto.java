package locadora.model;

public class Moto extends Veiculo {
	public Moto(String placa, String tipo, String modelo, int ano) {
		super(placa, tipo+": "+modelo, ano);
	}

	public double calcularCustoLocacao(int dias) {
		return dias * 50;
	}

	@Override
	public String toString() {
		return "Moto [placa=" + getPlaca() + ", modelo=" + getModelo() + ", ano=" + getAno() + ", disponivel="
				+ isDisponivel() + "]";
	}

}