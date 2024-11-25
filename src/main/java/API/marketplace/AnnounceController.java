package API.marketplace;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path="/announces")
@Controller
public class AnnounceController {
    private AnnounceRepository repository;
    private AnnounceModelAssembler assembler;

    AnnounceController (AnnounceRepository repository, AnnounceModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/all")
    @ResponseBody
    CollectionModel<EntityModel<Announce>> all() {
        List<EntityModel<Announce>> announces = repository.findAll().stream().map(
                assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(announces, linkTo(methodOn(AnnounceController.class).all()).withSelfRel());
    }

    @GetMapping("/all?type={type}")
    @ResponseBody
    CollectionModel<EntityModel<Announce>> all(@RequestParam String type) {
        List<EntityModel<Announce>> announces = repository.findAll().stream().map(
                assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(announces, linkTo(methodOn(AnnounceController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}")
    @ResponseBody
    EntityModel<Announce> one(@PathVariable Long id) {
        Announce announce = repository.findById(id).orElseThrow(() -> new AnnounceNotFoundException(id));

        return assembler.toModel(announce);
    }

    @PostMapping("/add")
    @ResponseBody
    ResponseEntity<EntityModel<Announce>> newAnnounce(@RequestBody Announce a) {
        Announce newAnnounce = repository.save(a); 

        return ResponseEntity.created(linkTo(methodOn(AnnounceController.class).one(newAnnounce.getId())).toUri()).body(
                assembler.toModel(newAnnounce));

    } 

    @DeleteMapping("/{id}")
    @ResponseBody
    ResponseEntity<?> remove(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ResponseBody
    ResponseEntity<?> updateAnnounce(@RequestBody Announce newAnnounce, @PathVariable Long id) {
        Announce updateAnnounce = repository.findById(id).map(announce -> {
            announce.setName(newAnnounce.getName());
            announce.setDescription(newAnnounce.getDescription());
            announce.setType(newAnnounce.getType());
            announce.setSeller(newAnnounce.getSeller());
            announce.setValue(newAnnounce.getValue());
            return repository.save(announce);
        }).orElseGet(() -> {
            return repository.save(newAnnounce);
        });

        return ResponseEntity //
            .created(assembler.toModel(updateAnnounce).getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(assembler.toModel(updateAnnounce));
    }
}
