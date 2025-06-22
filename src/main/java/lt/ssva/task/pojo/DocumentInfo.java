package lt.ssva.task.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DocumentInfo {
	@JsonProperty("Title")
	public String title;
	@JsonProperty("Author")
	public String author;
	@JsonProperty("Comments")
	public String comments;
	@JsonProperty("Subject")
	public String subject;
	@JsonProperty("Category")
	public String category;
	@JsonProperty("AntialiasingMode")
	public String antialiasingMode;
	@JsonProperty("TextAntialiasingMode")
	public String textAntialiasingMode;
	@JsonProperty("Keywords")
	public String keywords;
}