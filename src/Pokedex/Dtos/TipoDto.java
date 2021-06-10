package Pokedex.Dtos;

public class TipoDto {
	
	private long id;
	private String Descripcion;
	
	public TipoDto(long id, String descripcion) {
		this.id = id;
		Descripcion = descripcion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	
	
	
	

}
