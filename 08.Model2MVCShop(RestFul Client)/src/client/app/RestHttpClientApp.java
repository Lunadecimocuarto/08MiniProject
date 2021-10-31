package client.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.service.domain.User;




public class RestHttpClientApp {
	
	// main Method
	public static void main(String[] args) throws Exception{
		
		////////////////////////////////////////////////////////////////////////////////////////////
		// �ּ��� �ϳ��� ó���ذ��� �ǽ�
		////////////////////////////////////////////////////////////////////////////////////////////
		
//		System.out.println("\n====================================\n");
//		// 1.1 Http Get ��� Request : JsonSimple lib ���
		//RestHttpClientApp.getUserTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Get ��� Request : CodeHaus lib ���
//		RestHttpClientApp.getUserTest_Codehaus();
		
//		System.out.println("\n====================================\n");
//		// 2.1 Http Post ��� Request : JsonSimple lib ���
//		RestHttpClientApp.LoginTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Post ��� Request : CodeHaus lib ���
		//RestHttpClientApp.LoginTest_Codehaus();		
	
		//�Ϸ�
		RestHttpClientApp.addUserTest_JsonSimple();
		
		//�Ϸ�
//		RestHttpClientApp.addUserTest_Codehaus();
		
		//�Ϸ�
//		RestHttpClientApp.updateUser_get();
		
		//�Ϸ�
//		RestHttpClientApp.updateUser_post();
		
		//�Ϸ�
//		RestHttpClientApp.listUser();
		
	
	}
	
	
//================================================================//
	//1.1 Http Protocol GET Request : JsonSimple 3rd party lib ���
	public static void getUserTest_JsonSimple() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= "http://127.0.0.1:8080/user/json/getUser/admin";
				
		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> �����б�(JSON Value Ȯ��)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	}
	
	
	//1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib ���
	public static void getUserTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= "http://127.0.0.1:8080/user/json/getUser/admin";

		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> �ٸ� ������� serverData ó�� 
		//System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 User user = objectMapper.readValue(jsonobj.toString(), User.class);
		 System.out.println(user);
	}
//================================================================//	
	
//================================================================//
	//2.1 Http Protocol POST Request : FromData ���� / JsonSimple 3rd party lib ���
	public static void LoginTest_JsonSimple() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/user/json/login";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		//[ ��� 1 : String ���]
//		String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//		HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
		
		//[ ��� 2 : JSONObject ���]
		JSONObject json = new JSONObject();
		json.put("userId", "admin");
		json.put("password", "1234");
		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> �����б�(JSON Value Ȯ��)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);

	}
	
	
	//2.2 Http Protocol POST ��� Request : FromData���� 
	//==> JsonSimple + codehaus 3rd party lib ���
	public static void LoginTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/user/json/login";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
//		//[ ��� 1 : String ���]
//		String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//		HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
	
