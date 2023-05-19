package com.example.jpa;



import com.example.jpa.domain.Order;
import com.example.jpa.domain.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


@SpringBootApplication
public class JpaApplication  implements CommandLineRunner {


	@Autowired
	private EntityManagerFactory entityManagerFactory;


	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		try {

//			Order order = new Order();
//			order.addOrderItem(new OrderItem());
////			OrderItem orderItem = new OrderItem();
////			orderItem.setOrder(order);
//
//			entityManager.persist(order);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}

			entityManagerFactory.close();

		}
}
