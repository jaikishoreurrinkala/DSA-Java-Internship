package ticketbookingsystem;

public class User {
	 private String email;
	 private User next;
	public User(String email) {
		super();
		this.email = email;
		this.next=null;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setNext(User next)
	{
		this.next=next;
	}
	public User getNext()
	{
		return next;
	}
	 
}
 