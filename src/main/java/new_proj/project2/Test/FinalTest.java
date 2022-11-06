package new_proj.project2.Test;

import new_proj.project2.Application;
import new_proj.project2.beans.Category;
import new_proj.project2.beans.Company;
import new_proj.project2.beans.Coupon;
import new_proj.project2.beans.Customer;
import new_proj.project2.exceptions.CompanyAlreadyExist;
import new_proj.project2.exceptions.CouponException;
import new_proj.project2.exceptions.CustomerAlreadyExist;
import new_proj.project2.exceptions.NotFoundException;
import new_proj.project2.job.CouponExpirationDailyJob;
import new_proj.project2.login.ClientType;
import new_proj.project2.login.LoginManager;
import new_proj.project2.repository.CompanyRepository;
import new_proj.project2.repository.CouponRepository;
import new_proj.project2.services.AdminService;
import new_proj.project2.services.CompanyService;
import new_proj.project2.services.CustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.Date;

@SpringBootApplication
public class FinalTest {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx= SpringApplication.run(Application.class, args);
        Thread job=new Thread(new CouponExpirationDailyJob(ctx.getBean(CouponRepository.class)));
        job.start();

        LoginManager loginManager=ctx.getBean(LoginManager.class);
        AdminService adminService=null;

            //<<<<<<<<<<<<<<<login ADMIN>>>>>>>>>>>>>>

            try {
              adminService=(AdminService) loginManager.login("admin@admin.com","admin", ClientType.ADMINISTRATOR);
                //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ADD COMPANY~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                try {
                    adminService.addCompany(new Company("ninja","ninja-kitchen@gmail.com","nini45"));
                    adminService.addCompany(new Company("nespresso","nes_coffe@gmail.com","nespresso23"));
                    adminService.addCompany(new Company("dyson","dyson@gmail.com","dy23dy"));
                   adminService.addCompany(new Company("cafeCafe","cafeCafe@gmail.com","caffe24cafe"));
                    adminService.addCompany(new Company("cafeGreg","cafeGreg@gmail.com","caffeGre"));
                } catch (CompanyAlreadyExist e) {
                    e.printStackTrace();
                }
              //  ~~~~~~~~~~~~~~~GET ONE COMPANY~~~~~~~~~~~~~~~~
                Company company1=adminService.getOneCompany(5);
                System.out.println(company1);
                //~~~~~~~~~~~~~~~~~~~~~~~~~update company~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                company1.setPassword("cafeGreg80");
                adminService.updateCompany(company1);
                //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~DELETE COMPANY~~~~~~~~~~~~~~~~~~~
                adminService.deleteCompany(5);
                //~~~~~~~~~~~~~~~~~GET ALL COMPANIES~~~~~~~~~~~~~~~~
                adminService.getAllCompanies().forEach(System.out::println);

            //~~~~~~~~~~~~~~~~~~add customer~~~~~~~~~~~~~~~~~~
                try {
                    adminService.addCustomer(new Customer("elia","nager","elia142@gmail.com","elia368"));
                    adminService.addCustomer(new Customer("mor","cohen","mor_cohen@gmail.com","mor36"));
                    adminService.addCustomer(new Customer("inna","vinberg","inna1f4@gmail.com","inna1448"));
                  adminService.addCustomer(new Customer("sharona","amar","sharonaa247@gmail.com","sharona2v4"));
               } catch (CustomerAlreadyExist e) {
                    e.printStackTrace();
                }
                //~~~~~~~~~~~~~~~~~~~get one customer~~~~~~~~~~~~~~~~~
                Customer customer1=adminService.getOneCustomer(4);
                System.out.println(customer1);

                //~~~~~~~~~~~~~~~~~~update customer~~~~~~~~~~~~~~~~~
                customer1.setPassword("sharon12");
                adminService.updateCustomer(customer1);
                //~~~~~~~~~~~~~delete customer~~~~~~~~~~~~~~~
        try {
            adminService.deleteCustomer(4);
        } catch (NotFoundException ex) {
            ex.printStackTrace();
        }

