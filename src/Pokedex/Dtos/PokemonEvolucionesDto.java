package Pokedex.Dtos;

import java.util.ArrayList;
import java.util.List;

public class PokemonEvolucionesDto {
	
	private String nombre;
	private List<String> habilidades;
	private List<PokemonEvolucionDto> evoluciones;
	
	public PokemonEvolucionesDto(String nombre, List<String> habilidades,
			List<PokemonEvolucionDto> evoluciones) {
		this.nombre = nombre;
		this.habilidades = habilidades;
		this.evoluciones = evoluciones;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<String> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<String> habilidades) {
		this.habilidades = habilidades;
	}

	public List<PokemonEvolucionDto> getEvoluciones() {
		return evoluciones;
	}

	public void setEvoluciones(List<PokemonEvolucionDto> evoluciones) {
		this.evoluciones = evoluciones;
	}
	
	
	
	
}
	