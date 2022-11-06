package new_proj.project2.services;

import new_proj.project2.beans.Category;
import new_proj.project2.beans.Coupon;
import new_proj.project2.beans.Customer;
import new_proj.project2.exceptions.CouponException;
import new_proj.project2.exceptions.NotFoundException;
import new_proj.project2.repository.CompanyRepository;
import new_proj.project2.repository.CouponRepository;
import new_proj.project2.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService extends ClientFacade {
    private int customerId;


    public CustomerService(CompanyRepository companyRepository, CustomerRepository customerRepository, CouponRepository couponRepository) {
        super(companyRepository, customerRepository, couponRepository);
    }

    public boolean login(String email, String password) throws NotFoundException {
        if (customerRepository.existByEmailAndPassword(email, password) == null){
            throw new NotFoundException(" email or password not exist ");
        }
        //save customer id
       this.customerId=customerRepository.findIdByEmailAndPassword(email, password);
        return true;

    }

    public void purchaseCoupon(Coupon coupon) throws CouponException {
        //check if coupon not sailed
        if (coupon.getAmount()<1)
            throw new CouponException("coupon out of stock");
        else
            //check if coupon not expired
        if (Calendar.getInstance().getTime().after(coupon.getEndDate()))
            throw new CouponException(" coupon expired ! ");

       for (Coupon customerCoupon:couponRepository.getCustomerCoupons(customerId)){
           //checks if that customer already buys that coupon
           if (coupon.getId()==customerCoupon.getId())
               throw new CouponException("customer already purchase that coupon... ");

       }
       couponRepository.addCouponPurchase(customerId,coupon.getId());
       coupon.setAmount(coupon.getAmount()-1);
       couponRepository.save(coupon);
    }

    public List<Coupon>customerCoupons(){
       return couponRepository.getCustomerCoupons(customerId);
    }

    public List<Coupon>customerCouponsByCategory(Category category){
        return couponRepository.findCouponsByCategory(customerId,category.ordinal());
    }
    public List<Coupon>getAllCouponsForBuy(){
        return couponRepository.findAll();
    }
    public Coupon getOneCoupon(int couponID) throws NotFoundException {
      return couponRepository.findById(couponID).orElseThrow(()->new NotFoundException("coupon id not found"));
    }
    public List<Coupon>couponsByMaxPrice(double maxPrice){
        return couponRepository.findCustomerCouponsByMaxPrice(customerId,maxPrice);
    }
    public Customer getCustomerThatLogin() throws NotFoundException {
       return customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException("customer id not found! "));
    }
}
