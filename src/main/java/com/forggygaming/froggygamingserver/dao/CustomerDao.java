package com.forggygaming.froggygamingserver.dao;

import com.forggygaming.froggygamingserver.entity.Customer;
import com.forggygaming.froggygamingserver.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@EnableAutoConfiguration
@Slf4j
public class CustomerDao {
    private final SessionFactory sessionFactory;

    private final CustomerRepository customerRepository;


    @Autowired
    public CustomerDao(SessionFactory sessionFactory, CustomerRepository customerRepository) {
        this.sessionFactory = sessionFactory;
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomer() {

        return customerRepository.findAll();
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

    public Customer checkLogin(Customer customer) {
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            //câu lệnh sql
            String sql = "FROM Customer WHERE (cusEmail=:cusMail OR cusPhone=:cusPhone) AND( cusPassword=:cusPass)";
            //truy vấn sql tuỳ biến
            Query<Customer> query = session.createQuery(sql);
            //set giá trị (prepareStatement)
            query.setParameter("cusMail", customer.getCusEmail())
             .setParameter("cusPhone",customer.getCusPhone())
                    .setParameter("cusPass", customer.getCusPassword());
            //log kết quả
            log.info(query.toString());
            //trả về kết quả duy nhất
            return query.getSingleResult();

        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            session.close();
        }
        return null;
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
            String sql = " FROM Customer WHERE cusPhone =: cusPhone ";
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
            String sql="From Customer WHERE cusId=:cusId";
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
