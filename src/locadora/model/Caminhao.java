package locadora.model;

public class Caminhao extends Veiculo {

	public Caminhao(String placa, String tipo, String modelo, int ano) {
		super(placa, tipo+": "+modelo, ano);
	}

	@Override
	public double calcularCustoLocacao(int dias) {
		return dias * 200;
	}

	@Override
	public String toString() {
		return "Caminhao [placa=" + getPlaca() + ", modelo=" + getModelo() + ", ano=" + getAno() + ", disponivel="
				+ isDisponivel() + "]";
	}

}