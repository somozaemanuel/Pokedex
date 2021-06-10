package Pokedex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import Pokedex.Dtos.EvolucionDto;
import Pokedex.Dtos.PokemonDto;
import Pokedex.Dtos.PokemonEvolucionesDto;
import Pokedex.Dtos.TipoDto;
import pokemonServices.PokemonAppService;

public class Pokedex {

	private static Pokedex instance;
	private Connection conexion;
	private PokemonAppService pokemonAppService;

	private Pokedex() {
		String usuario = "user=DESKTOP-MD116J7\\Emanuel;";
		String database = "database=Pokemon;";
		String url = "jdbc:sqlserver://localhost:1434;integratedSecurity=true;" + database + usuario;

		try {
			conexion = DriverManager.getConnection(url);
			pokemonAppService = new PokemonAppService(conexion);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		instance = this;
	}

	public static Pokedex getInstance() {
		if (instance == null) {
			return new Pokedex();
		} else {
			return instance;
		}
	}

	public List<PokemonDto> getAllPokemones() {
		return pokemonAppService.getAllPokemones();
	}

	public PokemonEvolucionesDto getEvolucionesPokemon(String pokemonNombre) {
		PokemonDto pokemon = this.getPokemonByName(pokemonNombre);
		if (pokemon.getNombre() != "") {
			return pokemonAppService.getEvolucionesPokemon(pokemonNombre);
		} else {
			return null;
		}
	}

	public boolean addNewPokemon(PokemonDto pokemon) {
		return pokemonAppService.addNewPokemon(pokemon);
	}

	public PokemonDto getPokemonByName(String pokemonNombre) {
		return pokemonAppService.getPokemonByName(pokemonNombre);
	}

	public boolean updatePokemon(PokemonDto pokemon) {
		return pokemonAppService.updatePokemon(pokemon);
	}

	public boolean addNewEvolucion(EvolucionDto evolucion) {
		return pokemonAppService.addNewEvolucion(evolucion);
	}

	public TipoDto getTipoByName(String nombreTipo) {
		return pokemonAppService.getTipoByName(nombreTipo);
	}

	public boolean addNewTipoPokemon(TipoDto tipo, PokemonDto pokemon) {
		return pokemonAppService.addNewTipoPokemon(tipo, pokemon);
	}

	public boolean removeTipoPokemon(TipoDto tipo, PokemonDto pokemon) {
		return pokemonAppService.removeTipoPokemon(tipo, pokemon);
	}
}
