package com.matheuspc.datum.dto;

import com.matheuspc.datum.entities.Person;
import com.matheuspc.datum.enums.SexoEnum;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PersonDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nome;

    private SexoEnum sexo;
    private String email;

    @NotEmpty
    private Date dataNascimento;
    private String naturalidade;
    private String nacionalidade;

    @NotEmpty
    @Column(unique = true)
    private String cpf;

    public PersonDTO () {
    }

    public PersonDTO(Long id, String nome, SexoEnum sexo, String email, Date dataNascimento, String naturalidade, String nacionalidade, String cpf) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.naturalidade = naturalidade;
        this.nacionalidade = nacionalidade;
        this.cpf = cpf;
    }

    public PersonDTO(Person entity) {
        id = entity.getId();
        nome = entity.getNome();
        sexo = entity.getSexo();
        email = entity.getEmail();
        dataNascimento = entity.getDataNascimento();
        naturalidade = entity.getNaturalidade();
        nacionalidade = entity.getNacionalidade();
        cpf = entity.getCpf();
    }

    public Long getId() { return id; }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public SexoEnum getSexo() {
        return sexo;
    }

    public void setSexo(SexoEnum sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return id.equals(personDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
