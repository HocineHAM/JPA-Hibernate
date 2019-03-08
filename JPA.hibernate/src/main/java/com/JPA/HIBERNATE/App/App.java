package com.JPA.HIBERNATE.App;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.JPA.HIBERNATE.Library.Author;
import com.JPA.HIBERNATE.Library.Book;

public class App {
	private static Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String argv[]) {
		System.out.println("Runing App...");
		log.debug("Create persistence manager");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myApp");
		EntityManager em = emf.createEntityManager();
		log.debug("Create entities");
		Author Erik = new Author();
		Erik.setFirstName("Erik");
		Erik.setLastName("Lionel");
		
		Author Yahia = new Author();
		Yahia.setFirstName("Yahia");
		Yahia.setLastName("Touré");
		
		Book book = new Book();
		book.setIsbn("897-45-456678-43");
		book.setTitle("JAVA JEE JPA HIBERNATE");
		book.setPrice(70.80);
		
		book.getAuthors().add(Erik);
		book.getAuthors().add(Yahia);
		log.debug("Persistence entities in a transaction");
		
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(Erik);
		em.persist(Yahia);
		em.persist(book);
		
		transaction.commit();
		log.debug("Close Entity Manager");
		em.close();
		emf.close();

	}

}
