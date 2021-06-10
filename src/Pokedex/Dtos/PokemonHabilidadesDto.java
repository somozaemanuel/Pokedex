package Pokedex.Dtos;

import java.util.List;

public class PokemonHabilidadesDto {
	
	private String nombre;
	private List<String> habilidades;
	
	
	public PokemonHabilidadesDto(String _nombre, List<String> _habilidades) {
		nombre = _nombre;
		habilidades = _habilidades;
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
	
	

}
