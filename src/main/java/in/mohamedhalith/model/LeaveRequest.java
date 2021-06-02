package in.mohamedhalith.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;
@Data
public class LeaveRequest {

	private int leaveId;
	private Employee employee;
	private LocalDate fromDate;
	private LocalDate toDate;
	private int duration;
	private String type;
	private String reason;
	private String status = "Waiting for approval"; 
	private LocalDateTime cancelledTime;
	private LocalDateTime reviewedTime;
	private LocalDateTime appliedTime;
}
