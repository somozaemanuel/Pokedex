package Pokedex;

import java.util.List;
import java.util.Scanner;

import Pokedex.Dtos.EvolucionDto;
import Pokedex.Dtos.PokemonDto;
import Pokedex.Dtos.PokemonEvolucionDto;
import Pokedex.Dtos.PokemonEvolucionesDto;
import Pokedex.Dtos.TipoDto;

public class Aplicacion {

	public static void main(String[] args) {

		int inputOpcion = 0;
		/*
		 * for (PokemonDto pok : pokedex.getAllPokemones()) {
		 * System.out.println(pok.getNombre() + " " + pok.getNivelEncuentro() + " "
		 * +pok.getTipos()); }
		 * 
		 * PokemonHabilidadesTiposDto pok =
		 * pokedex.getAllHabilidadesTiposPokemon(pokedex.getAllPokemones().get(0));
		 * 
		 * System.out.println(pok.getNombre() + " " + pok.getHabilidades() + " "
		 * +pok.getTipos());
		 * 
		 * 
		 * for (PokemonEvolucionDto evol :
		 * pokedex.getAllEvoluciones(pokedex.getAllPokemones().get(9))) {
		 * System.out.println(evol.getNombre() + " " + evol.getnivelEvolucion() + " "
		 * +evol.getTipos()); }
		 */
		System.out.println("Bienvenido a la Pokedex! Teclee el número de la opción que desea realizar.");
		System.out.println("");
		while (inputOpcion != 5) {
			System.out.println("1 - Obtener lista completa de todos los pokemones.");
			System.out.println("2 - Mostrar evoluciones y habilidades de un pokemón.");
			System.out.println("3 - Añadir nuevo pokemón a la pokedex.");
			System.out.println("4 - Editar información sobre un pokemón.");
			System.out.println("5 - Salir.");
			System.out.println("");
			System.out.print("Ingrese un número: ");
			Scanner operacion = new Scanner(System.in);
			inputOpcion = operacion.nextInt();

			switch (inputOpcion) {
			case 1:
				showAllPokemones();
				break;
			case 2:
				showEvolucionesHabilidadesPokemon();
				break;
			case 3:
				addPokemon();
				break;
			case 4:
				editPokemon();
				break;
			default:
				System.out.println("¡Error! Por favor ingrese una opción válida.");
				System.out.println("");
				break;
			}

			int opcion = 0;

			while (opcion != 1 && opcion != 2) {
				System.out.println("");
				System.out.println("¿Desea realizar otra operación?: ");
				System.out.println("");
				System.out.println("1 - SI");
				System.out.println("2 - NO");
				System.out.println("");
				System.out.print("Ingrese un numero: ");
				Scanner seguir = new Scanner(System.in);
				opcion = seguir.nextInt();

				switch (opcion) {
				case 1:
					break;
				case 2:
					inputOpcion = 5;
					break;
				default:
					System.out.println("¡Error! Por favor ingrese una opción válida.");
					System.out.println("");
					break;
				}
			}

		}

		System.out.println("¡Gracias por utilizar la Pokedex!");

	}

	public static void showAllPokemones() {
		System.out.println("Lista de Pokemones: ");
		System.out.println("");
		for (PokemonDto pok : Pokedex.getInstance().getAllPokemones()) {
			System.out.println("ID: " + pok.getId() + " - " + " Nombre: " + pok.getNombre() + " - "
					+ "Nivel Encuentro: " + pok.getNivelEncuentro() + " - " + "Tipo/s: " + pok.getTipos());
		}
	}

	public static void showEvolucionesHabilidadesPokemon() {
		System.out.print("Ingrese el nombre del Pokemon que desea consultar: ");
		Scanner input = new Scanner(System.in);
		String nombre = input.next();

		System.out.println();

		PokemonEvolucionesDto pokemonEvoluciones = Pokedex.getInstance().getEvolucionesPokemon(nombre);

		if (pokemonEvoluciones != null) {

			System.out.println("Pokemon: " + pokemonEvoluciones.getNombre() + " - " + "Habilidades: "
					+ pokemonEvoluciones.getHabilidades());
			System.out.println("Las evoluciones son: ");

			for (PokemonEvolucionDto evol : pokemonEvoluciones.getEvoluciones()) {
				System.out.println("Nombre: " + evol.getNombre() + " - " + "Nivel Evolución: "
						+ evol.getNivelEvolucion() + " - " + "Tipos: " + evol.getTipos());
			}
		} else {
			System.out.println("¡Error! El pokemon ingresado no existe.");
		}
	}

