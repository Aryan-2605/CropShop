package aryan.SupportRequest.exceptions;

public class exception {
	//First Name
	public static class FirstNameTooLong extends RuntimeException{
		public FirstNameTooLong(String message) {
			super(message);
		}
	}
	
	//Last Name
	public static class LastNameTooLong extends RuntimeException{
		public LastNameTooLong(String message) {
			super(message);
		}
	}
	//Email 
	public static class EmailTooLong extends RuntimeException{
		public EmailTooLong(String message) {
			super(message);
		}
	}
	//Title
	public static class TitleTooLong extends RuntimeException{
		public TitleTooLong(String message) {
			super(message);
		}
	}
	//Query
	public static class QueryTooLong extends RuntimeException{
		public QueryTooLong(String message) {
			super(message);
		}
	}
}
