package facade;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import model.Actor;
import model.Film;

@Local
public interface FilmFacade {
		

	public void create(Film entity);

	public void edit(Film entity);

	public void remove(Film entity);	

	public Film find(Object id);

	public List<Film> findAll();

	public List<Film> findRange(int[] range);

	public int count();
	


}
