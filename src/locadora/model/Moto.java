package locadora.model;

public class Moto extends Veiculo {
	public Moto(String placa, String modelo, int ano) {
		super(placa, modelo, ano);
	}

	@Override
	public double calcularCustoLocacao(int dias) {
		return dias * 50;
	}

	@Override
	public String toString() {
		return "Moto [placa=" + getPlaca() + ", modelo=" + getModelo() + ", ano=" + getAno() + ", disponivel="
				+ isDisponivel() + "]";
	}

}