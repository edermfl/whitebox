package br.com.ederleite.example.whitebox.util;

import br.com.ederleite.example.whitebox.domain.annotations.Mascara;
import br.com.ederleite.example.whitebox.domain.model.Pessoa;
import org.junit.Assert;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import java.lang.reflect.Field;

/**
 * Created by eml on 27/01/16.
 */
public class MascaraUtilTest {
    private static final String CPF_PADRAO = "01234567890";

    private static final String CPF_MASCARADO_PADRAO = "012.345.678-90";

    @Test
    public void testAplicarMascara() throws Exception {

	final Pessoa pessoa = new Pessoa();
	pessoa.setCpf(CPF_PADRAO);
	System.out.println("Antes : " + pessoa.getCpf());
	Assert.assertEquals(CPF_PADRAO, pessoa.getCpf());

	new MascaraUtil().aplicarMascara(pessoa);
	System.out.println("Depois: " + pessoa.getCpf());
	Assert.assertEquals(CPF_MASCARADO_PADRAO, pessoa.getCpf());

    }

    @Test
    public void testRemoverMascara() throws Exception {
	final MascaraUtil mascaraUtil = new MascaraUtil();

	// invocação método privado
	// Cenário 1
	Object retornoMetodoPrivadoRemoverMascara = Whitebox
			.invokeMethod(mascaraUtil, "removerMascara", "01...234***56.789-0");
	System.out.println(retornoMetodoPrivadoRemoverMascara);
	Assert.assertEquals(CPF_PADRAO, retornoMetodoPrivadoRemoverMascara);

	// Cenário 2
	retornoMetodoPrivadoRemoverMascara = Whitebox
			.invokeMethod(mascaraUtil, "removerMascara", "abc(def)....--->>123");
	System.out.println(retornoMetodoPrivadoRemoverMascara);
	Assert.assertEquals("abcdef123", retornoMetodoPrivadoRemoverMascara);
    }

    @Test
    public void testGetValorComMascara() throws Exception {
	final MascaraUtil mascaraUtil = new MascaraUtil();

	// Com Whitebox, também é possível acessar os fields de uma classe e obter ser dados
	final Field cpf = Whitebox.getField(Pessoa.class, "cpf");
	final String mascaraCpf = cpf.getAnnotation(Mascara.class).value();
	System.out.println(mascaraCpf);

	// invocação método privado
	// Tenha muita atenção na ordem dos parametros pois isso pode fará que o resultado dê errado
	// Cenário 1
	Object retornoMetodoPrivadoRemoverMascara = Whitebox
			.invokeMethod(mascaraUtil, "getValorComMascara", mascaraCpf, "01...23456.789-0");
	System.out.println(retornoMetodoPrivadoRemoverMascara);
	Assert.assertEquals(CPF_MASCARADO_PADRAO, retornoMetodoPrivadoRemoverMascara);

	// Cenário 2
	retornoMetodoPrivadoRemoverMascara = Whitebox
			.invokeMethod(mascaraUtil, "getValorComMascara", "AA#.##", "ab.123");
	System.out.println(retornoMetodoPrivadoRemoverMascara);
	Assert.assertEquals("ab1.23", retornoMetodoPrivadoRemoverMascara);
    }

}