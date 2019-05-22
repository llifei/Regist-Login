package lf.user.domain;

public class User {
	private String username;
	private String password;
	private int age;
	private String gender;

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	private String verifyCode;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", verifyCode=" + verifyCode + "]";
	}
	public void setverifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
}
