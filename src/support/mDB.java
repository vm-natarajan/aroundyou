package support;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.bson.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class mDB {
	private static mDB mdb;
	Configurations configuration = Configurations.getInstance();
	String host = configuration.getProperty("Host");
	String dbPort = configuration.getProperty("Port");
	String dbName = configuration.getProperty("DatabaseName");
	String dbUsername = configuration.getProperty("UserName");
	String dbPassword = configuration.getProperty("Password");
	String dbUrl = "mongodb://"+dbUsername+":"+dbPassword+"@"+host+":"+dbPort+"/"+dbName;
	MongoClientURI uri  = new MongoClientURI(dbUrl); 
	MongoClient client = new MongoClient(uri);
	MongoDatabase db = client.getDatabase(uri.getDatabase());

	public static mDB getInstance(){
		if(mdb==null){
			mdb= new mDB();
		}
		return mdb;
	}


	public String readNews(String sectionName) {
		BasicDBObject criteria = new BasicDBObject();
		if(!sectionName.equalsIgnoreCase("home")) {
			criteria.append("section", sectionName);
		}else {
			criteria.append("section", Pattern.compile(".*"));
		}
		MongoCursor<Document> cursor = db.getCollection("newsfeeds").find(criteria).sort(new BasicDBObject("date", -1)).iterator();
		List<String> items = new ArrayList<>();
		try {
			while (cursor.hasNext()) {
				items.add(cursor.next().toJson());
			}
		} finally {
			cursor.close();
		}

		//client.close();

		return "[" + String.join(", ", items) + "]";
	}

	
	public String readNews(String source,String section) {
		BasicDBObject criteria = new BasicDBObject();
		criteria.append("source", source);
		criteria.append("section", section);
		MongoCursor<Document> cursor = db.getCollection("newsfeeds").find(criteria).sort(new BasicDBObject("date", -1)).iterator();
		List<String> items = new ArrayList<>();
		try {
			while (cursor.hasNext()) {
				items.add(cursor.next().toJson());
			}
		} finally {
			cursor.close();
		}

		//client.close();

		return "[" + String.join(", ", items) + "]";
	}

	
	public String readNews(String sectionName,int count) {
		BasicDBObject criteria = new BasicDBObject();
		criteria.append("section", sectionName);
		MongoCursor<Document> cursor = db.getCollection("newsfeeds").find(criteria).sort(new BasicDBObject("date", -1)).limit(count).iterator();
		List<String> items = new ArrayList<>();
		try {
			while (cursor.hasNext()) {
				items.add(cursor.next().toJson());
			}
		} finally {
			cursor.close();
		}

		//client.close();

		return "[" + String.join(", ", items) + "]";
	}
	
	public String searchNewsBy(String searchKeyword) {
		
		MongoCollection<Document> collection = db.getCollection("newsfeeds");
		BasicDBObject criteria = new BasicDBObject();
		criteria.put("title", "text");
		criteria.put("description", "text");
		collection.createIndex(criteria);
		MongoCursor<Document> cursor = collection.find(new BasicDBObject("$text", new BasicDBObject("$search", searchKeyword))).sort(new BasicDBObject("date", -1)).iterator();
		List<String> items = new ArrayList<>();
		try {
			while (cursor.hasNext()) {
				items.add(cursor.next().toJson());
			}
		} finally {
			cursor.close();
		}
		//client.close();
		return "[" + String.join(", ", items) + "]";
	}
	
	public String searchNewsByPeriodAndSection(String period,String section) {
		;
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calender = Calendar.getInstance();
		calender.setTime(new Date());
		calender.add(Calendar.DATE, Integer.parseInt(period)*(-1));
		String date = formater.format(calender.getTime());
		BasicDBObject criteria = new BasicDBObject();
		criteria.append("date", new BasicDBObject("$gt",date));
		criteria.append("section", section);
		MongoCursor<Document> cursor = db.getCollection("newsfeeds").find(criteria).sort(new BasicDBObject("date", -1)).iterator();
		List<String> items = new ArrayList<>();
		try {
			while (cursor.hasNext()) {
				items.add(cursor.next().toJson());
			}
		} finally {
			cursor.close();
		}

		//client.close();

		return "[" + String.join(", ", items) + "]";
	}
	
}
