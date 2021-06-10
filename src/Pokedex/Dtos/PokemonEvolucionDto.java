package Pokedex.Dtos;

import java.util.List;

public class PokemonEvolucionDto {
	
	private String nombre;
	private List<String> tipos;
	private int nivelEvolucion;
	
	
	public PokemonEvolucionDto(String nombre, List<String> tipos, int nivelEvolucion) {
		this.nombre = nombre;
		this.tipos = tipos;
		this.nivelEvolucion = nivelEvolucion;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public List<String> getTipos() {
		return tipos;
	}


	public void setTipos(List<String> tipos) {
		this.tipos = tipos;
	}


	public int getNivelEvolucion() {
		return nivelEvolucion;
	}


	public void setNivelEvolucion(int nivelEvolucion) {
		this.nivelEvolucion = nivelEvolucion;
	}

}
