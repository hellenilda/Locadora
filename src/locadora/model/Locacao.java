package locadora.model;

import java.time.LocalDate;

public class Locacao {
	private String id;
	private Veiculo veiculo;
	private String cpfCliente;
	private LocalDate dataRetirada;
	private LocalDate dataDevolucaoPrevista;
	private LocalDate dataDevolucaoReal;
	private double valorTotal;
	private boolean pago;

	public Locacao(Veiculo veiculo, String cpfCliente, LocalDate dataRetirada, LocalDate dataDevolucaoPrevista) {
		this.veiculo = veiculo;
		this.cpfCliente = cpfCliente;
		this.dataRetirada = dataRetirada;
		this.dataDevolucaoPrevista = dataDevolucaoPrevista;
		this.id = gerarId();
	}

	private String gerarId() {
		return "LOC-" + System.currentTimeMillis(); // ID único baseado no timestamp
	}

	// Getters e Setters
	public String getId() {
		return id;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public LocalDate getDataRetirada() {
		return dataRetirada;
	}

	public LocalDate getDataDevolucaoPrevista() {
		return dataDevolucaoPrevista;
	}

	public LocalDate getDataDevolucaoReal() {
		return dataDevolucaoReal;
	}

	public void setDataDevolucaoReal(LocalDate dataDevolucaoReal) {
		this.dataDevolucaoReal = dataDevolucaoReal;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

	@Override
	public String toString() {
		return "Locacao [id=" + id + ", veiculo=" + veiculo + ", cpfCliente=" + cpfCliente + ", dataRetirada="
				+ dataRetirada + ", dataDevolucaoPrevista=" + dataDevolucaoPrevista + ", dataDevolucaoReal="
				+ dataDevolucaoReal + ", valorTotal=" + valorTotal + ", pago=" + pago + "]";
	}

}