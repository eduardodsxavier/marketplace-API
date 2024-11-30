package API.marketplace.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import API.marketplace.models.Announce;
import API.marketplace.controllers.AnnounceController;


@Component
public class AnnounceModelAssembler implements RepresentationModelAssembler<Announce, EntityModel<Announce>> {

    @Override
    public EntityModel<Announce> toModel(Announce a) {

        EntityModel<Announce> todoModel = EntityModel.of(a,
                linkTo(methodOn(AnnounceController.class).one(a.getId())).withSelfRel(),
                linkTo(methodOn(AnnounceController.class).all()).withRel("announces"),
                linkTo(methodOn(AnnounceController.class).remove(a.getId())).withRel("remove"));

        return todoModel;
    }
}
