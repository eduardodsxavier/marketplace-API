package API.marketplace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/announces")
public class AnnounceController {
    @Autowired
    private AnnounceRepository repository;

    AnnounceController (AnnounceRepository repository) {
        this.repository = repository;
    }
}
