package new_proj.project2.services;

import new_proj.project2.beans.Company;
import new_proj.project2.beans.Coupon;
import new_proj.project2.beans.Customer;
import new_proj.project2.exceptions.CompanyAlreadyExist;
import new_proj.project2.exceptions.CustomerAlreadyExist;
import new_proj.project2.exceptions.NotFoundException;
import new_proj.project2.repository.CompanyRepository;
import new_proj.project2.repository.CouponRepository;
import new_proj.project2.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AdminService extends ClientFacade {
    private final String EMAIL="admin@admin.com";
    private final String PASSWORD="admin";

    public AdminService(CompanyRepository companyRepository, CustomerRepository customerRepository, CouponRepository couponRepository) {
        super(companyRepository, customerRepository, couponRepository);
    }

    public boolean login(String email, String password){
        return (email.equals(EMAIL)&&password.equals(PASSWORD));
    }

    public void addCompany(Company company) throws CompanyAlreadyExist {
       if (companyRepository.existCompanyByNameAndEmail(company.getName(),company.getEmail())!=null){
           throw new CompanyAlreadyExist();
       }
        companyRepository.save(company);
    }

   public void updateCompany(Company company) throws NotFoundException {
        if (!companyRepository.existsById(company.getId())){
            throw new NotFoundException("company not found,can't update... ");
        }
        companyRepository.save(company);
    }
    public void deleteCompany(int id) throws NotFoundException {
        if (!companyRepository.existsById(id)){
            throw new NotFoundException("company id not found");
        }
        for (Coupon companyCoupon:couponRepository.findByCompanyId(id)){
         couponRepository.deleteCouponsHistory(companyCoupon.getId());
         couponRepository.deleteById(companyCoupon.getId());
        }
        companyRepository.deleteById(id);
    }

    public List<Company> getAllCompanies(){
       return companyRepository.findAll();
    }

    public Company getOneCompany(int id) throws NotFoundException {
       Company c= companyRepository.findById(id).orElseThrow(()->new NotFoundException("company id not found! "));
       //for to get company coupons
        c.setCoupons(couponRepository.findByCompany(c));
        return c;
    }
    public void addCustomer(Customer customer) throws CustomerAlreadyExist {
        if (customerRepository.existByEmail(customer.getEmail())!=null){
            throw new CustomerAlreadyExist();
        }
        customerRepository.save(customer);
    }
    public void updateCustomer(Customer customer) throws NotFoundException {
        if (!customerRepository.existsById(customer.getId())){
            throw new NotFoundException("customer not found,can't update... ");
        }
        customerRepository.save(customer);

    }
    public void deleteCustomer(int id) throws NotFoundException {
        if (!customerRepository.existsById(id)){
            throw new NotFoundException("customer id not found..");
        }
        couponRepository.deleteCustomerHistory(id);
        customerRepository.deleteById(id);
    }
    public List<Customer>getAllCustomers(){
      return   customerRepository.findAll();
    }
    public Customer getOneCustomer(int id) throws NotFoundException {
       return customerRepository.findById(id).orElseThrow(() -> new NotFoundException("customer id not found! "));
    }

}
