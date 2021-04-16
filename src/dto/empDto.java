package dto;

public class empDto {
	
	private int seq; 
	private String name;
	private String phonenum; 
	private String email;
	private String sal;
	
	
	public empDto() {
	}


	public empDto(int seq, String name, String phonenum, String email, String sal) {
		super();
		this.seq = seq;
		this.name = name;
		this.phonenum = phonenum;
		this.email = email;
		this.sal = sal;
	}


	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhonenum() {
		return phonenum;
	}


	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSal() {
		return sal;
	}


	public void setSal(String sal) {
		this.sal = sal;
	}


	@Override
	public String toString() {
		return "사원정보 [사원번호=" + seq + ", 이름=" + name + ", 연락처=" + phonenum + ", 이메일=" + email + ", 급여=" + sal
				+ "]";
	}
	
	

}
