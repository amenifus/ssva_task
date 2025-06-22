package lt.ssva.task.pojo;

import java.util.ArrayList;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/*
 * ObjectMapper om = new ObjectMapper(); Root root = om.readValue(myJsonString,
 * Root.class);
 */

public class GetMapInfoResponse {
	public double currentVersion;
	public String serviceDescription;
	public String mapName;
	public String description;
	public String copyrightText;
	public boolean supportsDynamicLayers;
	public ArrayList<Layer> layers;
	public ArrayList<Object> tables;
	public SpatialReference spatialReference;
	public boolean singleFusedMapCache;
	public TileInfo tileInfo;
	public InitialExtent initialExtent;
	public FullExtent fullExtent;
	public int minScale;
	public int maxScale;
	public String units;
	public String supportedImageFormatTypes;
	public DocumentInfo documentInfo;
	public String capabilities;
	public String supportedQueryFormats;
	public boolean exportTilesAllowed;
	public int maxRecordCount;
	public int maxImageHeight;
	public int maxImageWidth;
	public String supportedExtensions;
}