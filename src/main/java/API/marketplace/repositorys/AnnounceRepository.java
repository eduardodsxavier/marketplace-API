package API.marketplace.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import API.marketplace.models.Announce;

public interface AnnounceRepository extends JpaRepository<Announce, Long> {
    @Query("SELECT a FROM Announce a WHERE a.type = %?1")
    List<Announce> findByType(String type);

    @Query("SELECT a FROM Announce a WHERE a.seller = %?1")
    List<Announce> findBySeller(String seller);

    @Query("SELECT type FROM Announce")
    List<String> allTypes();

    @Query("SELECT seller FROM Announce")
    List<String> allSellers();

}
