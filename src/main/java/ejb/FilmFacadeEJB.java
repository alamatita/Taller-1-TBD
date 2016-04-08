package ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.FilmFacade;
import model.Actor;
import model.Film;

@Stateless
public class FilmFacadeEJB extends AbstractFacade<Film> implements FilmFacade {

	@PersistenceContext(unitName = "sakilaPU")
	private EntityManager em;

	public FilmFacadeEJB() {
		super(Film.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

}
