package br.com.ederleite.example.whitebox.domain.model;

import org.junit.Assert;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

/**
 * Created by eml on 27/01/16.
 */
public class PessoaTest {

    @Test
    public void testeSetNome() throws Exception {
	// contruindo objeto via Whitebox
	final Pessoa pessoa = Whitebox.invokeConstructor(Pessoa.class);
	System.out.println(pessoa);
	Assert.assertEquals("Eder", pessoa.getNome());

	// alterando o nome da pessoa (atributo privado), usando setInternalState
	Whitebox.setInternalState(pessoa, "nome", "Eder Leite");
	System.out.println(pessoa);
	Assert.assertEquals("Eder Leite", pessoa.getNome());
    }

}
