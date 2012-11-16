package com.team2.dash;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/*
 * This class has sends html off to the server specified in ServerURL to have something done with it
 * An example call to this could be:
 * JSONObject j1 = sc.ConnectAndSendHTML(null, "GetTime.php?TimeZone=England, false); 
 * This should theortically support CodeIgniter, but it's untested currently
 */	

public class ServerConnector
{
	//Current website URL
	private static final String ServerURL = "http://ec2-54-242-181-128.compute-1.amazonaws.com";	
	private String responseString;
	
	/*
	 * This is the method that we will call to send, then receive data.
	 * PHP files should always return some form of a response, even if it's just json_decode(Array("success" => false));
	 *
	 *@vars = Data to be POSTed to the server (in SimplePHP) or for the URL in CodeIgniter (only uses vars[i][1])
	 *@webPage = Page for data to be sent to
	 *@useCodeIgniter = Are we using CodeIgniter or Simple PHP?
	 */	
	public ServerConnector (String[][] vars, String webPage, boolean useCodeIgniter)
	{			
		if(useCodeIgniter == true) 
		{
			ConnectAndSendUsingCodeIgniter(vars, webPage);
		}
		//Use PHP's native POST / GET rather than a framework
		else 
		{
			ConnectAndSendSimplePHP(vars, webPage);
		}		
	}	
	

	/*
	 * This will build a codeigniter url (as it takes GET variables differently to simple PHP) and then call that URL
	 * PHP files should always return some form of a response, even if it's just json_decode(Array("success" => false));
	 *
	 *@vars = Data to sent via URL in CodeIgniter (only uses vars[i][1])
	 *@webPage = Page for data to be sent to
	 */
	private void ConnectAndSendUsingCodeIgniter(String[][] vars, String webPage)
	{
		String temp = null;	
		HttpClient httpclient = new DefaultHttpClient();
    	String newURL = "";
		
    	//Lets build our code igniter URL here
    	if (vars != null && vars.length > 0)
		{    	    
    	    for(String[] singleString : vars)
    	    {
    	    	newURL = newURL + "/" + singleString[1];		    	    
    	    }	    	    	    	        	    
		}
		
		HttpPost httppost = new HttpPost(ServerURL + "/" + webPage + newURL);   	
		
    	try 
    	{

    	    HttpResponse response = httpclient.execute(httppost);    	        	    
    	    HttpEntity entity = response.getEntity();    	    
    	    temp = EntityUtils.toString(entity);

    	    if(entity != null)
    	    {	
    	    	//JSONObject CIResults = new JSONObject(temp);
        	    //return CIResults;
    	    	responseString = temp;
    	    } 
    	    else 
    	    {
    	    	return;    	    	
    	    }
    	
    	} 
    	catch (ClientProtocolException e) 
    	{ 
    		e.printStackTrace(); 
    		Log.v("Error", "SC ClientProtocol " + e.getMessage()); 
    		return; 
    	}    	
    	catch (IOException e) 
    	{ 
    		e.printStackTrace(); 
    		Log.v("Error", "SC IOException " + e.getMessage());     		
    		return;
    	} 
    	catch (Exception e) 
    	{ 
    		e.printStackTrace(); 
    		Log.v("Error", "SC Exception " + e.getMessage());        		
    		return;
    	}  
	}
	
	/*
	 * This will build a FORM for POSTing Data and then call the URL
	 * PHP files should always return some form of a response, even if it's just json_decode(Array("success" => false));
	 *
	 *@vars = Data to sent via URL in CodeIgniter (only uses vars[i][1])
	 *@webPage = Page for data to be sent to
	 */	
	private void ConnectAndSendSimplePHP(String[][] vars, String webPage)
	{	
		String temp = null;	
    	HttpClient httpclient = new DefaultHttpClient();
    	HttpPost httppost = new HttpPost(ServerURL + "/" + webPage);       	
		
    	try 
    	{
    		
    		if (vars != null && vars.length > 0)
    		{
	    	    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(vars.length);
	    	    for(String[] singleString : vars)
	    	    {
		    	    nameValuePairs.add(new BasicNameValuePair(singleString[0], singleString[1]));		    	    
	    	    }	    	    	    	    
	    	    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
    		}
    	 
    	    HttpResponse response = httpclient.execute(httppost);    	        	    
    	    HttpEntity entity = response.getEntity();    	    
    	    temp = EntityUtils.toString(entity);

    	    if(entity != null)
    	    {	
    	    	//JSONObject SimpleResults = new JSONObject(temp);
        	    //return SimpleResults;
    	    	responseString = temp;    	    	
    	    } 
    	    else 
    	    {
    	    	responseString = null;
    	    	return;    	    	
    	    }
    	
    	} 
    	catch (ClientProtocolException e) 
    	{ 
    		e.printStackTrace(); 
    		Log.v("Error", "SC ClientProtocol " + e.getMessage()); 
    		return; 
    	}    	
    	catch (IOException e) 
    	{ 
    		e.printStackTrace(); 
    		Log.v("Error", "SC IOException " + e.getMessage());     		
    		return;
    	} 
    	catch (Exception e) 
    	{ 
    		e.printStackTrace(); 
    		Log.v("Error", "SC Exception " + e.getMessage());        		
    		return;
    	}     	
	}
	
	public JSONObject ConvertStringToObject(String response)
	{
		if(response == null)
		{
			response = responseString;
		}
		try
		{
	    	JSONObject SimpleResults = new JSONObject(response);
    	    return SimpleResults;				
		}
		catch (JSONException e)
		{
    		e.printStackTrace(); 
    		Log.v("Error", "SC JSONException " + e.getMessage());   
		}
		return null;
	}
}
