package br.ucsal.tabuleiro.peca;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import br.ucsal.tabuleiro.core.Casa;
import br.ucsal.tabuleiro.core.MovimentoInvalidoException;
import br.ucsal.tabuleiro.core.Peca;

public class PecaPadrao implements Peca {

	private static final Color COR_PADRAO = Color.RED;

	private static final Color COR_BORDA_PADRAO = Color.BLUE;

	private Color cor;

	private int tamanho;

	private Color corBorda;

	public PecaPadrao(Color cor, Color corBorda, int tamanho) {
		super();
		this.cor = cor;
		this.corBorda = corBorda;
		this.tamanho = tamanho;
	}

	public PecaPadrao(int tamanho) {
		this(COR_PADRAO, COR_BORDA_PADRAO, tamanho);
	}

	@Override
	public void desenhar(Graphics g, Casa casa) {
		Point centro = casa.obterCentro();
		g.setColor(cor);
		g.fillOval(centro.x - tamanho / 2, centro.y - tamanho / 2, tamanho, tamanho);
		g.setColor(corBorda);
		g.drawOval(centro.x - tamanho / 2, centro.y - tamanho / 2, tamanho, tamanho);
	}

	@Override
	public void colocar(Casa casaDestino) throws MovimentoInvalidoException {
		casaDestino.setPeca(this);
	}

	@Override
	public void retirar(Casa casaAtual) throws MovimentoInvalidoException {
		casaAtual.setPeca(null);
	}

	@Override
	public void mover(Casa casaAtual, Casa casaDestino) throws MovimentoInvalidoException {
		colocar(casaDestino);
		retirar(casaAtual);
	}

}
