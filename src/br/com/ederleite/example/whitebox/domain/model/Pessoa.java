package br.com.ederleite.example.whitebox.domain.model;

import br.com.ederleite.example.whitebox.domain.annotations.Mascara;

/**
 * Created by eml on 27/01/16.
 */
public class Pessoa {

    @Mascara("###.###.###-##")
    private String cpf;

    private String nome;

    public Pessoa() {
	nome = "Eder";
    }

    public String getCpf() {
	return cpf;
    }

    public String getNome() {
	return nome;
    }

    public void setCpf(final String pCpf) {
	cpf = pCpf;
    }

    @Override public String toString() {
	return "Pessoa{" +
			"nome='" + nome + '\'' +
			", cpf='" + cpf + '\'' +
			'}';
    }
}
