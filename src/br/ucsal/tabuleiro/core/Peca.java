package br.ucsal.tabuleiro.core;

import java.awt.Graphics;

public interface Peca {

	public void desenhar(Graphics g, Casa casa);

	public void colocar(Casa casaDestino) throws MovimentoInvalidoException;

	public void retirar(Casa casaAtual) throws MovimentoInvalidoException;

	public void mover(Casa casaAtual, Casa casaDestino) throws MovimentoInvalidoException;

}
