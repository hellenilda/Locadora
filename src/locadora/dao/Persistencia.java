package locadora.dao;

import java.util.List;

import locadora.model.Veiculo;

public interface Persistencia<T> {
	void salvar(); // T é um tipo genérico. Flexível, para evitar o casting
	public static final List<Veiculo> veiculos = null;
	void carregar();
}