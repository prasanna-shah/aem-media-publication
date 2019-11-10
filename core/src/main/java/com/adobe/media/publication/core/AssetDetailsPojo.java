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
  
public class AssetDetailsPojo extends WCMUsePojo {
  
    private AssetBean assetBean = null;  
     
    public AssetBean getAssetBean() {
        return this.assetBean;
    }
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    public void activate() throws Exception {        
           
        final ResourceResolver resolver = getResource().getResourceResolver();
        String queryparams = getRequest().getQueryString(); 
        
		//getting asset path
        String[] pathArr = queryparams.split("=");
        String assetpath = pathArr[1].replace("&wcmmode", "");
        
        Resource resource = resolver.getResource(assetpath +"/jcr:content/metadata"); 

		//getting metadata asset values from each asset
        if (resource != null) { 
            ValueMap valueMap = resource.getValueMap(); 
            assetBean = new AssetBean();
            if (valueMap.containsKey("assettitle")) {
            	assetBean.setTitle( valueMap.get("assettitle", String.class));
            }
            if (valueMap.containsKey("ownername")) {
            	assetBean.setOwnername( valueMap.get("ownername", String.class));
            }
            if (valueMap.containsKey("owneremail")) {
            	assetBean.setOwneremail( valueMap.get("owneremail", String.class));
            }
            if (valueMap.containsKey("asseturl")) {
            	assetBean.setType( valueMap.get("assettype", String.class));
            }
            if (valueMap.containsKey("assettype")) {
            	assetBean.setUrl( valueMap.get("asseturl", String.class));
            }
            String[] fileNameArr = assetpath.split("/");
            assetBean.setFilename(fileNameArr[fileNameArr.length-1]);
        }                           
    }      
}