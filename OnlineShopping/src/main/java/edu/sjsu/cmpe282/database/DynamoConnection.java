package edu.sjsu.cmpe282.database;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

public class DynamoConnection {
	private static DynamoConnection connection;
	private AmazonDynamoDBClient client;
	
	private DynamoConnection() {
		AWSCredentials credentials = null;
		try {
			credentials = new ProfileCredentialsProvider("default")
					.getCredentials();
		} catch (Exception e) {
			throw new AmazonClientException(
					"Cannot load the credentials from the credential profiles file. "
							+ "Please make sure that your credentials file is at the correct "
							+ "location (/home/saru/.aws/credentials), and is in valid format.",
					e);
		}
		client = new AmazonDynamoDBClient(credentials);
		Region usWest1 = Region.getRegion(Regions.US_WEST_1);
		client.setRegion(usWest1);
	}

	public  AmazonDynamoDBClient getClient() {
		return client;
	}
	
	public static DynamoConnection getInstance() {
		if (connection == null) {
			synchronized (DynamoConnection.class) {
				if (connection == null) {
					connection = new DynamoConnection();
				}
			}
		}
		return connection;
	}
}
