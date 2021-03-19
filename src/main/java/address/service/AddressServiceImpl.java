package address.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import address.dto.AddressDto;
import address.mapper.AddressMapper;

@Service
public class AddressServiceImpl implements AddressService {
//	일반 메서드는 몸통이 있고 추상메서드느 몸통부분이 없다
//	클래스라서 메소드를 외부에서 가져다가 써야함, 그래서 앞에 public을 넣어줘야한다 이걸 안 넣으며 default 로 가져간다. 
	
	@Autowired
//	여기서만 사용할 것이라서 private 해줌
	private AddressMapper addrMapper;
	
	@Override
	public List<AddressDto> selectListAddress() throws Exception {
		return addrMapper.selectListAddress();
	}
	
//	상세내용
	@Override
	public AddressDto selectDetailAddress(int num) throws Exception {
		return addrMapper.selectDetailAddress(num);
	}
	
//	입력은 반환값 없음
//	인설트하려면 데이터타입 통째로 필요해서 AddressDto addr
	@Override
	public void insertAddress(AddressDto addr) throws Exception {
		addrMapper.insertAddress(addr);
	}
	
//	수정
	@Override
	public void updateAddress(AddressDto addr) throws Exception {
		addrMapper.updateAddress(addr);
	}
	
//	삭제
	@Override
	public void deleteAddress(int num) throws Exception {
		addrMapper.deleteAddress(num);
	}


	
}
