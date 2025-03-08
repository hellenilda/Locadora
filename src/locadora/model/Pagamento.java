package locadora.model;

import java.time.LocalDate;

public class Pagamento {
	private String id;
	private String idLocacao;
	private double valorPago;
	private String metodoPagamento;
	private LocalDate dataPagamento;

	public Pagamento(String idLocacao, double valorPago, String metodoPagamento) {
		this.idLocacao = idLocacao;
		this.valorPago = valorPago;
		this.metodoPagamento = metodoPagamento;
		this.dataPagamento = LocalDate.now();
		this.id = gerarId();
	}

	private String gerarId() {
		return "PAG-" + System.currentTimeMillis(); // ID único baseado no timestamp
	}

	// Getters e Setters
	public String getId() {
		return id;
	}

	public String getIdLocacao() {
		return idLocacao;
	}

	public double getValorPago() {
		return valorPago;
	}

	public String getMetodoPagamento() {
		return metodoPagamento;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	@Override
	public String toString() {
		return "Pagamento [id=" + id + ", idLocacao=" + idLocacao + ", valorPago=" + valorPago + ", metodoPagamento="
				+ metodoPagamento + ", dataPagamento=" + dataPagamento + "]";
	}

}