package com.example.demo;

public class Prodotto {
	
	String nome;
	String marca;
	double prezzo;
	String url;
	int pezzi;
	public int getPezzi() {
		return pezzi;
	}
	public void setPezzi(int pezzi) {
		this.pezzi = pezzi;
	}
	public int getPezziV() {
		return pezziV;
	}
	public void setPezziV(int pezziV) {
		this.pezziV = pezziV;
	}
	int pezziV;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "gioco [nome=" + nome + ", marca=" + marca + ", prezzo=" + prezzo + ", url=" + url + "]";
	}
	
	

}
