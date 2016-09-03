package org.wildfly.swarm.jpajaxrscdi.resource;

import org.wildfly.swarm.jpajaxrscdi.dto.MessageDTO;
import org.wildfly.swarm.jpajaxrscdi.service.MessageService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/messages")
@RequestScoped
public class MessageResource {

    @Inject
    private MessageService messageService;

    @GET
    @Produces("application/json")
    public List<MessageDTO> getMessages() {
        return messageService.getMessages();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public MessageDTO getMessage(@PathParam("id") long messageId) {
        return messageService.getMessage(messageId);
    }

    @POST
    @Produces("application/json")
    public MessageDTO saveMessage(MessageDTO message) throws Exception {
        return messageService.saveMessage(message);
    }
}
