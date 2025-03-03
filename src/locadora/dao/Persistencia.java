package locadora.dao;

import java.util.List;

public interface Persistencia<T> {
	void salvar(List<T> lista); // T é um tipo genérico. Flexível, para evitar o casting

	List<T> carregar();
}