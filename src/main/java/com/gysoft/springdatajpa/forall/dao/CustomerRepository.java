package com.gysoft.springdatajpa.forall.dao;

/**
 * @Description
 * @Author DJZ-WWS
 * @Date 2019/6/13 9:24
 */

import com.gysoft.springdatajpa.forall.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * 根据lastname查找
     * @param lastName
     * @return
     */
    List<Customer> findByLastName(String lastName);



   /**
    * 多条件  根据lastName和firstname
    * @author   weiwensi
    * @date 14:05 2019/6/13
    *@param  firstName
    *@param  lastName
    * @return
    * @throws Exception
    * @version 2.1
    **/


    List<Customer>  findCustomerByFirstNameAndAndLastName(String firstName,String lastName);


 /**
     * 根据id查询
     * @author   weiwensi
     * @date 14:14 2019/6/13
     *@param
     * @return
     * @throws Exception
     * @version 2.1
     **/

     Customer   findById(Long  id);
    /**
     * 根据firstName查询
     *
     */

    Customer  findByFirstName(String  firstName);


    /**
     * 模糊匹配
     * @param bauer
     * @return
     */
    @Query("select c from Customer c where c.firstName=?1")
    List<Customer> findByFirstName2(String bauer);

 /**
  * 测试实体sql的查询
  * @param lastName
  * @return
  */
 @Query(value = "select * from customer  where lastName=? order by id desc",nativeQuery = true)
   // @Query("select c from Customer c where c.lastName=?1 order by c.id desc")
 List<Customer> findByLastName2(String lastName);

    /**
     * 一个参数，匹配两个字段
     * @param name2
     * @return
     * 这里Param的值和=:后面的参数匹配，但不需要和方法名对应的参数值对应
     */
    @Query("select c from Customer c where c.firstName=:name or c.lastName=:name  order by c.id desc")
    List<Customer> findByName(@Param("name") String name2);

    /**
     * 一个参数，匹配两个字段
     * @param name
     * @return
     * 这里的%只能放在占位的前面，后面不行
     */
    @Query("select c from Customer c where c.firstName like %?1")
    List<Customer> findByName2(@Param("name") String name);

    /**
     * 一个参数，匹配两个字段
     * @param name
     * @return
     * 开启nativeQuery=true，在value里可以用原生SQL语句完成查询
     */
    @Query(nativeQuery = true,value = "select * from Customer c where c.first_name like concat('%' ,?1,'%') ")
    List<Customer> findByName3(@Param("name") String name);
}
