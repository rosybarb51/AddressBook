package address.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import address.dto.AddressDto;

@Mapper
public interface AddressMapper {
//	전체리스트, 상세 내용, 입력, 수정, 삭제
	
//	List 타입으로 리턴하고, 데이터베이스와 연동돼 있는 AddressDto를 가져옴
//	select 구문이라서 selectListAddress() 사용
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