//		//[ ��� 2 : JSONObject ���]
//		JSONObject json = new JSONObject();
//		json.put("userId", "admin");
//		json.put("password", "1234");
//		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
		
		//[ ��� 3 : codehaus ���]
		User user01 =  new User();
		user01.setUserId("admin");
		user01.setPassword("1234");
		ObjectMapper objectMapper01 = new ObjectMapper();
		//Object ==> JSON Value �� ��ȯ
		String jsonValue = objectMapper01.writeValueAsString(user01);
		HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
		
		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> �ٸ� ������� serverData ó�� 
		//System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println("jsonobj : "+jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 User user = objectMapper.readValue(jsonobj.toString(), User.class);
		 System.out.println(user);
	}	
	

	  public static void addUserTest_JsonSimple() throws Exception{ 
	  // HttpClient : Http Protocol �� client �߻�ȭ 
	  HttpClient httpClient = new DefaultHttpClient();
	  
	  String url = "http://127.0.0.1:8080/user/json/addUser"; 
	  
	  HttpPost httpPost =  new HttpPost(url);
	  httpPost.setHeader("Accept", "application/json");
	  httpPost.setHeader("Content-Type", "application/json");
	  
	  JSONObject json = new JSONObject();
	  json.put("userId", "testId");
	  json.put("password", "123456");
	  json.put("userName", "test�̸�");
	  
	  HttpEntity requestHttpEntity = new StringEntity(json.toString(), "utf-8");
	  httpPost.setEntity(requestHttpEntity);
	  
	  HttpResponse response = httpClient.execute(httpPost);
	  
	  System.out.println("response : "+response);
	  
	  HttpEntity responseHttpEntity = response.getEntity(); 
	  //entity���� �ٵ� �����.
	  
	  InputStream is = responseHttpEntity.getContent();
	  BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
	  System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
	  String serverData = br.readLine();
	  System.out.println("serverDate : "+serverData);
		
	
	  JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
	  System.out.println("jsonobj :"+jsonobj);
	  
	  }
	  
	  public static void addUserTest_Codehaus()	throws Exception{
		  
		  HttpClient httpClient = new DefaultHttpClient();

		  String url = "http://127.0.0.1:8080/user/json/addUser"; 
		  
		  HttpPost httpPost =  new HttpPost(url);
		  httpPost.setHeader("Accept", "application/json");
		  httpPost.setHeader("Content-Type", "application/json");
		  
		  User user = new User();
		  user.setUserId("testUserId22");
		  user.setUserName("codehausTest");
		  user.setPassword("0000000");
		  
		  ObjectMapper objectMapper = new ObjectMapper();
		  String jsonValue = objectMapper.writeValueAsString(user);
		  System.out.println(jsonValue);
		  
		  HttpEntity httpEntity = new StringEntity(jsonValue, "utf-8");
		  httpPost.setEntity(httpEntity);
		  
		  HttpResponse response = httpClient.execute(httpPost);
		  
		  HttpEntity resEntity = response.getEntity();
		  
		  InputStream is = resEntity.getContent();
		  BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
		  
		  JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		  
		 // ObjectMapper mapper = new ObjectMapper();
		 // User user01 = mapper.readValue(jsonobj.toString(), User.class);
		 // System.out.println(user01);
		 
	  }
	  
	  public static void updateUser_get() throws Exception{
		  
		  HttpClient httpClient = new DefaultHttpClient();
		  
		  String url = "http://127.0.0.1:8080/user/json/updateUser/testUserId22";
		  
		  HttpGet httpGet = new HttpGet(url);
		  httpGet.setHeader("Accept","application/json");
		  httpGet.setHeader("Content-Type", "application/json");
		  		  
		  HttpResponse response = httpClient.execute(httpGet);
		  
		  System.out.println(response);
		  
		  HttpEntity resEntity=response.getEntity();
		  
		  InputStream is=resEntity.getContent();
		  BufferedReader br=new BufferedReader(new InputStreamReader(is,"utf-8"));
		  
		  JSONObject jsonobj=(JSONObject)JSONValue.parse(br);
		  
	  }
	  
 public static void updateUser_post() throws Exception{
		  
		  HttpClient httpClient = new DefaultHttpClient();
		  
		  String url = "http://127.0.0.1:8080/user/json/updateUser";
		  
		  HttpPost httpPost = new HttpPost(url);
		  httpPost.setHeader("Accept","application/json");
		  httpPost.setHeader("Content-Type", "application/json");
		  
		  User user=new User();
		  user.setUserId("testUserId22");
		  user.setAddr("�ּ�");
		  user.setUserName("�̸�");
		  
		  ObjectMapper objectMapper = new ObjectMapper();
		  
		  String jsonValue = objectMapper.writeValueAsString(user);
		  System.out.println("jsonValue:"+jsonValue);
		  
		  HttpEntity reqHttpEntity = new StringEntity(jsonValue,"utf-8");
		  httpPost.setEntity(reqHttpEntity);
		  		  
		  HttpResponse response = httpClient.execute(httpPost);
		  
		  System.out.println("res:"+response);
		  
		  HttpEntity resEntity=response.getEntity();
		  
		  InputStream is=resEntity.getContent();
		  BufferedReader br=new BufferedReader(new InputStreamReader(is,"utf-8"));
		  
		 // System.out.println("[Server���� ���� Data Ȯ��]");
		 // String serverData=br.readLine();
		 // System.out.println("serverData:"+serverData);
		  
		  //JSONValue :: ���ڿ��� JSON��üȭ
//		  JSONObject jsonobj=(JSONObject)JSONValue.parse(br);
//		  
//		  ObjectMapper objMapper=new ObjectMapper();
//		  User returnUser=objMapper.readValue(jsonobj.toString(),User.class);
//
//		  System.out.println("returnUser:"+returnUser);
		  
		  
	  }
	  
	  public static void listUser() throws Exception{
		  
		  HttpClient httpClient = new DefaultHttpClient();
		  
		  String url = "http://127.0.0.1:8080/user/json/listUser";
		 		  
		  HttpGet httpGet = new HttpGet(url);
		  httpGet.setHeader("Accept","application/json");
		  httpGet.setHeader("Content-Type", "application/json");
		  
		  HttpResponse httpResponse = httpClient.execute(httpGet);
		  
		  System.out.println(httpResponse);
		  
		  HttpEntity resEntity = httpResponse.getEntity();
		  
		  InputStream is = resEntity.getContent();
		  BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
		  
		  JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		  ObjectMapper objMapper = new ObjectMapper();
		  
		  System.out.println(jsonobj);
		  
	  }
	  

}