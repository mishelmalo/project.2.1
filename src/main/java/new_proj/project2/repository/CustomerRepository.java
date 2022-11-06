package new_proj.project2.repository;

import new_proj.project2.beans.Company;
import new_proj.project2.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Query(value = "SELECT * FROM customers WHERE email=?1",nativeQuery = true)
    Customer existByEmail(String email);

    @Query(value = "SELECT * FROM customers WHERE email=?1 && password=?2 ",nativeQuery = true)
    Customer existByEmailAndPassword(String email, String password);

    @Query(value = "select p.id from customers p where p.email= :email and p.password= :password",nativeQuery = true)
    int findIdByEmailAndPassword(@Param("email")String email, @Param("password")String password);




}
