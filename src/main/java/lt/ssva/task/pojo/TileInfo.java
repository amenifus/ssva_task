package lt.ssva.task.pojo;

import java.util.ArrayList;

public class TileInfo {
	public int rows;
	public int cols;
	public int dpi;
	public String format;
	public int compressionQuality;
	public TileInfo origin;
	public TileInfo spatialReference;
	public ArrayList<Lod> lods;
}