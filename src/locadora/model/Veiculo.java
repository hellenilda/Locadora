package locadora.model;

public abstract class Veiculo {
	private String placa;
	private String modelo;
	private int ano;
	private boolean disponivel;

	public Veiculo(String placa, String modelo, int ano) {
		this.placa = placa;
		this.modelo = modelo;
		this.ano = ano;
		this.disponivel = true;
	}

	public double calcularCustoLocacao(int dias) {
		return 0.0;
	};

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	@Override
	public String toString() {
		return "Veiculo [placa=" + placa + ", modelo=" + modelo + ", ano=" + ano + ", disponivel=" + disponivel + "]";
	}

}
