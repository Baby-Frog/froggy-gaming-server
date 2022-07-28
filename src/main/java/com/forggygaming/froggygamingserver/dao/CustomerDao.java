package com.forggygaming.froggygamingserver.dao;

import com.forggygaming.froggygamingserver.entity.Customer;
import com.forggygaming.froggygamingserver.repository.CustomerRepository;
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
            System.out.println("error" + e.getMessage());
        } finally {
            //đóng luồng
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
            System.out.println(query.getSingleResult());
            //trả về kết quả duy nhất
            return query.getSingleResult();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return null;
    }

    public List<Customer> findByEmailOrPhone(Customer customer) {
        Session session = sessionFactory.openSession();

        try {

            session.beginTransaction();
            String sql = " FROM Customer WHERE cusEmail =: cusEmail OR cusPhone=:cusPhone";
            Query<Customer> query = session.createQuery(sql);
            query.setParameter("cusEmail", customer.getCusEmail()).setParameter("cusPhone",customer.getCusPhone());

            return query.getResultList();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return null;
    }


}
