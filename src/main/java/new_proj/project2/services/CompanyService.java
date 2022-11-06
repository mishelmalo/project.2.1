package new_proj.project2.services;

import new_proj.project2.beans.Category;
import new_proj.project2.beans.Company;
import new_proj.project2.beans.Coupon;
import new_proj.project2.exceptions.CouponException;
import new_proj.project2.exceptions.NotFoundException;
import new_proj.project2.repository.CompanyRepository;
import new_proj.project2.repository.CouponRepository;
import new_proj.project2.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class CompanyService extends ClientFacade {
    private int CompanyID;

    public CompanyService(CompanyRepository companyRepository, CustomerRepository customerRepository, CouponRepository couponRepository) {
        super(companyRepository, customerRepository, couponRepository);
    }

    public boolean login(String email, String password) throws NotFoundException {
       if (companyRepository.existCompanyByEmailAndPassword(email, password) == null){
           throw new NotFoundException(" email or password not exist ");
       }
       //save  company id
       this.CompanyID=companyRepository.findCompanyIdByEmailAndPassword(email, password);
       return true;

    }
    public void addCoupon(Coupon coupon) throws CouponException {
      for (Coupon companyCoupon:couponRepository.findByCompanyId(CompanyID)){
          if (Objects.equals(coupon.getTitle(), companyCoupon.getTitle()))
              throw new CouponException(" the company already have that coupon title!");
      }
        couponRepository.save(coupon);
    }
    public  void updateCoupon(Coupon coupon) throws NotFoundException {
        if(! couponRepository.existsById(coupon.getId())){
            throw new NotFoundException("coupon id not found can't update");
        }
        couponRepository.save(coupon);
    }

    public void deleteCoupon(int id) throws CouponException {
        if (!couponRepository.existsById(id))
            throw new CouponException("coupon id not exists");
        couponRepository.deleteCouponsHistory(id);
        couponRepository.deleteById(id);
    }
    public Set<Coupon> getCompanyCoupons() throws NotFoundException {
      return couponRepository.findByCompanyId(CompanyID) ;
    }
    public List<Coupon> getCouponByCategory(Category category){
        return couponRepository.findByCompanyCategory(CompanyID,category.ordinal());
    }
    public List<Coupon> getCouponByMaxPrice(double maxPrice){
        return couponRepository.findCouponsByMaxPrice(CompanyID,maxPrice);
    }
    public Coupon getCompanyCoupon(int couponId){
        return couponRepository.findCompanyCoupon(couponId,CompanyID);
    }
    //get company details
    public Company getCompanyThatLogin() throws NotFoundException {
        Company company= companyRepository.findById(CompanyID).orElseThrow(()->new NotFoundException(" company not found"));
        //get company coupons
        company.setCoupons(couponRepository.findByCompanyId(company.getId()));
        return company;
    }
}
