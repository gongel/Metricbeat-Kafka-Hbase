

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;
import net.sf.json.JSONObject;

public class JsonReader {
	private String json_string;
	private JSONObject obj;
	
	public JsonReader(String json_string) {
		this.json_string = json_string;
		obj=JSONObject.fromObject(this.json_string);
	}
	public String TimeTran(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String formatStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		return formatStr;
	}

//	public void reader()  {
//		
//		String value=new DateTime((String)obj.get("@timestamp")).toDate().toString();
//		System.out.println("time is:"+TimeTran(value));
//		JSONObject metricset=(JSONObject) obj.get("metricset");
//		Object metricset_name = metricset.get("name");
//		System.out.println("metricset'name is "+metricset_name);
//		JSONObject system=(JSONObject) obj.get("system");
//		System.out.println(metricset_name+" is "+system.get(metricset_name));
//		JSONObject host_obj=(JSONObject) obj.get("host");
//		System.out.println("host is:"+host_obj.get("name"));
//	}
	
	public String get_host() {
//		JSONObject obj=JSONObject.fromObject(json_string);
		JSONObject host_obj=(JSONObject) obj.get("host");
		return host_obj.get("name").toString();
	}
	
	public String get_time() {
//		JSONObject obj=JSONObject.fromObject(json_string);
		String value=new DateTime((String)obj.get("@timestamp")).toDate().toString();
		return TimeTran(value);
	}
	
	public String get_metricname() {
//		JSONObject obj=JSONObject.fromObject(json_string);
		JSONObject metricset=(JSONObject) obj.get("metricset");
		return metricset.get("name").toString();
		 
	}
	public String get_system() {
//		JSONObject obj=JSONObject.fromObject(json_string);
		JSONObject system=(JSONObject) obj.get("system");
		return system.get(get_metricname()).toString();
	}
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		String json_string="{\r\n" + 
				"	\"@timestamp\":\"2018-07-21T10:03:50.455Z\",\r\n" + 
				"	\"@metadata\":\r\n" + 
				"	{\r\n" + 
				"		\"beat\":\"metricbeat\",\r\n" + 
				"		\"type\":\"doc\",\r\n" + 
				"		\"version\":\"6.3.1\",\r\n" + 
				"		\"topic\":\"metric-m\"\r\n" + 
				"	},\r\n" + 
				"	\"metricset\":\r\n" + 
				"	{\r\n" +  
				"		\"rtt\":67292,\r\n" + 
				"		\"name\":\"process\",\r\n" + 
				"		\"module\":\"system\"\r\n" + 
				"	},\r\n" + 
				"	\"beat\":\r\n" + 
				"	{\r\n" + 
				"		\"name\":\"master\",\r\n" + 
				"		\"hostname\":\"master\",\r\n" + 
				"		\"version\":\"6.3.1\"\r\n" + 
				"	},\r\n" + 
				"	\"host\":\r\n" + 
				"	{\r\n" + 
				"		\"name\":\"master\"\r\n" + 
				"	},\r\n" + 
				"	\"system\":\r\n" + 
				"	{\r\n" + 
				"		\"process\":\r\n" + 
				"		{\r\n" + 
				"			\"name\":\"pickup\",\r\n" + 
				"			\"cmdline\":\"pickup -l -t fifo -u\",\r\n" + 
				"			\"cpu\":\r\n" + 
				"			{\r\n" + 
				"				\"total\":\r\n" + 
				"				{\r\n" + 
				"					\"value\":10,\r\n" + 
				"					\"pct\":0,\r\n" + 
				"					\"norm\":\r\n" + 
				"					{\r\n" + 
				"						\"pct\":0\r\n" + 
				"					}\r\n" + 
				"				},\r\n" + 
				"				\"start_time\":\"2018-07-21T09:28:38.000Z\"\r\n" + 
				"			},\r\n" + 
				"			\"pid\":1634,\r\n" + 
				"			\"pgid\":1615,\r\n" + 
				"			\"memory\":\r\n" + 
				"			{\r\n" + 
				"				\"share\":2633728,\r\n" + 
				"				\"size\":85450752,\r\n" + 
				"				\"rss\":\r\n" + 
				"				{\r\n" + 
				"					\"bytes\":3547136,\r\n" + 
				"					\"pct\":0.0001\r\n" + 
				"				}\r\n" + 
				"			},\r\n" + 
				"			\"cwd\":\"/var/spool/postfix\",\r\n" + 
				"			\"fd\":\r\n" + 
				"			{\r\n" + 
				"				\"open\":9,\r\n" + 
				"				\"limit\":\r\n" + 
				"				{\r\n" + 
				"					\"soft\":4096,\r\n" + 
				"					\"hard\":4096\r\n" + 
				"				}\r\n" + 
				"			},\r\n" + 
				"			\"ppid\":1615,\r\n" + 
				"			\"state\":\"sleeping\",\r\n" + 
				"			\"username\":\"postfix\"\r\n" + 
				"		}\r\n" + 
				"	}\r\n" + 
				"}";
		JsonReader jr=new JsonReader(json_string);
//		new JsonReader(json_string).reader();
		System.out.println(jr.get_host());;
		System.out.println(jr.get_metricname());
		System.out.println(jr.get_system());
		System.out.println(jr.get_time());
	}
}
