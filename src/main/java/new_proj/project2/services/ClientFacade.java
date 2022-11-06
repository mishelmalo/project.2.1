package new_proj.project2.services;

import new_proj.project2.repository.CompanyRepository;
import new_proj.project2.repository.CouponRepository;
import new_proj.project2.repository.CustomerRepository;

public abstract class ClientFacade  {

    public abstract boolean login(String email, String password)throws Exception;

   public CompanyRepository companyRepository;
    public CustomerRepository customerRepository;
    public CouponRepository couponRepository;

    public ClientFacade(CompanyRepository companyRepository, CustomerRepository customerRepository, CouponRepository couponRepository) {
        this.companyRepository = companyRepository;
        this.customerRepository = customerRepository;
        this.couponRepository = couponRepository;
    }
}
