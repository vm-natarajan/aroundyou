package news.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import support.mDB;

@Path("/")
public class NewsServices {

	
	@GET
	@Path("/section")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getNewsBySection(@QueryParam("sectionName") String sectionName) {
		mDB db = mDB.getInstance();
		String news = db.readNews(sectionName);
		return Response.status(200).entity(news.toString()).build();
	}

	/*@GET
	@Path("/section")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getNewsBySection(@QueryParam("sectionName") String sectionName,@QueryParam("count") int count) {
		mDB db = mDB.getInstance();
		String news = db.readNews(sectionName,count);
		return Response.status(200).entity(news.toString()).build();
	}*/

	
	@GET
	@Path("/search")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response searchNewsByKeyword(@QueryParam("text") String text) {
		mDB db = mDB.getInstance();
		String news = db.searchNewsBy(text);
		return Response.status(200).entity(news.toString()).build();
	}

	@GET
	@Path("/period")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response searchNewsByPeriodAndSection(@QueryParam("period") String period,@QueryParam("section") String section) {
		mDB db = mDB.getInstance();
		String news = db.searchNewsByPeriodAndSection(period,section);
		return Response.status(200).entity(news.toString()).build();
	}
	
	@GET
	@Path("/source")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response searchNewsBySourceAndSection(@QueryParam("source") String source,@QueryParam("section") String section) {
		mDB db = mDB.getInstance();
		String news = db.readNews(source,section);
		return Response.status(200).entity(news.toString()).build();
	}
}
