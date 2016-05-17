package br.com.fiescfrotas.util;

import java.io.StringWriter;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;

public class RestUtil {

	public Response buildListResponse(Object result) {

		StringWriter fw = new StringWriter();

		try {
			ObjectMapper mapper = new ObjectMapper();

			mapper.writeValue(fw, result);

			return Response.ok(fw.toString()).build();

		} catch (Exception ex) {
			return this.buildErrorResponse(ex.getMessage());
		}
	}

	public Response buildResponse(Object result) {

		Gson gson = new Gson();
		String resultString = gson.toJson(result);

		return Response.ok(resultString)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

	public Response buildErrorResponse(Object result, Response.Status status) {

		ResponseBuilder rb = Response.status(status);

		rb = rb.entity(result);
		rb = rb.type("text/plain");

		return rb.build();

	}

	public Response buildErrorResponse(Object result) {

		return buildErrorResponse(result,Response.Status.INTERNAL_SERVER_ERROR);

	}

	public Response buildUnathorizedResponse(Object result) {

		ResponseBuilder rb = Response.status(Response.Status.UNAUTHORIZED);

		rb = rb.entity(result);
		rb = rb.type("text/plain");

		return rb.build();
	}

	public Response buildForbiddenResponse(Object result) {

		ResponseBuilder rb = Response.status(Response.Status.FORBIDDEN);

		rb = rb.entity(result);
		rb = rb.type("text/plain");

		return rb.build();
	}

}
