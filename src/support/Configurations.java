package support;
import java.io.InputStream;
import java.util.Properties;

public class Configurations {
	private static Configurations configurations;
	private Properties properties;
	private Configurations(){};
	public static Configurations getInstance(){
		if(configurations==null){
			configurations= new Configurations();
		}
		return configurations;
	}
	/**
	 * 
	 * Method name  : setConfigurationProperties
	 * Return types : void
	 * Description  :
	 */
	private void setConfigurationProperties(){
		InputStream inStream = this.getClass().getClassLoader()
				.getResourceAsStream("support/configurations.properties");
		properties = new Properties();
		try {
			properties.load(inStream);
		} catch (Exception e) {
			new Exception("Error in reading configuration file!!!");
		}
	}
	/**
	 * 
	 * Method name  : getProperty
	 * Return types : String
	 * Description  :
	 */
	public String getProperty(String property){
		if(properties==null){
			setConfigurationProperties();
		}
		if(properties.getProperty(property)!=null){
			return properties.getProperty(property);
		}else{
			return "No such property";
		}
	}

}