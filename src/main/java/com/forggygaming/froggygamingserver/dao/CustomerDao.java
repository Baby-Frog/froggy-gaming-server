package com.forggygaming.froggygamingserver.dao;

import com.forggygaming.froggygamingserver.entity.Customer;
import com.forggygaming.froggygamingserver.repository.CustomerJPA;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableAutoConfiguration
public class CustomerDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private CustomerJPA customerJPA;
    public List<Customer> getAllCustomer(){

        return customerJPA.findAll();
    }

    public void insertCus(Customer customer){
       //mở luồng
       Session session= sessionFactory.openSession();
        try {
            //bắt đầu giao dịch
            session.beginTransaction();
            //lưu vào db
            session.save(customer);
            //nộp(commit)
            session.getTransaction().commit();
        }catch (Exception e){
            //báo lỗi
            System.out.println("error"+e.getMessage());
        }finally {
            //đóng luồng
            session.close();
        }
    }



}
