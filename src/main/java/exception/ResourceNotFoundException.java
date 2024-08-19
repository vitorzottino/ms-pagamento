package exception;

public class ResourceNotFoundException extends RuntimeException{

    public  ResourceNotFoundException(Long id){
        super("Recurso não encotrado! ID: " + id);
    }
}
