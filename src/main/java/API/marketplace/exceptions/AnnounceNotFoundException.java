package API.marketplace.exceptions;

public class AnnounceNotFoundException extends RuntimeException {

    public AnnounceNotFoundException(Long id) {
        super("Could not find announce of id: " + id);
    }
}
