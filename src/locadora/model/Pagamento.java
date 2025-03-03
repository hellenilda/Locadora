package locadora.model;

import java.time.LocalDate;

public class Pagamento {
	private String idPagamento;
	private String idLocacao;
	private double valorPago;
	private LocalDate dataPagamento;
	private String metodoPagamento;

	public Pagamento(String idPagamento, String idLocacao, double valorPago, LocalDate dataPagamento,
			String metodoPagamento) {
		super();
		this.idPagamento = idPagamento;
		this.idLocacao = idLocacao;
		this.valorPago = valorPago;
		this.dataPagamento = dataPagamento;
		this.metodoPagamento = metodoPagamento;
	}

	public String getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(String idPagamento) {
		this.idPagamento = idPagamento;
	}

	public String getIdLocacao() {
		return idLocacao;
	}

	public void setIdLocacao(String idLocacao) {
		this.idLocacao = idLocacao;
	}

	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(String metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}

	@Override
	public String toString() {
		return "Pagamento [idPagamento=" + idPagamento + ", idLocacao=" + idLocacao + ", valorPago=" + valorPago
				+ ", dataPagamento=" + dataPagamento + ", metodoPagamento=" + metodoPagamento + "]";
	}

}