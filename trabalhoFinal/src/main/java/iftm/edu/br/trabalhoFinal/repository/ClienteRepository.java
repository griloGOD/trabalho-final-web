package iftm.edu.br.trabalhoFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import iftm.edu.br.trabalhoFinal.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	public Cliente findByUsuario(String usuario);
	
}
