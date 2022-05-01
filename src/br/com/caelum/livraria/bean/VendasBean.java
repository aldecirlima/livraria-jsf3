package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Venda;

@Named
@ViewScoped
public class VendasBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private LivroDao livroDao;

	public BarChartModel getVendasModel() {
		
		BarChartModel model = new BarChartModel();
		
//		Para deixar o grafico animado utilizar o setAnimate
		model.setAnimate(true);

		ChartSeries vendaSerie = new ChartSeries();
		vendaSerie.setLabel("Vendas 2016");

		List<Venda> vendas = getVendas();

		for (Venda venda : vendas) {
			vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}
		
		ChartSeries vendaSerie2015 = new ChartSeries();
		vendaSerie2015.setLabel("Vendas 2015");
		
		vendas = getVendas();
		for (Venda venda : vendas) {
			vendaSerie2015.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}

		model.addSeries(vendaSerie);
		model.addSeries(vendaSerie2015);

		return model;
	}

	public List<Venda> getVendas( ) {

		List<Livro> livros = livroDao.listaTodos();
		List<Venda> vendas = new ArrayList<Venda>();

//		Gerando vendas para o gráfico

		for (Livro livro : livros) {
//			Long millis = System.currentTimeMillis();
			Random random = new Random((int) (Math.random() * 1000));
			Integer quantidade = random.nextInt(500);
			vendas.add(new Venda(livro, quantidade));
		}

		return vendas;
	}

}
