package new_proj.project2.login;

import new_proj.project2.exceptions.NotFoundException;
import new_proj.project2.services.AdminService;
import new_proj.project2.services.ClientFacade;
import new_proj.project2.services.CompanyService;
import new_proj.project2.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class LoginManager {


    @Autowired
    private final ApplicationContext applicationContext;

    public LoginManager(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    public ClientFacade login(String email, String password, ClientType clientType) throws NotFoundException {
        switch(clientType){
            case ADMINISTRATOR:
                AdminService admins = applicationContext.getBean(AdminService.class);
                if(!admins.login(email, password))
                    throw new NotFoundException("admin not login");
                return admins;

            case CUSTOMER:
                CustomerService custs = applicationContext.getBean(CustomerService.class);
                if(!custs.login(email, password))
                    throw new NotFoundException("customer not login");
                return custs;

            case COMPANY:
                CompanyService compas = applicationContext.getBean(CompanyService.class);
                if(!compas.login(email, password))
                    throw new NotFoundException("company not login");
                return compas;

        }
        return null;
    }

}
