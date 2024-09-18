package iftm.edu.br.trabalhoFinal.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="quadra")
public class Quadra implements Serializable{
	
	private static final long serialVersionUID = 7250626834959159007L;
	
	@Id
	@SequenceGenerator(name="gerador", sequenceName="quadra_codigo_seq", allocationSize=1)
	@GeneratedValue(generator="gerador", strategy = GenerationType.SEQUENCE)
	
	private Long codigo;
	private String tipo;
	private Double valor;
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quadra other = (Quadra) obj;
		return Objects.equals(codigo, other.codigo);
	}
	@Override
	public String toString() {
		return "Quadra [codigo=" + codigo + ", tipo=" + tipo + ", valor=" + valor + "]";
	}
	
	
	
}
