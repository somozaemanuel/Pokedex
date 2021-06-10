package pokedexTester;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Pokedex.*;
import Pokedex.Dtos.*;
import pokemonServices.PokemonAppService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PokedexTester {

	private final String usuario = "user=DESKTOP-MD116J7\\Emanuel;";
	private final String database = "database=PokemonTest;";
	private final String url = "jdbc:sqlserver://localhost:1434;integratedSecurity=true;" + database + usuario;
	private Connection conexion;
	private PokemonAppService pokemonAppService;

	// DELETES
	private final String queryHabilidadesPokemonDelete = "DELETE FROM HabilidadesPokemon";
	private final String queryHabilidadesDelete = "DELETE FROM Habilidades";
	private final String queryTiposPokemonDelete = "DELETE FROM TiposPokemon";
	private final String queryTiposDelete = "DELETE FROM Tipos";
	private final String queryEvolucionesDelete = "DELETE FROM Evoluciones";
	private final String queryPokemonesDelete = "DELETE FROM Pokemones;";
	// INSERTS
	private final String queryPokemonesInsert = "INSERT INTO Pokemones VALUES ('Charmander', 1);INSERT INTO Pokemones VALUES ('Charmeleon', 16);INSERT INTO Pokemones VALUES ('Charizard', 32);INSERT INTO Pokemones VALUES ('Bulbasaur', 1);INSERT INTO Pokemones VALUES ('Ivysaur', 16);INSERT INTO Pokemones VALUES ('Venusaur', 32);INSERT INTO Pokemones VALUES ('Squirtle', 1);INSERT INTO Pokemones VALUES ('Wartortle', 16);INSERT INTO Pokemones VALUES ('Blastoise', 32);INSERT INTO Pokemones VALUES ('Eevee', 3);INSERT INTO Pokemones VALUES ('Vaporeon', 16);INSERT INTO Pokemones VALUES ('Jolteon', 16);INSERT INTO Pokemones VALUES ('Flareon', 16);";
	private final String queryEvolucionesInsert = "INSERT INTO Evoluciones VALUES (1,2,16);INSERT INTO Evoluciones VALUES (2,3,32);INSERT INTO Evoluciones VALUES (4,5,16);INSERT INTO Evoluciones VALUES (5,6,32);INSERT INTO Evoluciones VALUES (10,11,16);INSERT INTO Evoluciones VALUES (10,12,16);INSERT INTO Evoluciones VALUES (10,13,16);";
	private final String queryTiposInsert = "INSERT INTO Tipos VALUES ('Fuego');INSERT INTO Tipos VALUES ('Agua');INSERT INTO Tipos VALUES ('Rayo');INSERT INTO Tipos VALUES ('Hierba');INSERT INTO Tipos VALUES ('Normal');";
	private final String queryHabilidadesInsert = "INSERT INTO Habilidades VALUES ('Placaje',1,1,0);INSERT INTO Habilidades VALUES ('Pistola Agua',1,4,0);INSERT INTO Habilidades VALUES ('Llama',0,2,10);INSERT INTO Habilidades VALUES ('Araniazo',3,1,0);INSERT INTO Habilidades VALUES ('Coletazo',2,3,0);INSERT INTO Habilidades VALUES ('Roer',0,1,5);";
	private final String queryTiposPokemonInsert = "INSERT INTO TiposPokemon VALUES (1,1);INSERT INTO TiposPokemon VALUES (2,1);INSERT INTO TiposPokemon VALUES (3,1);INSERT INTO TiposPokemon VALUES (4,4);INSERT INTO TiposPokemon VALUES (5,4);INSERT INTO TiposPokemon VALUES (6,4);INSERT INTO TiposPokemon VALUES (7,2);INSERT INTO TiposPokemon VALUES (8,2);INSERT INTO TiposPokemon VALUES (9,2);INSERT INTO TiposPokemon VALUES (12,3);INSERT INTO TiposPokemon VALUES (11,2);INSERT INTO TiposPokemon VALUES (12,1);INSERT INTO TiposPokemon VALUES (11,5);";
	private final String queryHabilidadesPokemonInsert = "INSERT INTO HabilidadesPokemon VALUES (1,1);INSERT INTO HabilidadesPokemon VALUES (1,3);INSERT INTO HabilidadesPokemon VALUES (2,3);INSERT INTO HabilidadesPokemon VALUES (7,2);INSERT INTO HabilidadesPokemon VALUES (7,4);INSERT INTO HabilidadesPokemon VALUES (3,6);INSERT INTO HabilidadesPokemon VALUES (3,5);INSERT INTO HabilidadesPokemon VALUES (10,1);INSERT INTO HabilidadesPokemon VALUES (10,5);";

	Connection conectar() {
		try {
			return DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	Statement getStmt() {
		try {
			return conexion.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@BeforeEach
	void inserts() {
		conexion = conectar();
		pokemonAppService = new PokemonAppService(conexion);
		try {
			Statement stmt = conexion.createStatement();
			
			stmt.execute(queryHabilidadesPokemonDelete);
			stmt.execute(queryHabilidadesDelete);
			stmt.execute(queryTiposPokemonDelete);
			stmt.execute(queryTiposDelete);
			stmt.execute(queryEvolucionesDelete);
			stmt.execute(queryPokemonesDelete);
			String query1 = "DBCC CHECKIDENT ('HabilidadesPokemon', RESEED, 0)";
			String query2 = "DBCC CHECKIDENT ('Habilidades', RESEED, 0)";
			String query3 = "DBCC CHECKIDENT ('TiposPokemon', RESEED, 0)";
			String query4 = "DBCC CHECKIDENT ('Tipos', RESEED, 0)";
			String query5 = "DBCC CHECKIDENT ('Evoluciones', RESEED, 0)";
			String query6 = "DBCC CHECKIDENT ('Pokemones', RESEED, 0)";
			stmt.execute(query1);
			stmt.execute(query2);
			stmt.execute(query3);
			stmt.execute(query4);
			stmt.execute(query5);
			stmt.execute(query6);
			//INSERTS
			stmt.execute(queryPokemonesInsert);
			stmt.execute(queryEvolucionesInsert);
			stmt.execute(queryTiposInsert);
			stmt.execute(queryHabilidadesInsert);
			stmt.execute(queryTiposPokemonInsert);
			stmt.execute(queryHabilidadesPokemonInsert);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	void getAllPokemonesTest() {
		// Obtener todos los pokemones
		assertEquals(13, pokemonAppService.getAllPokemones().size());
	}
	
	@Test
	void getPokemonByNameTest() {
		PokemonDto pokemon = pokemonAppService.getPokemonByName("Vaporeon");
		assertEquals("Vaporeon",pokemon.getNombre());
		assertEquals(16, pokemon.getNivelEncuentro());
		assertEquals("Agua",pokemon.getTipos().remove(0));
		assertEquals("Normal",pokemon.getTipos().remove(0));
	}
	
	@Test
	void getPokemonEvolucionesHabilidadesByNameTest() {
		PokemonEvolucionesDto pokemon = pokemonAppService.getEvolucionesPokemon("Eevee");
		assertEquals("Vaporeon",pokemon.getEvoluciones().remove(0).getNombre());
		assertEquals("Jolteon",pokemon.getEvoluciones().remove(0).getNombre());
		assertEquals("Flareon",pokemon.getEvoluciones().remove(0).getNombre());
		assertEquals("Placaje",pokemon.getHabilidades().remove(0));
		assertEquals("Coletazo",pokemon.getHabilidades().remove(0));	
	}
	
	@Test
	void getPokemonEvolucionesInfoByNameTest() {
		PokemonEvolucionesDto pokemon = pokemonAppService.getEvolucionesPokemon("Charmander");
		for(PokemonEvolucionDto pk : pokemon.getEvoluciones()) {
			PokemonDto evolucionInfo = pokemonAppService.getPokemonByName(pk.getNombre());
			assertEquals("Charmeleon",evolucionInfo.getNombre());
			assertEquals(16,evolucionInfo.getNivelEncuentro());
			assertEquals("Fuego",evolucionInfo.getTipos().remove(0));
		}
		
		PokemonEvolucionesDto pokemon2 = pokemonAppService.getEvolucionesPokemon("Charmeleon");
		for(PokemonEvolucionDto pk : pokemon2.getEvoluciones()) {
			PokemonDto evolucionInfo = pokemonAppService.getPokemonByName(pk.getNombre());
			assertEquals("Charizard",evolucionInfo.getNombre());
			assertEquals(32,evolucionInfo.getNivelEncuentro());
			assertEquals("Fuego",evolucionInfo.getTipos().remove(0));
		}
		
	}
	
	@Test
	void addNewPokemonAndRetrieveTest() {
		PokemonDto psyduck = new PokemonDto("Psyduck", 5, 0);
		pokemonAppService.addNewPokemon(psyduck);
		
		PokemonDto getPsyduck = pokemonAppService.getPokemonByName("Psyduck");
		assertEquals(getPsyduck.getNombre(),"Psyduck");
		assertEquals(getPsyduck.getNivelEncuentro(),5);
		assertNotEquals(getPsyduck.getId(),0);
	}
	
	@Test
	void updatePokemonTest() {
		PokemonDto compareBlastoise = pokemonAppService.getPokemonByName("Blastoise");
		PokemonDto editBlastoise = pokemonAppService.getPokemonByName("Blastoise");
		editBlastoise.setNombre("Kubone");
		editBlastoise.setNivelEncuentro(4);
		pokemonAppService.updatePokemon(editBlastoise);
		PokemonDto compareKubone = pokemonAppService.getPokemonByName("Kubone");
		assertEquals(compareBlastoise.getId(),compareKubone.getId());
		
		TipoDto tipo = pokemonAppService.getTipoByName("Normal");
		pokemonAppService.addNewTipoPokemon(tipo,compareKubone);
		PokemonDto kuboneActualizado = pokemonAppService.getPokemonByName("Kubone");
		assertNotEquals(kuboneActualizado.getTipos().size(),compareKubone.getTipos().size());
		
	}
	
	
	@AfterEach
	void closeConnection() {
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
