package vo;

public class BoardVO {
	int seq;
	String title;
	String content;
	String writer;
	String pw;
	String indate;
	String sido;
	String gugun;
	String gender;
	String state;

	
	public BoardVO() {}
	
	public BoardVO(String title, String content, String writer, String pw, String sido,
			String gugun, String gender, String state) {
		super();
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.pw = pw;
		this.sido = sido;
		this.gugun = gugun;
		this.gender = gender;
		this.state = state;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getIndate() {
		return indate;
	}

	public void setIndate(String indate) {
		this.indate = indate;
	}

	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getGugun() {
		return gugun;
	}

	public void setGugun(String gugun) {
		this.gugun = gugun;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
