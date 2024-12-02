package API.marketplace.controllers;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import API.marketplace.repositorys.AnnounceRepository;
import API.marketplace.models.Announce;
import API.marketplace.assembler.AnnounceModelAssembler;
import API.marketplace.exceptions.AnnounceNotFoundException;

@RequestMapping(path="/announces")
@Controller
public class AnnounceController {
    private AnnounceRepository repository;
    private AnnounceModelAssembler assembler;

    AnnounceController (AnnounceRepository repository, AnnounceModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // return all the announces on the database
    @GetMapping("")
    @ResponseBody
    public CollectionModel<EntityModel<Announce>> all() {
        List<EntityModel<Announce>> announces = repository.findAll().stream().map(
                assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(announces, linkTo(methodOn(AnnounceController.class).all()).withSelfRel());
    }

    // return all the announces with given type on the database
    @GetMapping("/type")
    @ResponseBody
    public CollectionModel<EntityModel<Announce>> searchByType(@RequestParam("type") String type) {
        List<EntityModel<Announce>> announces = repository.findByType(type).stream().map(
                assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(announces, linkTo(methodOn(AnnounceController.class).all()).withSelfRel());
    }

    @GetMapping("/types")
    @ResponseBody
    public CollectionModel<EntityModel<Link>> allTypes() {
        return CollectionModel.of(repository.allTypes().stream().map(assembler::typeModel).collect(Collectors.toList()));
    }

    // return all the announces with given type on the database
    @GetMapping("/seller")
    @ResponseBody
    public CollectionModel<EntityModel<Announce>> searchBySeller(@RequestParam("seller") String seller) {
        List<EntityModel<Announce>> announces = repository.findBySeller(seller).stream().map(
                assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(announces, linkTo(methodOn(AnnounceController.class).all()).withSelfRel());
    }

    @GetMapping("/sellers")
    @ResponseBody
    public CollectionModel<EntityModel<Link>> allSellers() {
        return CollectionModel.of(repository.allSellers().stream().map(assembler::sellerModel).collect(Collectors.toList()));
    }

    // return the announces on the database with the id passed by the URL
    @GetMapping("/{id}")
    @ResponseBody
    public EntityModel<Announce> one(@PathVariable Long id) {
        Announce announce = repository.findById(id).orElseThrow(() -> new AnnounceNotFoundException(id));

        return assembler.toModel(announce);
    }

    // add the Announce passend in the body of the post
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<EntityModel<Announce>> newAnnounce(@RequestBody Announce a) {
        Announce newAnnounce = repository.save(a); 

        return ResponseEntity.created(linkTo(methodOn(AnnounceController.class).one(newAnnounce.getId())).toUri()).body(
                assembler.toModel(newAnnounce));
    } 

    // verify if the announce of id x exist if so change the values of the announce, if don't exist add the announce
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updateAnnounce(@RequestBody Announce newAnnounce, @PathVariable Long id) {
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

    // delete the announce of id passed to the URL
    @DeleteMapping("/{id}/remove")
    @ResponseBody
    public ResponseEntity<?> remove(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
