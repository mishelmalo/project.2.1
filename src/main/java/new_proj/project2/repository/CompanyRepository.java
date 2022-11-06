package new_proj.project2.repository;

import new_proj.project2.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
    //return company exist by email and password
    @Query(value = "SELECT * FROM companies WHERE email=?1 && password=?2 ",nativeQuery = true)
        //check if company exist by email and password
    Company existCompanyByEmailAndPassword(String email,String password);
    //find company id
    @Query(value = "select p.id from companies p where p.email= :email and p.password= :password",nativeQuery = true)
     int findCompanyIdByEmailAndPassword(@Param("email")String email,@Param("password")String password);
//exist by company name and email
    @Query(value = "SELECT * FROM companies WHERE name=?1 && email=?2 ",nativeQuery = true)
    Company existCompanyByNameAndEmail(String name,String email);


}
