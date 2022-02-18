package gameEntity;

// This entity contains getters and setters with fields to match
public class VideoGame {
	
	// data fields corresponding to rows in database
	private int videoGameId;
	private String name;
	private String type;
	
	// constructor
	public VideoGame(int videoGameId, String name, String type) {
		this.videoGameId = videoGameId;
		this.name = name;
		this.type = type;
	}
	
	// all getters and setters (created automatically using eclipse)
	public int getVideoGameId() {
		return videoGameId;
	}

	public void setVideoGameId(int videoGameId) {
		this.videoGameId = videoGameId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
