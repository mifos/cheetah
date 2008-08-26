package springapp.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springapp.domain.Product;

@Repository("productDao")
public class JpaProductDao implements ProductDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional(readOnly=true)
	public List<Product> getProductList() {
		Query query = entityManager.createQuery("from Product");
		return query.getResultList();
	}

	@Transactional
	public void saveProduct(Product prod) {
		if (prod.getId() == null) {
			entityManager.persist(prod);
		}
		else {
			entityManager.merge(prod);
		}
	}

}
