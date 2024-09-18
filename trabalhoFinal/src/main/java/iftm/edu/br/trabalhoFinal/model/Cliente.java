package iftm.edu.br.trabalhoFinal.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import iftm.edu.br.trabalhoFinal.validation.NomeUsuarioUnico;

@Entity
@Table(name="usuario")
public class Cliente implements Serializable{

	private static final long serialVersionUID = -8560115210955708907L;
	
	@Id
	@SequenceGenerator(name="gerador", sequenceName="usuario_codigo_seq", allocationSize=1)
	@GeneratedValue(generator="gerador", strategy = GenerationType.SEQUENCE)
	
	private Long codigo;
	@NotBlank(message = "O Nome é obrigatório")
	private String nome;
	@NotBlank(message = "O CPF é obrigatório")
	private String cpf;
	@NotBlank(message = "O Telefone é obrigatório")
	private String telefone;
	@NomeUsuarioUnico
	@NotBlank(message = "O login do usuário é obrigatório")
	@Column(name = "usuario")
	private String usuario;
	@NotBlank(message = "A senha do usuário é obrigatória")
	@Size(min = 5)
	private String senha;
	private Boolean ativo;
	@ManyToMany
	@JoinTable(name = "usuario_papel", joinColumns = @JoinColumn(name = "codigo_usuario"), inverseJoinColumns = @JoinColumn(name = "codigo_papel"))
	private List<Papel> papeis = new ArrayList<>();
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public void adicionarPapel(Papel papel) {
		papeis.add(papel);
	}

	public void removerPapel(Papel papel) {
		papeis.remove(papel);
	}
	
	public List<Papel> getPapeis() {
		return papeis;
	}
	public void setPapeis(List<Papel> papeis) {
		this.papeis = papeis;
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
		Cliente other = (Cliente) obj;
		return Objects.equals(codigo, other.codigo);
	}
	@Override
	public String toString() {
		return "Cliente [codigo=" + codigo + ", nome=" + nome + ", cpf=" + cpf + ", telefone=" + telefone + ", usuario="
				+ usuario + ", senha=" + senha + ", ativo=" + ativo + "]";
	}

	
	
	
}
