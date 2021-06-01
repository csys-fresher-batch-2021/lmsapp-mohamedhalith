package in.mohamedhalith.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;
@Data
public class Employee implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private int id;
	private int employeeId;
	private long mobileNumber;
	private String email;
	private String username;
	private String password;
	private boolean status;
	private LocalDate joinedDate;
	private LocalDateTime modifiedTime;

}
