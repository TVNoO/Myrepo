package tvn.com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "serverams")
public class Server {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "serverName")
	private String serverName;
	
	@Column(name = "serverPlatform")
	private String serverPlatform;
	
	@Column(name = "serverSSH")
	private String serverSSH;
	
	@Column(name = "rootPassword")
	private String rootPassword;
	
	@Column(name = "startDate")
	private String startDate;
	
	@Column(name = "endDate")
	private String endDate;
	
	@Column(name = "AMSversion")
	private String AMSversion;
	
	@Column(name = "note")
	private String note;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServerPlatform() {
		return serverPlatform;
	}

	public void setServerPlatform(String serverPlatform) {
		this.serverPlatform = serverPlatform;
	}

	public String getServerSSH() {
		return serverSSH;
	}

	public void setServerSSH(String serverSSH) {
		this.serverSSH = serverSSH;
	}

	public String getRootPassword() {
		return rootPassword;
	}

	public void setRootPassword(String rootPassword) {
		this.rootPassword = rootPassword;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getAMSversion() {
		return AMSversion;
	}

	public void setAMSversion(String aMSversion) {
		AMSversion = aMSversion;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
