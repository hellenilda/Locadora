package locadora.dao;

public interface Persistencia<T> {
	void salvar(); // T é um tipo genérico. Flexível, para evitar o casting

	void carregar();
}