package API.marketplace;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface AnnounceRepository extends JpaRepository<Announce, Long> {

    @Query("select * from Announces where type = ?1")
    Announce findByType(String emailAddress);
}