	public static void addPokemon() {
		System.out.print("Ingrese el nombre del nuevo pokemon: ");
		Scanner nombreInput = new Scanner(System.in);
		String nombre = nombreInput.next();
		System.out.print("Ingrese el nivel de encuentro del pokemon: ");
		Scanner nivelEncuentroInput = new Scanner(System.in);
		int nivelEncuentro = nivelEncuentroInput.nextInt();

		PokemonDto pokemon = new PokemonDto(nombre, nivelEncuentro, 0);

		boolean resultado = Pokedex.getInstance().addNewPokemon(pokemon);
		if (resultado) {
			System.out.println("¡Pokemon agregado con éxito!");
		} else {
			System.out.println("Ocurrió un error al insertar el pokemon.");
		}

	}

	public static void editPokemon() {
		System.out.print("Ingrese el nombre del pokemon a modificar: ");
		Scanner nombreInput = new Scanner(System.in);
		String nombre = nombreInput.next();
		PokemonDto pokemonToEdit = Pokedex.getInstance().getPokemonByName(nombre);
		if (pokemonToEdit.getNombre() != "") {
			System.out.println("¿Qué operación desea realizar con " + nombre + "?");
			System.out.println("1 - Modificar nombre");
			System.out.println("2 - Añadir evolución");
			System.out.println("3 - Añadir tipo");
			System.out.println("4 - Remover tipo");
			Scanner operacionInput = new Scanner(System.in);
			int operacion = operacionInput.nextInt();

			switch (operacion) {
			case 1:
				System.out.print("Ingrese el nuevo nombre: ");
				Scanner nombrePokemonInput = new Scanner(System.in);
				String nombrePokemon = nombrePokemonInput.next();
				pokemonToEdit.setNombre(nombrePokemon);
				Pokedex.getInstance().updatePokemon(pokemonToEdit);
				break;
			case 2:
				System.out.print("Ingrese el nombre del pokemon al que evoluciona: ");
				Scanner nombreEvolucionInput = new Scanner(System.in);
				String nombreEvolucion = nombreEvolucionInput.next();
				PokemonDto pokemonEvolucion = Pokedex.getInstance().getPokemonByName(nombreEvolucion);
				System.out.println("Ingrese el nivel al cual evoluciona el pokemon: ");
				Scanner nivelEvolucionInput = new Scanner(System.in);
				int nivelEvolucion = nivelEvolucionInput.nextInt();
				EvolucionDto evolucion = new EvolucionDto(0, pokemonToEdit.getId(), pokemonEvolucion.getId(),
						nivelEvolucion);
				boolean resultado = Pokedex.getInstance().addNewEvolucion(evolucion);
				if (resultado) {
					System.out.println("¡Evolución agregada con éxito!");
				} else {
					System.out.println("Ocurrió un error al insertar la evolución.");
				}
				break;
			case 3:
				System.out.print("Ingrese el tipo que desea añadir: ");
				Scanner tipoAddInput = new Scanner(System.in);
				String tipoAdd = tipoAddInput.next();
				TipoDto tipoPokemonToAdd = Pokedex.getInstance().getTipoByName(tipoAdd);
				if (tipoPokemonToAdd.getDescripcion() != "") {
					boolean res1 = Pokedex.getInstance().addNewTipoPokemon(tipoPokemonToAdd, pokemonToEdit);
					if (res1) {
						System.out.println("¡Tipo agregado con éxito!");
					} else {
						System.out.println("Ocurrió un error al insertar el tipo.");
					}
				} else {
					System.out.println("¡Error! No existe el tipo.");
				}
				break;
			case 4:
				System.out.print("Ingrese el tipo que desea remover: ");
				Scanner tipoRemoveInput = new Scanner(System.in);
				String tipoRemove = tipoRemoveInput.next();
				TipoDto tipoPokemonToRemove = Pokedex.getInstance().getTipoByName(tipoRemove);
				if (tipoPokemonToRemove.getDescripcion() != "") {
					boolean res2 = Pokedex.getInstance().removeTipoPokemon(tipoPokemonToRemove, pokemonToEdit);
					if (res2) {
						System.out.println("¡Tipo removido con éxito!");
					} else {
						System.out.println("Ocurrió un error al remover el tipo.");
					}
				} else {
					System.out.println("¡Error! No existe el tipo.");
				}
				break;
			default:
				break;
			}
		} else {
			System.out.println("¡Error! El pokemon ingresado no existe.");
		}

	}
}
