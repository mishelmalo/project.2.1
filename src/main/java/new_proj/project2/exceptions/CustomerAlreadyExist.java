package new_proj.project2.exceptions;

public class CustomerAlreadyExist extends Exception{
    public CustomerAlreadyExist(){
        super(" that customer email exist try again ");
    }
}
