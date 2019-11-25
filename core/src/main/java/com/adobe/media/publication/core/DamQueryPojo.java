package com.adobe.media.publication.core;
 
import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.jcr.Session;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
  
public class DamQueryPojo extends WCMUsePojo {
  
    private List<AssetBean> assetList = null;  
     
    public List<AssetBean> getAssetList() {
        return this.assetList;
    }
       
    private QueryBuilder builder;
    private Session session;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());  
    
    @Override
    public void activate() throws Exception {        
           
        final ResourceResolver resolver = getResource().getResourceResolver();
		
		String assetFolderPath = get("assetFolderPath", String.class);
        if(assetFolderPath == null) {
        	assetFolderPath = "/content/dam/media-publication";
        }
            
        //Invoke the adaptTo method to create a Session used to create a QueryManager
        session = resolver.adaptTo(Session.class);
                                   
        // create query description as hash map (simplest way, same as form post)
        Map<String, String> map = new HashMap<String, String>();              
        map.put("path", assetFolderPath);
        map.put("type", "dam:Asset");
        map.put("p.offset", "0"); // same as query.setStart(0) below
        map.put("p.limit", "-1"); //  allows to display all query results
        
        builder = getSlingScriptHelper().getService(QueryBuilder.class); 
        Query query = builder.createQuery(PredicateGroup.create(map), session);
        query.setStart(0);
                    
        SearchResult result = query.getResult();
        assetList = new ArrayList<AssetBean>();
        
        // iterating over the results
        for (Hit hit : result.getHits()) {
               String path = hit.getPath();
               AssetBean asbean = new AssetBean();
               asbean.setUrl(path);
               logger.info("Path is: "+path);
			   
			   Resource assetResource = resolver.getResource(path +"/jcr:content/metadata"); 
               if (assetResource != null) { 
                   ValueMap valueMap = assetResource.getValueMap(); 
                   asbean.setEnable(valueMap.get("enable", String.class));
               }
			   
               String[] fileNameArr = path.split("/");
               String filename = fileNameArr[fileNameArr.length-1];
               asbean.setFilename(filename);
               
               //setting filetype
               if(filename.contains("pdf")){
            	   asbean.setType("pdf");
               } else if(filename.contains("mp4") || filename.contains("mpeg") ){
            	   asbean.setType("video");
               } else {
            	   asbean.setType("image");
               }
               
			   //populating asset list
               assetList.add(asbean);
        } 
    }      
}