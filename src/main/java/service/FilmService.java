package service;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.management.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import ejb.ActorFacadeEJB;
import facade.ActorFacade;
import facade.FilmFacade;
import model.Actor;
import model.Film;

@Path("/films") 
public class FilmService {

	@EJB
	FilmFacade FilmFacadeEJB;

	@EJB
	ActorFacade ActorFacadeEJB;

	Logger logger = Logger.getLogger(FilmService.class.getName());

	// Obtener todas las peliculas
	@GET
	@Produces({ "application/xml", "application/json" })
	public List<Film> findAll() {
		return FilmFacadeEJB.findAll();
	}

	// Obtener una pelicula por su ID
	@GET
	@Path("{id}")
	@Produces({ "application/xml", "application/json" })
	public Film find(@PathParam("id") Integer id) {
		return FilmFacadeEJB.find(id);
	}

	// Obtener los actores que trabajaron en una pelicula
	@GET
	@Path("{id}/actors")
	@Produces({ "application/xml", "application/json" })
	public Set<Actor> findActors(@PathParam("id") Integer id) {
		return FilmFacadeEJB.find(id).getActors();
	}

	// Esta funcion sirve para agregarle un actor a una pelicula
	@POST
	@Path("{film_id}/actors/{actor_id}")
	public void addActor(@PathParam("film_id") Integer film_id, @PathParam("actor_id") Integer actor_id) {	
			
		// Obtener el actor y pelicula que se relacionan
		Actor nuevoActor = ActorFacadeEJB.find(actor_id);
		Film pelicula = FilmFacadeEJB.find(film_id);
		
		// Agregar el actor a la pelicula
		pelicula.getActors().add(nuevoActor);
		
		// Guardar la pelicula
		FilmFacadeEJB.edit(pelicula);
	}

	// Eliminar una pelicula
	@DELETE
	    @Path("{id}")
	    public void remove(@PathParam("id") Integer id){
	    	Film filmABorrar = FilmFacadeEJB.find(id);
	    	FilmFacadeEJB.remove(filmABorrar);
	    }


	    // Eliminar un actor de una pelicula
	 @DELETE
	 @Path("{film_id}/actors/{actor_id}")
	public void removeActor(@PathParam("film_id") Integer film_id, @PathParam("actor_id") Integer actor_id) {	
		// Obtener el actor y pelicula que se relacionan
		Actor actor = ActorFacadeEJB.find(actor_id);
		Film pelicula = FilmFacadeEJB.find(film_id);

		// Remover el actor del arreglo de actores de esa pelicula
		pelicula.getActors().remove(actor);

		// Guardar cambios
		FilmFacadeEJB.edit(pelicula);

	}

	    // Crear una pelicula
	@POST
	@Consumes({ "application/xml", "application/json" })
	public void create(Film entity) {
		FilmFacadeEJB.create(entity);
	}

	// Editar una pelicula
	@PUT
	@Path("{id}")
	@Consumes({ "application/xml", "application/json" })
	public void edit(@PathParam("id") Integer id, Film entity) {
		entity.setFilmId(id.intValue());
		FilmFacadeEJB.edit(entity);
	}

}
