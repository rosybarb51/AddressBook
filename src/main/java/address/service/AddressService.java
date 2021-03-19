package address.service;

import java.util.List;

import address.dto.AddressDto;

public interface AddressService {
//	맵퍼에 있는 것 그대로 복사해서 붙여넣기 하면됨..
	
//	전체목록
	List<AddressDto> selectListAddress() throws Exception;
	
//	상세내용
	AddressDto selectDetailAddress(int num) throws Exception;
	
//	입력은 반환값 없음
//	인설트하려면 데이터타입 통째로 필요해서 AddressDto addr
	void insertAddress(AddressDto addr) throws Exception;
	
//	수정
	void updateAddress(AddressDto addr) throws Exception;
	
//	삭제
	void deleteAddress(int num) throws Exception;


}
