package br.ucsal.tabuleiro.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tabuleiro extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final int QTD_CASAS_PADRAO = 8;

	private static final Color COR1_PADRAO = Color.WHITE;

	private static final Color COR2_PADRAO = Color.LIGHT_GRAY;

	private static final Color COR_CASA_SELECIONADA_PADRAO = Color.GREEN;

	private Casa[][] casasTabuleiro;

	private int tamanhoCasa;

	private Color cor1 = COR1_PADRAO;

	private Color cor2 = COR2_PADRAO;

	private Color corCasaSelecionada = COR_CASA_SELECIONADA_PADRAO;

	private Integer qtdCasas = QTD_CASAS_PADRAO;

	private Casa casaSelecionada = null;

	/**
	 * Constrói um tabuleiro com a quantidade de casas e a largura
	 * especificadas. O tabuleiro é criado vazio, ou seja, sem nenhuma peça.
	 * 
	 * @param largura
	 *            - Lagura total do tabuleiro.
	 */
	public Tabuleiro(int largura, int qtdCasas) {
		super();
		this.qtdCasas = qtdCasas;
		this.tamanhoCasa = calcularTamanhoCasa(largura);
		criarCasasTabuleiro();
		criarJanelaTabuleiro(largura);
		addMouseListener(listener);
	}

	/**
	 * Constrói um tabuleiro com a quantidade de casas padrão e a largura
	 * especificada. O tabuleiro é criado vazio, ou seja, sem nenhuma peça.
	 * 
	 * @param largura
	 *            - Lagura total do tabuleiro.
	 */
	public Tabuleiro(int largura) {
		this(largura, QTD_CASAS_PADRAO);
	}

	private void criarCasasTabuleiro() {
		casasTabuleiro = new Casa[qtdCasas][qtdCasas];
		for (int linha = 0; linha < qtdCasas; linha++) {
			for (int coluna = 0; coluna < qtdCasas; coluna++) {
				Color cor = cor1;
				if (coluna % 2 == linha % 2) {
					cor = cor2;
				}
				casasTabuleiro[linha][coluna] = new Casa(linha, coluna, cor, tamanhoCasa);
			}
		}
	}

	/**
	 * Trata os eventos de click nas casas do tabuleiro.
	 */
	private MouseListener listener = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			selecionarPosicao(e);
		}
	};

	/**
	 * Coloca no tabuleiro, na linha e coluna especificadas, a peca informada.
	 * Caso já exista no tabuleiro uma peça na casa informada, a mesma será
	 * substituida.
	 * 
	 * @param linha
	 *            - linha na qual será colocada a peça (começando em zero).
	 * @param coluna
	 *            - coluna na qual será colocada a peça (começando em zero).
	 * @param peca
	 *            - peça a ser colocada no tabuleiro.
	 * @throws MovimentoInvalidoException
	 */
	public void colocarPeca(int linha, int coluna, Peca novaPeca) throws MovimentoInvalidoException {
		novaPeca.colocar(casasTabuleiro[linha][coluna]);
		repaint();
	}

	/**
	 * Retira do tabuleiro a peça localizada na linha e coluna especificadas.
	 * Caso não exista no tabuleiro uma peça na posiçao informada, será
	 * retornado null.
	 * 
	 * @param linha
	 *            - linha da qual será retirada a peçaa (começando em zero).
	 * @param coluna
	 *            - coluna da qual será retirada a peça (começando em zero).
	 * @throws MovimentoInvalidoException
	 */
	public void retirarPeca(int linha, int coluna) throws MovimentoInvalidoException {
		Casa casaAtual = casasTabuleiro[linha][coluna];
		Peca peca = casaAtual.getPeca();
		if (peca != null) {
			peca.retirar(casaAtual);
		}
		this.repaint();
	}

	/**
	 * Retona a casa localizada na linha e coluna especificadas
	 * 
	 * @param linha
	 *            - linha da qual será observada a peça (começando em zero).
	 * @param coluna
	 *            - coluna da qual será observada a peça (começando em zero).
	 */
	public Casa observarCasa(int linha, int coluna) {
		return casasTabuleiro[linha][coluna];
	}

	public Casa obterCasaSelecionada() {
		return casaSelecionada;
	}

	/**
	 * Define a cor das casas brancas do tabuleiro.
	 * 
	 * @param cor1
	 *            - nova cor para as casas brancas do tabuleiro.
	 */
	public void setCor1(Color cor1) {
		this.cor1 = cor1;
	}

	/**
	 * Define a cor das casas escuras do tabuleiro.
	 * 
	 * @param cor1
	 *            - nova cor para as casas escuras do tabuleiro.
	 */
	public void setCor2(Color cor2) {
		this.cor2 = cor2;
	}

	private JFrame criarJanelaTabuleiro(int largura) {
		JFrame janela = new JFrame();
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setSize(largura + 17, largura + 40);
		janela.setVisible(true);
		janela.add(this);
		return janela;
	}

	private Integer calcularTamanhoCasa(int largura) {
		return largura / QTD_CASAS_PADRAO;
	}

	@Override
	protected void paintComponent(Graphics g) {
		desenharTabuleiro(g);
	}

	private void desenharTabuleiro(Graphics g) {
		for (int i = 0; i < QTD_CASAS_PADRAO; i++) {
			for (int j = 0; j < QTD_CASAS_PADRAO; j++) {
				casasTabuleiro[j][i].desenhar(g);
			}
		}
		if (casaSelecionada != null) {
			casaSelecionada.desenharSelecao(g, corCasaSelecionada);
		}
	}

	protected void selecionarPosicao(MouseEvent e) {
		Integer linha = e.getX() / tamanhoCasa;
		Integer coluna = e.getY() / tamanhoCasa;
		if (casaSelecionada == null) {
			casaSelecionada = casasTabuleiro[linha][coluna];
		} else {
			Casa casaDestino = casasTabuleiro[linha][coluna];
			Peca peca = casaSelecionada.getPeca();
			try {
				peca.mover(casaSelecionada, casaDestino);
			} catch (MovimentoInvalidoException e1) {
				System.out.println("Movimento não válido.");
			} catch (NullPointerException e1) {
				System.out.println("Peça não encontrada.");
			}
			casaSelecionada = null;
		}
		repaint();
	}

}
