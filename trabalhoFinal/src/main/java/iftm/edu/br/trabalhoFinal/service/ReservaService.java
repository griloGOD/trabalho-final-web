package iftm.edu.br.trabalhoFinal.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import iftm.edu.br.trabalhoFinal.model.Reserva;
import iftm.edu.br.trabalhoFinal.repository.ReservaRepository;

@Service
public class ReservaService {
 private static final Logger logger = LoggerFactory.getLogger(ReservaService.class);
 
 @Autowired
 private ReservaRepository reservaRepository;
 
 @Transactional
 public void salvar(Reserva reserva) {
     logger.debug("Salvando a reserva {}", reserva);
     reservaRepository.save(reserva);
     logger.debug("Reserva salva com sucesso");
 }
 
}