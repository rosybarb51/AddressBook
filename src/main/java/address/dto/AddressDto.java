package address.dto;

import lombok.Data;

// 럼복을 통해서 데이터 클래스를 사용함
@Data
public class AddressDto {
	private int num;
	private String name;
	private String phone;
	private String tel;
	private String email;
	private String addr;
	private String delYn;
	private String passwd;
}
