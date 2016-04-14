package JsonSort.JsonSort;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * Hello world!
 *
 */
public class App 
{
	static TreeMap<String, Object> mainTreeMap;
	
	public static void main( String[] args )
	{
		JsonElement root;
		mainTreeMap = new TreeMap<String, Object>();
		try {
			root = new JsonParser().parse(new FileReader("/Users/sannigeri/Desktop/file1.json"));
			JsonObject jsonObject = root.getAsJsonObject();
			
			mainTreeMap = sortJsonElement(jsonObject);
			
			System.out.println(mainTreeMap.toString().replace("=", ":"));
			
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static TreeMap<String, Object> sortJsonElement (JsonObject jsonObject){//, TreeMap<String, Object> parentMap){
		TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
		
		for (Entry<String, JsonElement> entry : jsonObject.entrySet()) {
			if(entry.getValue().isJsonObject()){				
				treeMap.put(entry.getKey(), sortJsonElement(entry.getValue().getAsJsonObject()));
			}else{
					treeMap.put(entry.getKey(), entry.getValue());
			}
		}
		
		return treeMap;
	}
}
