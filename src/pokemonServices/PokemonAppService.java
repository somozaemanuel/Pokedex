package pokemonServices;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Pokedex.Dtos.EvolucionDto;
import Pokedex.Dtos.PokemonDto;
import Pokedex.Dtos.PokemonEvolucionDto;
import Pokedex.Dtos.PokemonEvolucionesDto;
import Pokedex.Dtos.PokemonHabilidadesDto;
import Pokedex.Dtos.TipoDto;

import java.sql.ResultSet;

public class PokemonAppService {

	Connection conexion;

	public PokemonAppService(Connection conexion) {
		this.conexion = conexion;
	}

	public List<PokemonDto> getAllPokemones() {

		List<PokemonDto> pokemones = new ArrayList<PokemonDto>();

		try {

			Statement stmt = conexion.createStatement();

			String query = "SELECT * FROM Pokemones";

			java.sql.ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				Statement stmt2 = conexion.createStatement();

				query = "SELECT * FROM TiposPokemon tp " + "INNER JOIN Tipos t ON tp.TipoId = t.Id "
						+ "WHERE PokemonId = " + rs.getLong("Id");
				java.sql.ResultSet rs2 = stmt2.executeQuery(query);

				List<String> tipos = new ArrayList<String>();

				while (rs2.next()) {
					tipos.add(rs2.getString("Descripcion"));
				}

				PokemonDto pokemon = new PokemonDto(rs.getString("Nombre"), rs.getInt("NivelEncuentro"),
						rs.getLong("Id"));
				pokemon.setTipos(tipos);
				pokemones.add(pokemon);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pokemones;
	}

	public boolean updatePokemon(PokemonDto pokemon) {
		try {

			Statement stmt = conexion.createStatement();

			String query = "UPDATE Pokemones " + "SET Nombre = '" + pokemon.getNombre() + "', NivelEncuentro = "
					+ pokemon.getNivelEncuentro() + "WHERE Id = " + pokemon.getId();

			stmt.execute(query);

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public PokemonEvolucionesDto getEvolucionesPokemon(String pokemonNombre) {

		try {
			// Chequeo que exista el pokemon
			PokemonEvolucionesDto pokemonEvoluciones = new PokemonEvolucionesDto(pokemonNombre,
					this.getPokemonHabilidades(pokemonNombre).getHabilidades(), null);

			Statement stmt = conexion.createStatement();

			String query = "SELECT pk2.*, e.NivelEvolucion "
					+ "FROM Pokemones pk LEFT JOIN Evoluciones e ON pk.Id = e.PokemonBaseId LEFT JOIN Pokemones pk2 ON e.PokemonEvolucionId = pk2.Id "
					+ "WHERE pk2.Id IS NOT NULL AND pk.Nombre = '" + pokemonNombre + "'";

			ResultSet rs = stmt.executeQuery(query);

			List<PokemonEvolucionDto> evoluciones = new ArrayList<PokemonEvolucionDto>();

			pokemonEvoluciones.setEvoluciones(evoluciones);

			while (rs.next()) {
				String query2 = "SELECT * FROM Pokemones pk LEFT JOIN TiposPokemon tp ON pk.Id = tp.PokemonId LEFT JOIN Tipos t ON tp.TipoId = t.Id "
						+ "WHERE pk.Id = " + rs.getLong("Id");
				Statement stmt2 = conexion.createStatement();
				ResultSet rs2 = stmt2.executeQuery(query2);
				List<String> tipos = new ArrayList<String>();

				while (rs2.next()) {
					tipos.add(rs2.getString("Descripcion"));
				}

				PokemonEvolucionDto evolucion = new PokemonEvolucionDto(rs.getString("Nombre"), tipos,
						rs.getInt("NivelEvolucion"));
				evoluciones.add(evolucion);

			}

			return pokemonEvoluciones;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public PokemonHabilidadesDto getPokemonHabilidades(String pokemonNombre) {

		try {

			List<String> habilidades = new ArrayList<String>();

			PokemonHabilidadesDto pokemonHabilidades = new PokemonHabilidadesDto(pokemonNombre, habilidades);

			Statement stmt = conexion.createStatement();

			String query = "SELECT * FROM Pokemones pk LEFT JOIN HabilidadesPokemon hp ON pk.Id = hp.PokemonId LEFT JOIN Habilidades h ON hp.HabilidadId = h.Id"
					+ " WHERE h.Id IS NOT NULL AND pk.Nombre = '" + pokemonNombre + "'";

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				habilidades.add(rs.getString("Descripcion"));
			}

			return pokemonHabilidades;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public boolean addNewPokemon(PokemonDto pokemon) {

		try {
			Statement stmt = conexion.createStatement();
			String query = "INSERT INTO Pokemones VALUES ('" + pokemon.getNombre() + "'" + ","
					+ pokemon.getNivelEncuentro() + ")";
			stmt.execute(query);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public PokemonDto getPokemonByName(String nombrePokemon) {
		PokemonDto pokemon = new PokemonDto("", 0, 0);
		try {
			Statement stmt = conexion.createStatement();
			String query = "SELECT * FROM Pokemones WHERE Nombre = '" + nombrePokemon + "'";
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				pokemon = new PokemonDto(rs.getString("Nombre"), rs.getInt("NivelEncuentro"), rs.getLong("Id"));
			}

			Statement stmt2 = conexion.createStatement();
			String query2 = "SELECT * FROM TiposPokemon WHERE PokemonId = " + pokemon.getId();
			ResultSet rs2 = stmt2.executeQuery(query2);
			String tipoIds = "(";
			boolean hayTipos = false;

			while (rs2.next()) {
				tipoIds += rs2.getLong("TipoId") + ",";
				hayTipos = true;
			}

			if (hayTipos) {
				tipoIds = tipoIds.substring(0, tipoIds.length() - 1);
				tipoIds += ")";
				Statement stmt3 = conexion.createStatement();
				String query3 = "SELECT * FROM Tipos WHERE Id IN " + tipoIds;
				ResultSet rs3 = stmt3.executeQuery(query3);

				while (rs3.next()) {
					pokemon.addTipo(rs3.getString("Descripcion"));
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return pokemon;
	}

	public TipoDto getTipoByName(String nombreTipo) {
		TipoDto tipo = new TipoDto(0, "");
		try {
			Statement stmt = conexion.createStatement();
			String query = "SELECT * FROM Tipos WHERE Descripcion = '" + nombreTipo + "'";
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				tipo = new TipoDto(rs.getLong("Id"), rs.getString("Descripcion"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return tipo;
	}

	public boolean addNewEvolucion(EvolucionDto evolucion) {
		try {
			Statement stmt = conexion.createStatement();
			String query = "INSERT INTO Evoluciones VALUES (" + evolucion.getPokemonBaseId() + ","
					+ evolucion.getPokemonEvolucionId() + "," + evolucion.getNivelEvolucion() + ")";
			stmt.execute(query);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean addNewTipoPokemon(TipoDto tipo, PokemonDto pokemon) {
		try {
			Statement stmt = conexion.createStatement();
			String verificoTipo = "SELECT * FROM Tipos WHERE Id = " + tipo.getId();
			ResultSet rs = stmt.executeQuery(verificoTipo);
			if (rs.next()) {
				String query = "INSERT INTO TiposPokemon VALUES (" + pokemon.getId() + "," + tipo.getId() + ")";
				stmt.execute(query);
				return true;
			} else
				return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}
	
	public List<TipoDto> getTiposForView(){
		List<TipoDto> tipos = new ArrayList<TipoDto>();
		try {
			Statement stmt = conexion.createStatement();
			String query = "SELECT * From Tipos";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				tipos.add(new TipoDto(rs.getLong("Id"),rs.getString("Descripcion")));
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return tipos;
	}

	public boolean removeTipoPokemon(TipoDto tipo, PokemonDto pokemon) {
		try {
			Statement stmt = conexion.createStatement();
			String query = "DELETE FROM TiposPokemon WHERE PokemonId = " + pokemon.getId() + " AND TipoId = "
					+ tipo.getId();
			stmt.execute(query);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
