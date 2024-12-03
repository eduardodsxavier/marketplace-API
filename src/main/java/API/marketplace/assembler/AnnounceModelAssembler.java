package API.marketplace.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import API.marketplace.models.Announce;
import API.marketplace.models.link;
import API.marketplace.controllers.AnnounceController;


@Component
public class AnnounceModelAssembler implements RepresentationModelAssembler<Announce, EntityModel<Announce>> {

    @Override
    public EntityModel<Announce> toModel(Announce a) {

        EntityModel<Announce> AnnounceModel = EntityModel.of(a,
                linkTo(methodOn(AnnounceController.class).one(a.getId())).withSelfRel(),
                linkTo(methodOn(AnnounceController.class).remove(a.getId())).withRel("remove"),
                linkTo(methodOn(AnnounceController.class).searchByType(a.getType())).withRel("type"),
                linkTo(methodOn(AnnounceController.class).searchBySeller(a.getSeller())).withRel("seller"));

        return AnnounceModel;
    }

    public EntityModel<link> typeModel(String type) {
        return EntityModel.of(new link(type, linkTo(methodOn(AnnounceController.class).searchByType(type)).withRel(type)));
    }

    public EntityModel<link> sellerModel(String seller) {
        return EntityModel.of(new link(seller, linkTo(methodOn(AnnounceController.class).searchByType(seller)).withRel(seller)));
    }
}

