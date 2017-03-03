package ee.uptime.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import ee.uptime.util.SignedRequestsHelper;

public class AmazonUrl {
	public List<Amazon> amazonUrl(String searchKey, int page) {
		String awsAccessKeyId = null;
		String awsSecretKey = null;
		String endpoint = null;
		String associateTag = null;
		
		Properties properties = new Properties();
		Thread currentThread = Thread.currentThread();
		ClassLoader contextClassLoader = currentThread.getContextClassLoader();
		InputStream input = contextClassLoader.getResourceAsStream("amazon.properties");

		try {
			if (input != null) {
				properties.load(input);
				awsAccessKeyId = properties.getProperty("AWSACCESSKEYID");
				awsSecretKey = properties.getProperty("AWSSECRETKEY");
				endpoint = properties.getProperty("ENDPOINT");
				associateTag = properties.getProperty("ASSOCIATETAG");
			} else {
				System.out.println("File not found!");
			}
			
		} catch (IOException ex) {
			System.out.println("File not found!");
		} catch (NullPointerException e) {
			System.out.println("Cannot read properties");
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		SignedRequestsHelper helper = null;
		try {
			
			helper = SignedRequestsHelper.getInstance(endpoint, awsAccessKeyId, awsSecretKey);
		} catch (Exception e) {
			System.out.println("Faulty query!");;
		}
		
		String pageStr = Integer.toString(page);
		String requestUrl = null;
		Map<String, String> params = new HashMap<String, String>();
        params.put("Service", "AWSECommerceService");
        params.put("Version", "2009-03-31");
        params.put("Operation", "ItemSearch");
        params.put("Keywords", searchKey);
        params.put("ItemPage", pageStr);
        params.put("SearchIndex", "All");
        params.put("ResponseGroup", "Large");
        params.put("AssociateTag", associateTag);
        
        try {
        	requestUrl = helper.sign(params);
        } catch (NullPointerException e){
        	System.out.println("URL cannot be completed!");
        }
        
        
        AmazonUrlContent auc = new AmazonUrlContent();
        List<Amazon> titles = auc.fetchTitle(requestUrl);
		return titles;
	}
}
