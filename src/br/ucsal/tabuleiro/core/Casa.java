package br.ucsal.tabuleiro.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Casa {

	private Integer linha;

	private Integer coluna;

	private Color cor;

	private Peca peca;

	private Integer tamanho;

	public Casa(Integer linha, Integer coluna, Color cor, Integer tamanho) {
		super();
		this.linha = linha;
		this.coluna = coluna;
		this.cor = cor;
		this.tamanho = tamanho;
		this.peca = null;
	}

	public void desenhar(Graphics g) {
		g.setColor(cor);
		g.fillRect(linha * tamanho, coluna * tamanho, tamanho, tamanho + 1);
		g.drawRect(linha * tamanho, coluna * tamanho, tamanho, tamanho + 1);
		if (peca != null) {
			peca.desenhar(g, this);
		}
	}

	public void desenharSelecao(Graphics g, Color corSelecionada) {
		g.setColor(corSelecionada);
		g.drawRect(linha * tamanho, coluna * tamanho, tamanho, tamanho + 1);
	}

	public Point obterCentro() {
		return new Point(linha * tamanho + tamanho / 2, coluna * tamanho + tamanho / 2);
	}

	public Integer getLinha() {
		return linha;
	}

	public void setLinha(Integer linha) {
		this.linha = linha;
	}

	public Integer getColuna() {
		return coluna;
	}

	public void setColuna(Integer coluna) {
		this.coluna = coluna;
	}

	public Color getCor() {
		return cor;
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}

	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}

}
