package com.forggygaming.froggygamingserver.dao;

import com.forggygaming.froggygamingserver.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableAutoConfiguration
public class productDao {
    private final SessionFactory sessionFactory;

    public productDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public List<Product> getAllProduct(){
        Session session= sessionFactory.openSession();
        try {
            session.beginTransaction();
           return session.createQuery("FROM Product ").getResultList();

        } catch (Exception e) {

            session.getTransaction().rollback();

        }finally {
            session.close();
        }
        return null;
    }
    public List<Product> getProductByName(String proName){
        Session session= sessionFactory.openSession();
        try {
            session.beginTransaction();
            String sql="FROM Product WHERE proName=:proName";
            Query<Product> query= session.createQuery(sql);
            query.setParameter("proName",proName);
            return query.getResultList();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return null;
    }


}
