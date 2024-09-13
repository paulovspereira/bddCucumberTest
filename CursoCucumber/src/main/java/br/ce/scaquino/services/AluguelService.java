package br.ce.scaquino.services;

import java.util.Calendar;

import br.ce.scaquino.entidades.Filme;
import br.ce.scaquino.entidades.NotaAluguel;
import br.ce.scaquino.utils.DateUtils;

public class AluguelService {

	public NotaAluguel alugar(Filme filme, String tipoAluguel) {
		
		if(filme.getEstoque() == 0)
			throw new RuntimeException("Filme sem estoque");
		
		NotaAluguel nota = new NotaAluguel();
		
		if("extendido".equals(tipoAluguel)) {
			nota.setPreco(filme.getAluguel() * 2);
			nota.setDataEntrega(DateUtils.obterDataDiferencaDias(3));

		}
		else {
			nota.setPreco(filme.getAluguel());
			nota.setDataEntrega(DateUtils.obterDataDiferencaDias(1));

		}
			
		filme.setEstoque(filme.getEstoque() -1);
		
		return nota;

		
	}

}
