package Pokedex.Dtos;

public class EvolucionDto {
	
	private long id;
	private long pokemonBaseId;
	private long pokemonEvolucionId;
	private int nivelEvolucion;
	
	
	public EvolucionDto(long id, long pokemonBaseId, long pokemonEvolucionId, int nivelEvolucion) {
		this.id = id;
		this.pokemonBaseId = pokemonBaseId;
		this.pokemonEvolucionId = pokemonEvolucionId;
		this.nivelEvolucion = nivelEvolucion;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getPokemonBaseId() {
		return pokemonBaseId;
	}


	public void setPokemonBaseId(long pokemonBaseId) {
		this.pokemonBaseId = pokemonBaseId;
	}


	public long getPokemonEvolucionId() {
		return pokemonEvolucionId;
	}


	public void setPokemonEvolucionId(long pokemonEvolucionId) {
		this.pokemonEvolucionId = pokemonEvolucionId;
	}


	public int getNivelEvolucion() {
		return nivelEvolucion;
	}


	public void setNivelEvolucion(int nivelEvolucion) {
		this.nivelEvolucion = nivelEvolucion;
	}
	
	
	

}
