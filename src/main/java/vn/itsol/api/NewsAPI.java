package vn.itsol.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import vn.itsol.model.NewsModel;
import vn.itsol.service.INewsService;
import vn.itsol.service.impl.NewsService;

@Path("/news-api")
public class NewsAPI {

	INewsService newsService = new NewsService();
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<NewsModel> findAll() {
		List<NewsModel> listshow = newsService.findAll();
		return listshow;
	}

	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public NewsModel findById(@PathParam("id") int id) {
		return newsService.findById(id);
	}

	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertNews(NewsModel newsM) {

		Integer check = newsService.insertNews(newsM);
		NewsModel test = newsService.findById(check);
		return Response.status(201).entity(test).build();

	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateNews(NewsModel newsM) {
		newsService.updateNews(newsM);
		NewsModel test = newsService.findById(newsM.getNewsId());
		if (test != null) {
			return Response.status(201).entity(test).build();
		}
		return Response.status(201).entity("update false !").build();

	}

	@PUT
	@Path("/delete")
	public Response hideNews(NewsModel newsM) {
		boolean check = newsService.hideNews(newsM);

		if (check) {
			return Response.status(201).entity("hide successfully !").build();
		}
		return Response.status(201).entity("update false !").build();

	}

	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteNews(NewsModel newsM) {
		boolean check = newsService.deleteNews(newsM);
		if (check) {
			return Response.status(202).entity("delete successfully !").build();
		}
		return Response.status(202).entity("delete false !").build();

	}


}
