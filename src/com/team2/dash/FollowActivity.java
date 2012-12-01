package com.team2.dash;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.team2.dash.entity.VenueInfo;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class FollowActivity extends Activity {
	
	/**
	 * Private members
	 */
	private int followId;   //This is going to be for the user we have displayed
	private int userId;		// this is actual server user ID	

    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        // display the activity_follow.xml layout screen
        setContentView(R.layout.activity_follow);
        
        Bundle bundle = getIntent().getExtras();
        followId = bundle.getInt("followId");        
        userId = bundle.getInt("userId");        
        
 /*   	String[][] vars = new String[6][2];
    	vars[0][0] = "user_id"; 
    	vars[0][1] = userId + "";  
    	vars[1][0] = "location_id";
    	vars[1][1] = venue.getId().toString();
    	vars[2][0] = "location_name";
    	vars[2][1] = venue.getVenueName();
    	vars[3][0] = "location_address";     			
    	vars[3][1] = venue.getVenueAddress();
    	vars[4][0] = "location_lat";
    	vars[4][1] = venue.getLatitude() + "";
    	vars[5][0] = "location_lng";
    	vars[5][1] = venue.getLongitude() + "";  	    
    	
    	String response;
    	try
    	{
    		ServerConnector sc = new ServerConnector(vars, true, CheckIn.this, "Contacting Dash Server ...");
    		response = sc.execute(new String[] {  getResources().getString(R.string.checkinUser) }).get(5, TimeUnit.SECONDS);    	
    	} 
    	catch (Exception e)
    	{
    		e.printStackTrace();
			Log.v("Error", "CheckIn Exception " + e.getMessage());   
			return;
    	}
    	*/
       //TODO: Go off and get data.
        
        //TODO: Show list with who this user is following
        
        //TODO: Follow button        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.activity_follow, menu);
        return true;
    }
    
    public void onClickUserReviews(View view) {
    	
    }
    public void onClickUserFollowers(View view) {
    	
    }
    public void onClickFollowingUsers(View view) {
    	
    }
    public void onClickUserCheckins(View view) {
    	
    }
    public void onClickFollowUser(View view) {
    	
       	String[][] vars = new String[2][2];
    	vars[0][0] = "userid"; 
    	vars[0][1] = userId + "";  
    	vars[1][0] = "followid";
    	vars[1][1] = followId + "";  
    	
    	String response;
    	try
    	{
    		ServerConnector sc = new ServerConnector(vars, true, FollowActivity.this, "Contacting Dash Server ...");
    		response = sc.execute(new String[] {  getResources().getString(R.string.followUser) }).get(5, TimeUnit.SECONDS);    	
    	} 
    	catch (Exception e)
    	{
    		e.printStackTrace();
			Log.v("Error", "CheckIn Exception " + e.getMessage());   
			return;
    	}

		JSONObject results = ServerConnector.ConvertStringToObject(response);

		if (results == null) {
			Toast.makeText(this, "Unable to pull results", Toast.LENGTH_SHORT).show();
			return;
		}

		try
    	{    		
			String status = results.getString("status");

    		if( status.equals("true") ){
    			Toast.makeText(this, "You are following selected user", Toast.LENGTH_SHORT).show();
    		}
    		else{
    			String txt = "Error: " + status + "\n" + results.getString("error");
    			Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
    			return;
    		}
    			
    	}      	
	    catch (JSONException e)
	    {
	    	Toast.makeText(this, "Unable to pull results", Toast.LENGTH_SHORT).show();
			Log.v("Error", "JSONException " + e.getMessage());    			
			return;
	    }    	  

      }

    
}
