package new_proj.project2;

import new_proj.project2.beans.Category;
import new_proj.project2.beans.Company;
import new_proj.project2.beans.Coupon;
import new_proj.project2.beans.Customer;
import new_proj.project2.exceptions.CompanyAlreadyExist;
import new_proj.project2.exceptions.CouponException;
import new_proj.project2.exceptions.CustomerAlreadyExist;
import new_proj.project2.exceptions.NotFoundException;
import new_proj.project2.repository.CouponRepository;
import new_proj.project2.services.AdminService;
import new_proj.project2.services.CompanyService;
import new_proj.project2.services.CustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws NotFoundException, CompanyAlreadyExist, CustomerAlreadyExist, CouponException {

	//ConfigurableApplicationContext ctx= SpringApplication.run(Application.class, args);


		//AdminService adminService=ctx.getBean(AdminService.class);
		//CompanyService companyService=ctx.getBean(CompanyService.class);
		//CustomerService customerService=ctx.getBean(CustomerService.class);
		//<<<<<<<<<<<<<<Admin>>>>>>>>>>>>>>>>.
		//***********LOGIN**********
		//System.out.println(adminService.login("admin@admin.com","admin"));
		//************ADD COMPANY**************
		//try {
		//	adminService.addCompany(new Company("japanika","japan32@gmail.com","japa123"));
		//	adminService.addCompany(new Company("pizza agvania","p_agvania@gmail.com","agva11"));
		//	adminService.addCompany(new Company("rent-eldan","eldan@gmail.com","eldan123"));
		//	adminService.addCompany(new Company("rent-eldan","eldan@gmail.com","eldan123"));

		//} catch (CompanyAlreadyExist e) {
		//	e.printStackTrace();
		//}



		//************update company***********
	//	Company company =null;
	//	try {
	//		company=adminService.gatOneCompany(3);
	//		company.setPassword("eldan458");
	//		adminService.updateCompany(company);
	//		System.out.println(company);
	//	} catch (NotFoundException e) {
	//		e.printStackTrace();
	//	}
     // ***********delete company*************
	//		 try {
	//	adminService.deleteCompany(3);
	//	} catch (NotFoundException e) {
	//		e.printStackTrace();
	//		}

		//**************GET ONE COMPANY**********
	//	System.out.println(adminService.getOneCompany(2).getCoupons());
		//************get all companies*******
//		adminService.getAllCompanies().forEach(System.out::println);

		//***********add customer*************
	//	try {
	//		adminService.addCustomer(new Customer("yosi", "yosi", "yosi_yosi@gmail.com", "yosi23"));
	//		adminService.addCustomer(new Customer("milena", "kats", "milena@gmail.com", "milena_12"));
	//		adminService.addCustomer(new Customer("leon", "ishakov", "leo_is@gmail.com", "leo456"));
	//		adminService.addCustomer(new Customer("leon", "ishakov", "leo_is@gmail.com", "leo456"));
	//	} catch (CustomerAlreadyExist e) {
	//		e.printStackTrace();
	//	}

		//*************update customer***********
	//	Customer customer =null;
	//		try {
	//			customer=adminService.getOneCustomer(1);
	//			customer.setLast_name("levin");
	//			adminService.updateCustomer(customer);
	//			//System.out.println(customer);
	//		} catch (NotFoundException e) {
	//			e.printStackTrace();
	//		}
		//***********delete customeR****************
	//	try {
	//		adminService.deleteCustomer(3);
	//	} catch (NotFoundException e) {
	//		e.printStackTrace();
	//	}


		//************get all customers****************
//		adminService.getAllCustomers().forEach(System.out::println);

		//***********GET ONE CUSTOMER ***************
//		System.out.println(adminService.getOneCustomer(2));

		//<<<<<<<<<<<<<<<company service>>>>>>>>>>>>>>>>>>>

		//**************LOGIN**********
	//	System.out.println(companyService.login("japan32@gmail.com","japa123"));

		//**************add coupon************
		//try {
		//	companyService.addCoupon(new Coupon(Category.FAST_FOOD," one big plate party sushi ","many of roles with salomon and tuna",
		//			Date.valueOf("2022-08-03"),Date.valueOf("2022-08-22"),10,109,"hgd",companyService.getCompanyThatLogin()));
			//companyService.addCoupon(new Coupon(Category.FAST_FOOD,"business meal ","one role japan + nudels for veg choose",
			//		Date.valueOf("2022-06-03"),Date.valueOf("2022-08-30"),2,49.3,"hfh",companyService.getCompanyThatLogin()));
		//	companyService.addCoupon(new Coupon(Category.FAST_FOOD,"business meal ","two roles japan + nudels for veg choose",
			//		Date.valueOf("2022-05-03"),Date.valueOf("2022-07-02"),2,79.9,"hfh",companyService.getCompanyThatLogin()));
		//	companyService.addCoupon(new Coupon(Category.FAST_FOOD,"business meal for lunch ","two roles japan + nudels for veg choose",
		//			Date.valueOf("2022-05-03"),Date.valueOf("2022-07-02"),2,79.9,"hfh",companyService.getCompanyThatLogin()));
		//} catch (NotFoundException e) {
		//	e.printStackTrace();
		//}
		//**********UPDATE COUPON********
   //try {
//	Coupon coupon=companyService.getCompanyCoupon(2);
//	coupon.setAmount(3);
//	companyService.updateCoupon(coupon);
//} catch (NotFoundException e) {
//	e.printStackTrace();
//}

    //********DELETE COUPON*********
	//		companyService.deleteCoupon(6);
		//**********GET COMPANY COUPONS**************
	//	companyService.getCompanyCoupons().forEach(System.out::println);
		//********GET COMPANY DETAILS*********
	//	System.out.println(companyService.getCompanyThatLogin());
		//****** GET COMPANY COUPONS BY CATEGORY **************
	//	System.out.println(companyService.getCouponByCategory(Category.FAST_FOOD));
		//**************GET COMPANY COUPONS BY MAX PRICE ************
	//	System.out.println(companyService.getCouponByMaxPrice(109));

		//<<<<<<<<<<<<<<<customer service>>>>>>>>>>>>>>>>>>>>>>>>.

		//*****************LOGIN**************
		//System.out.println(customerService.login("milena@gmail.com", "milena_12"));

		//********* PURCHASE COUPON **********
//		Coupon coupon2=customerService.getOneCoupon(1);
//		try {
//			customerService.purchaseCoupon(coupon2);
//		} catch (CouponException e) {
//			e.printStackTrace();
//	}

//		Coupon coupon3=customerService.getOneCoupon(1);
//		try {
//			customerService.purchaseCoupon(coupon3);
//		} catch (CouponException e) {
//			e.printStackTrace();
//		}
		//***********get all customer coupons*****
	//	System.out.println(customerService.customerCoupons());
		//*************get all customer coupons by category*********
	//	System.out.println(customerService.customerCouponsByCategory(Category.FAST_FOOD));
		//**************customer coupons by max price**************
	//	System.out.println(customerService.couponsByMaxPrice(107));
		//****************get information of customer that login***********
	//	System.out.println(customerService.getCustomerThatLogin());
	//	System.out.println(customerService.getAllCouponsForBuy());


	}

}
