package br.com.chaordic.sweetspot.resources;

import java.net.URI;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.chaordic.sweetspot.core.exception.RequestExistsException;
import br.com.chaordic.sweetspot.core.manager.RequestManager;
import br.com.chaordic.sweetspot.core.pojo.CreateRequest;
import br.com.chaordic.sweetspot.core.pojo.RequestInfo;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;

@Path("/sweetspot/requests")
@Produces(MediaType.APPLICATION_JSON)
public class RequestsResource {

    private RequestManager requestManager;

    public RequestsResource(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    @POST
    @Timed
    public Response createRequest(CreateRequest request) {
        try {
            this.requestManager.create(request);
            return Response.created(URI.create(request.getId())).build();
        } catch (RequestExistsException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @GET
    @Timed
    @Path("{requestId}")
    public Response getRequest(@PathParam("requestId") String requestId) {
        Optional<RequestInfo> optionalInfo = this.requestManager.getInfo(requestId);
        if (optionalInfo.isPresent()) {
            return Response.ok(optionalInfo.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Timed
    public Response getAllRequests() {
        Set<String> requestIds = this.requestManager.getRequestIds();
        String reqList = requestIds.isEmpty()? "No requests." : Joiner.on('\n').join(requestIds);
        return Response.ok(reqList).build();
    }
}
