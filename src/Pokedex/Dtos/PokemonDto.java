package Pokedex.Dtos;

import java.util.ArrayList;
import java.util.List;

public class PokemonDto {
	
	private long id;
	private String nombre;
	private int nivelEncuentro;
	private List<String> tipos;
	
	public PokemonDto(String _nombre, int _nivelEncuentro, long _id) {
		nombre = _nombre;
		nivelEncuentro = _nivelEncuentro;	
		id = _id;
		tipos = new ArrayList<String>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNivelEncuentro() {
		return nivelEncuentro;
	}

	public void setNivelEncuentro(int nivelEncuentro) {
		this.nivelEncuentro = nivelEncuentro;
	}
	
	public List<String> getTipos() {
		return tipos;
	}
	
	public void setTipos(List<String> tipos) {
		this.tipos = tipos;
	}
	
	public void addTipo(String tipo) {
		this.tipos.add(tipo);
	}
	
	public long getId() {
		return id;
	}
	
	
	

}