        //  ~~~~~~~~get all customers ~~~~~~~~~~~~~~~~~~
               adminService.getAllCustomers().forEach(System.out::println);


            } catch (NotFoundException e) {
                e.printStackTrace();
            }

        //<<<<<<<<<<<<<<<<<<<<<<<<<<,company>>>>>>>>>>>>>>>>>>>>>>>>>>

        CompanyService companyService=null;
        try {
        companyService=(CompanyService) loginManager.login("nes_coffe@gmail.com","nespresso23", ClientType.COMPANY);
            try {
                companyService.addCoupon(new Coupon(Category.ELECTRONICS,"NESPRESSO coffee machine gran","nespresso coffee machine gran lattissima white",
                        Date.valueOf("2022-08-27"),Date.valueOf("2022-08-30"),5,1104.70,"fhj",companyService.getCompanyThatLogin()));
                companyService.addCoupon(new Coupon(Category.ELECTRONICS,"NESPRESSO coffee machine inissia","nespresso coffee machine innisia+milk frother black",
                        Date.valueOf("2022-08-27"),Date.valueOf("2022-09-23"),3,649,"hjk",companyService.getCompanyThatLogin()));
                companyService.addCoupon(new Coupon(Category.ELECTRONICS,"NESPRESSO coffe machine creatiata ","nespresso coffe machine creatista plus j520",
                        Date.valueOf("2022-08-01"),Date.valueOf("2022-09-10"),10,1699,"jhf",companyService.getCompanyThatLogin()));
                companyService.addCoupon(new Coupon(Category.ELECTRONICS,"NESPRESSO coffe citiz","nespresso coffe machine gran citiz red",
                        Date.valueOf("2022-08-21"),Date.valueOf("2022-08-27"),2,729,"fhj",companyService.getCompanyThatLogin()));
            companyService.addCoupon(new Coupon(Category.GROCERIES,"NESPRESSO coffee capsules ","100 nespresso coffe capsules for drink with milk",
                    Date.valueOf("2022-08-03"),Date.valueOf("2022-09-05"),20,235.20,"fhj",companyService.getCompanyThatLogin()));


             } catch (CouponException e) {
               e.printStackTrace();
            }
            //~~~~~~~~~~~~~~get one coupon~~~~~~~~~~~~~~~~~
           Coupon coupon1= companyService.getCompanyCoupon(4);
            System.out.println(coupon1);

          //  ~~~~~~~~~~~~~~update coupon~~~~~~~~~~~~~~~~~~
           coupon1.setEndDate(Date.valueOf("2022-08-26"));
            companyService.updateCoupon(coupon1);
            //~~~~~~~~~~~~~~~`delete coupon~~~~~~~~~~~~~~~~~~~
            try {
                companyService.deleteCoupon(3);
            } catch (CouponException e) {
                e.printStackTrace();
            }
            //~~~~~~~~~~~~~~get all company coupons~~~~~~~~~~~~~
            companyService.getCompanyCoupons().forEach(System.out::println);
            //~~~~~~~~~~~~~~get all coupons by category~~~~~~~~
            companyService.getCouponByCategory(Category.GROCERIES).forEach(System.out::println);
            //~~~~~~~~~get all coupons by max price~~~~~~~~~~~~~~
            companyService.getCouponByMaxPrice(700).forEach(System.out::println);
            //~~~~~~~~~`get company details~~~~~~~~~~~~`
          System.out.println(companyService.getCompanyThatLogin());
            System.out.println(companyService.getCompanyThatLogin().getCoupons());

        } catch (NotFoundException e) {
            e.printStackTrace();
       }

        //<<<<<<<<<<<<<<<<<<<<<<<<<<`customer>>>>>>>>>>>>>>>>>>>>>

       CustomerService customerService =null;
        try {
            customerService=(CustomerService) loginManager.login("inna1f4@gmail.com","inna1448",ClientType.CUSTOMER);
           //~~~~~~~~~~~~~~add coupon purchase~~~~~~~~~
             try {
                customerService.purchaseCoupon(customerService.getOneCoupon(1));
                customerService.purchaseCoupon(customerService.getOneCoupon(2));
               customerService.purchaseCoupon(customerService.getOneCoupon(5));
            } catch (CouponException e) {
                e.printStackTrace();
            }

            //~~~~~~~~~~get all customer coupons~~~~~~~~~~
            customerService.customerCoupons().forEach(System.out::println);
            //~~~~~~~~~get coupons by category~~~~~~~~~~~
            customerService.customerCouponsByCategory(Category.ELECTRONICS).forEach(System.out::println);
            //~~~~~~~~~~~~~~~`coupons by max price~~~~~~~~~~~~
            customerService.couponsByMaxPrice(1000).forEach(System.out::println);
            //~~~~~~~~~~get customer details~~~~~~~~~
            System.out.println(customerService.getCustomerThatLogin());
            //~~~~~~~~````get all coupons for buy~~~~~~~~~~
            customerService.getAllCouponsForBuy().forEach(System.out::println);

        } catch (NotFoundException e) {
            e.printStackTrace();
        }


        //ctx.close();

    }
}
