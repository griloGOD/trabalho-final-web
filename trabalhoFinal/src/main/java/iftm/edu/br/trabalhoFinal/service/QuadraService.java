package iftm.edu.br.trabalhoFinal.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import iftm.edu.br.trabalhoFinal.model.Quadra;
import iftm.edu.br.trabalhoFinal.repository.QuadraRepository;

@Service
public class QuadraService {
 private static final Logger logger = LoggerFactory.getLogger(QuadraService.class);
 
 @Autowired
 private QuadraRepository quadraRepository;
 
 @Transactional
 public void salvar(Quadra quadra) {
     logger.debug("Salvando a quadra {}", quadra);
     quadraRepository.save(quadra);
     logger.debug("Quadra salva com sucesso");
 }
 
 @Transactional
 public void remover(Quadra quadra) {
     quadraRepository.delete(quadra);
     logger.debug("Quadra removida com sucesso {}", quadra);
 }

}