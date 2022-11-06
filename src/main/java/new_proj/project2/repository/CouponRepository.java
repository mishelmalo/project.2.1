package new_proj.project2.repository;

import new_proj.project2.beans.Company;
import new_proj.project2.beans.Coupon;
import new_proj.project2.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface CouponRepository extends JpaRepository<Coupon,Integer> {
    Set<Coupon> findByCompanyId(int companyID);
    Set<Coupon> findByCompany(Company company);



@Query(value = "select * from coupons where company_id=?1 && category=?2",nativeQuery = true)
List<Coupon> findByCompanyCategory( int companyId, int categoryId);

@Query(value = "select * from coupons  where company_id=?1 and price<=?2",nativeQuery = true )
    List<Coupon> findCouponsByMaxPrice(int companyID,double price);

@Query(value = "SELECT * FROM coupons  WHERE id=?1 && company_id=?2",nativeQuery = true)
    Coupon findCompanyCoupon(@Param("id") int couponId,@Param("comId") int companyId);

//delete history purchase  by customer id
@Modifying
@Transactional
@Query(value = "delete from customers_coupons where customer_id=?1",nativeQuery = true)
    void deleteCustomerHistory(int customerId);

//delete history purchase by coupon id
    @Modifying
    @Transactional
    @Query(value = "delete  from customers_coupons where coupons_id=?1",nativeQuery = true)
    void deleteCouponsHistory( int couponID);

    //get all customer coupons
     @Query(value = "select coupons.id, coupons.category , coupons.title , coupons.description , coupons.start_date , coupons.end_date," +
             " coupons.amount , coupons.price , coupons.image , coupons.company_id from customers_coupons join coupons " +
            "where customers_coupons.coupons_id = coupons.id and  customers_coupons.customer_id=?1",nativeQuery = true)
    List<Coupon> getCustomerCoupons(int customerID);


//customer buys coupon
    @Modifying
    @Transactional
    @Query(value = "insert into customers_coupons values(?1, ?2)",nativeQuery = true)
    void addCouponPurchase(int customerID,int couponID);
//find all customer coupons by category
    @Query(value = "SELECT coupons.id, coupons.category , coupons.title , coupons.description , coupons.start_date , coupons.end_date" +
            " , coupons.amount , coupons.price , coupons.image , coupons.company_id FROM customers_coupons" +
            " JOIN coupons WHERE customers_coupons.coupons_id=coupons.id and customers_coupons.customer_id=?1 and" +
            " coupons.category=?2 ",nativeQuery = true)
    List<Coupon> findCouponsByCategory( int customerID,int categoryID);

    //find customer coupons by max price
    @Query(value = "SELECT coupons.id, coupons.category , coupons.title , coupons.description  , coupons.start_date , coupons.end_date" +
            " , coupons.amount , coupons.price , coupons.image , coupons.company_id FROM customers_coupons" +
            " JOIN coupons WHERE customers_coupons.coupons_id=coupons.id and customers_coupons.customer_id=?1 and" +
            " coupons.price<?2 ",nativeQuery = true)
    List<Coupon> findCustomerCouponsByMaxPrice( int customerID,double maxPrice);



}
