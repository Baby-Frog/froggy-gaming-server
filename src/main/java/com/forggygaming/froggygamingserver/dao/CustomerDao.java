package com.forggygaming.froggygamingserver.dao;

import com.forggygaming.froggygamingserver.entity.Customer;
import com.forggygaming.froggygamingserver.repository.CustomerJPA;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
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

    public Customer insertCus(Customer customer){
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
        return customer;
    }
    public Customer checkloginEmail(Customer customer){
        Session session=sessionFactory.openSession();
        try {
            session.beginTransaction();
            //câu lệnh sql
            String sql=" FROM Customer WHERE (cusEmail=:cusmail OR cusPhone=:cusPhone ) AND cusPassword=:cuspass";
            //truy vấn sql tuỳ biến
            Query<Customer> query= session.createQuery(sql);
            //set giá trị (prepareStatement)
            query.setParameter("cusmail",customer.getCusEmail())
                    .setParameter("cusPhone",customer.getCusPhone())
                    .setParameter("cuspass",customer.getCusPassword());
            //log kết quả
            System.out.println(query.getSingleResult());
            //trả về kết quả
            return query.getSingleResult();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            session.close();
        }
        return null;
    }





}
