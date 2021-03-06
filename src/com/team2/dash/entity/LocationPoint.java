package com.team2.dash.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.format.DateFormat;

public class LocationPoint implements Parcelable{
	int		_id;			// primary key
	int		workout_id;		// 
	int 	time;			// real time of the reading
	int		workoutTime;	// time since workout started excluding breaks
	double	latitude;
	double 	longtitude;
	double	altitude;
	
	
	public LocationPoint(Parcel in){
		_id = in.readInt();
		workout_id = in.readInt();
		time = in.readInt();
		workoutTime = in.readInt();
		latitude = in.readDouble();
		longtitude = in.readDouble();
		altitude = in.readDouble();
	}
	
	public LocationPoint( int _id, int workout_id, int time, int workoutTime, double latitude, 
			double longtitude, double altitude){
		this._id = _id;
		this.workout_id = workout_id;
		this.time = time;
		this.workoutTime = workoutTime;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.altitude = altitude;
	}
	
    @Override
	public String toString(){
    	
		String txt = DateFormat.format("MM/dd/yy h:mm:ssaa ", time).toString();
		return (txt);
	}
	
	public int getID(){
		return	this._id;
	}
	public void setID( int _id ){
		this._id = _id;
	}

	public int getWorkoutID(){
		return	this.workout_id;
	}
	public void setWorkoutID( int workout_id ){
		this.workout_id = workout_id;
	}

	public int getTime(){
		return	this.time;
	}
	public void setTime( int time ){
		this.time = time;
	}

	public int getWorkoutTime(){
		return	this.workoutTime;
	}
	public void setWorkoutTime( int workoutTime ){
		this.workoutTime = workoutTime;
	}

	public double getLatitude(){
		return	this.latitude;
	}
	public void setLatitude( double latitude ){
		this.latitude = latitude;
	}

	public double getLongtitude(){
		return	this.longtitude;
	}
	public void setLongtitude( double longtitude ){
		this.longtitude = longtitude;
	}

	public double getAltitude(){
		return	this.altitude;
	}
	public void setAltitude( double altitude ){
		this.altitude = altitude;
	}

	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel dest, int arg1) {
		dest.writeInt(_id);
		dest.writeInt(workout_id);
		dest.writeInt(time);
		dest.writeInt(workoutTime);
		dest.writeDouble(latitude);
		dest.writeDouble(longtitude);
		dest.writeDouble(altitude);
		
	}
	
public static final Parcelable.Creator<LocationPoint> CREATOR = new Creator<LocationPoint>() {
		
		public LocationPoint[] newArray(int size) {
			// TODO Auto-generated method stub
			return new LocationPoint[size];
		}
		
		public LocationPoint createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new LocationPoint(source);
		}
		
		
	};


}
