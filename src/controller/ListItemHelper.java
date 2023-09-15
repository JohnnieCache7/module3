package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ListItem;

/**
 * Jonathan Argueta-Herrera - jiarguetaherrera CIS175 Fall 2023 Aug, 30
 */
public class ListItemHelper {

	public static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("VendingMachine");

	public void insertSnack(ListItem li) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}

	public List<ListItem> showAllSnacks() {
		EntityManager em = emfactory.createEntityManager();
		List<ListItem> allSnacks = em.createQuery("SELECT i FROM ListItem i").getResultList();
		return allSnacks;
	}

	public void deleteASnack(ListItem toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListItem> typedQuery = em.createQuery(
				"Select li from ListItem li where li.snack = :selectedSnack and li.price = :selectedPrice",
				ListItem.class);

		typedQuery.setParameter("selectedSnack", toDelete.getSnack());
		typedQuery.setParameter("selectedPrice", toDelete.getPrice());

		typedQuery.setMaxResults(1);

		ListItem result = typedQuery.getSingleResult();

		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public ListItem searchSnackById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ListItem found = em.find(ListItem.class, idToEdit);
		em.close();
		return found;
	}

	public void updateSnack(ListItem toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	public List<ListItem> searchSnackBySnack(String snack) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListItem> typedQuery = em.createQuery("select li from ListItem li where li.snack = :selectedSnack",
				ListItem.class);
		typedQuery.setParameter("selectedSnack", snack);

		List<ListItem> foundSnack = typedQuery.getResultList();
		em.close();
		return foundSnack;
	}

	public List<ListItem> searchSnackByPrice(int price) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListItem> typedQuery = em.createQuery("select li from ListItem li where li.price = :selectedPrice",
				ListItem.class);
		typedQuery.setParameter("selectedPrice", price);

		List<ListItem> foundSnack = typedQuery.getResultList();
		em.close();
		return foundSnack;
	}

	public void cleanUp() {
		emfactory.close();
	}

}


