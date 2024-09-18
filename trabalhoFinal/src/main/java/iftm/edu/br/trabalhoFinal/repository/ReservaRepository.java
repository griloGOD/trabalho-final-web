package iftm.edu.br.trabalhoFinal.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import iftm.edu.br.trabalhoFinal.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{

	public List<Reserva> findByDia(Date dia);
}
