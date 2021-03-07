package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
	private int id;
	private CatUser catUser;
	private Boolean addquyen;
	private Boolean editquyen;
	private Boolean delquyen;
}
