package com.forggygaming.froggygamingserver.dao;

import com.forggygaming.froggygamingserver.entity.Customer;
import com.forggygaming.froggygamingserver.repository.CustomerRepo;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableAutoConfiguration
@Slf4j
public class CustomerDao {
    private final SessionFactory sessionFactory;

    private final CustomerRepo customerRepo;


    @Autowired
    public CustomerDao(SessionFactory sessionFactory, CustomerRepo customerRepo) {
        this.sessionFactory = sessionFactory;
        this.customerRepo = customerRepo;
    }

    public List<Customer> getAllCustomer() {

        return customerRepo.findAll();
    }

    public Customer insertCus(Customer customer) {
        //mở luồng
        Session session = sessionFactory.openSession();
        try {
            //bắt đầu giao dịch
            session.beginTransaction();
            //lưu vào db
            session.save(customer);
            //nộp(commit)

            session.getTransaction().commit();
        } catch (Exception e) {
            //báo lỗi
            log.info("error" + e.getMessage());
        } finally {
            //đóng luồng
            session.close();
        }
        return customer;
    }

    public Customer updateCustomer(Customer customer){
        Session session= sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(customer);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.info(e.getMessage());
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return customer;
    }

    public Customer findByEmail(String email) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            String sql = " FROM Customer WHERE cusEmail =: cusEmail ";
            Query<Customer> query = session.createQuery(sql);
            query.setParameter("cusEmail", email);

            return query.getSingleResult();

        } catch (Exception e) {
            log.info(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return null;
    }
    public Customer findByPhone(Long phone){
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            String sql = " FROM Customer WHERE cusPhoneNumber =: cusPhone ";
            Query<Customer> query = session.createQuery(sql);
            query.setParameter("cusPhone",phone);

            return query.getSingleResult();

        } catch (Exception e) {
            log.info(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return null;
    }
    public Customer getById(long id){
        Session session= sessionFactory.openSession();
        try {
            session.beginTransaction();
            String sql="From Customer WHERE id =:cusId";
            Query<Customer> query= session.createQuery(sql).setParameter("cusId",id);
            return query.getSingleResult();
        }catch (Exception e){
            log.info("Error :"+e);
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return null;
    }


}