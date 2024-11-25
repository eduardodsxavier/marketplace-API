package API.marketplace;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class AnnounceModelAssembler implements RepresentationModelAssembler<Announce, EntityModel<Announce>> {

    @Override
    public EntityModel<Announce> toModel(Announce a) {

        EntityModel<Announce> todoModel = EntityModel.of(a,
                linkTo(methodOn(AnnounceController.class).one(a.getId())).withSelfRel());

        return  todoModel;
    }
}
