package iftm.edu.br.trabalhoFinal.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import iftm.edu.br.trabalhoFinal.service.CadastroUsuarioService;
import iftm.edu.br.trabalhoFinal.validation.NomeUsuarioUnico;

public class NomeUsuarioUnicoValidator implements ConstraintValidator<NomeUsuarioUnico, String> {

	private static final Logger logger = LoggerFactory.getLogger(NomeUsuarioUnicoValidator.class);
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		logger.debug("value recebido para validar {}", value);
		return value != null && !cadastroUsuarioService.isNomeUsuarioJaUsado(value);
	}

}