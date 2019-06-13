package com.gysoft.springdatajpa.forall.controller;

import com.gysoft.springdatajpa.forall.dao.CustomerRepository;
import com.gysoft.springdatajpa.forall.pojo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @Description
 * @Author DJZ-WWS
 * @Date 2019/6/13 11:46
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerRepository repository;

    /**
     * 初始化数据
     */
    @RequestMapping("/index")
    public void index() {
        // save a couple of customers
        //这里使用了代理的方式实现了数据的存储


        repository.save(new Customer("Jack", "Bauer"));
        repository.save(new Customer("Chloe", "O'Brian"));
        repository.save(new Customer("Kim", "Bauer"));
        repository.save(new Customer("David", "Palmer"));
        repository.save(new Customer("Michelle", "Dessler"));
        repository.save(new Customer("Bauer", "Dessler"));

    }

    /**
     * 查询所有
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public void findAll(){
        Iterable<Customer> result = repository.findAll();
        for (Customer customer:result){
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");
    }
    /**
     * 查询ID为1的数据
     */
    @RequestMapping("/findOne")
    @ResponseBody
    public Customer findOne(){
        Customer result = repository.findOne(1L);
        if(result!=null){
            System.out.println(result.toString());
        }
        System.out.println("-------------------------------------------");
        return result;
    }
    /**
     * 查询ID为1的数据
     */
    @RequestMapping("/delete")
    public void delete(){

        System.out.println("删除前数据：");
        Iterable<Customer> customers = repository.findAll();
        for (Customer customer:customers){
            System.out.println(customer.toString());
        }

        System.out.println("删除ID=3数据：");
        Customer cu = repository.findById(3L);
        if(null==cu){
            throw new  IllegalArgumentException("您要删除的数据已经不存在");
        }
        repository.delete(3L);

        System.out.println("删除后数据：");
        customers = repository.findAll();
        for (Customer customer:customers){
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");
    }

    /**
     * 根据lastName查询
     */
    @RequestMapping("/findByLastName")
    @ResponseBody
    public  List<Customer>  findByLastName(){
        List<Customer> result = repository.findByLastName("Bauer");
        for (Customer customer:result){
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");

        List<Customer> customerByFirstNameAndAndLastName = repository.findCustomerByFirstNameAndAndLastName("Jack", "Bauer");

        customerByFirstNameAndAndLastName.forEach(e->{
            System.out.println("根据firstname和lastName查询的结果"+e);

        });

      return customerByFirstNameAndAndLastName;
    }

    /**
     * 测试使用原生的sql进行查询
     *
     *  为null
     */

    /**
     * 查询FirstName为指定用户昵称
     */
    @RequestMapping("/findByFirstName")
    public void findByFirstName(){
        Customer customer = repository.findByFirstName("Bauer");
        if(customer!=null){
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");
    }

    /**
     * @Query注解方式查询
     * 查询FirstName为指定字符串
     */
    @RequestMapping("/findByFirstName2")
    public void findByFirstName2(){
        List<Customer> customer = repository.findByFirstName2("Bauer");
        if(customer!=null){
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");
    }

    /**
     * @Query注解方式查询
     * 查询LastName为指定字符串
     */
    @RequestMapping("/findByLastName2")
    public void findByLastName2(){
        List<Customer> result = repository.findByLastName2("Dessler");
        for (Customer customer:result){
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");
    }

    /**
     * @Query注解方式查询,
     * 用@Param指定参数，匹配firstName和lastName
     */
    @RequestMapping("/findByName")
    public void findByName(){
        List<Customer> result = repository.findByName("Bauer");
        for (Customer customer:result){
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");
    }

    /**
     * @Query注解方式查询,使用关键词like
     * 用@Param指定参数，firstName的结尾为e的字符串
     */
    @RequestMapping("/findByName2")
    public void findByName2(){
        List<Customer> result = repository.findByName2("e");
        for (Customer customer:result){
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");
    }

    /**
     * @Query注解方式查询，模糊匹配关键字e
     */
    @RequestMapping("/findByName3")
    public void findByName3(){
        List<Customer> result = repository.findByName3("e");
        for (Customer customer:result){
            System.out.println(customer.toString());
        }
        System.out.println("-------------------------------------------");
    }
}

