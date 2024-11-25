package API.marketplace;

class AnnounceNotFoundException extends RuntimeException {

    AnnounceNotFoundException(Long id) {
        super("Could not find announce of id: " + id);
    }
}
