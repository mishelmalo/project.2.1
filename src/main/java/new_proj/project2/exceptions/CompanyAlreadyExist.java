package new_proj.project2.exceptions;

public class CompanyAlreadyExist extends Exception{
    public CompanyAlreadyExist(){
        super("  companies have that email or name");
    }
}
