package API.marketplace.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import API.marketplace.models.Announce;

public interface AnnounceRepository extends JpaRepository<Announce, Long> {

}
