package exceptionhandler;

public class Error {
	private String title;
	private int status;
	private long timeStamp;
	private ErrorMessage errorMessage;

	public Error(String title, int status, long timeStamp, ErrorMessage errorMessage) {
		super();
		this.title = title;
		this.status = status;
		this.timeStamp = timeStamp;
		this.errorMessage = errorMessage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

}
