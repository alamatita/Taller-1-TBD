package facade;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import model.Actor;

@Local
public interface ActorFacade {
	

	public void create(Actor entity);

	public void edit(Actor entity);

	public void remove(Actor entity);

	public Actor find(Object id);

	public List<Actor> findAll();

	public List<Actor> findRange(int[] range);

	public int count();

}
