package address.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import address.dto.AddressDto;
import address.service.AddressService;

// @RestController는 rest api 방식을 사용할 경우 @RestController를 사용, @ResponceBody 어노테이션의 기능을 가지고 있음. 
@RestController
public class AddressApiController {
	@Autowired
	private AddressService addrService;
	
	@RequestMapping(value="/api/address", method=RequestMethod.GET)
	public List<AddressDto> restOpenListAddress() throws Exception {
		return addrService.selectListAddress();
	}
	
	@RequestMapping(value="/api/address/{num}", method=RequestMethod.GET)
	public AddressDto restOpenDetailAddress(@PathVariable("num") int num) throws Exception {
		return addrService.selectDetailAddress(num);
	}
	
	@RequestMapping(value="/api/address/write", method=RequestMethod.POST)
	public void restInsertAddress(AddressDto addr) throws Exception {
		addrService.insertAddress(addr);
	}
	
	@RequestMapping(value="/api/address/update", method=RequestMethod.PUT)
	public String restUpdateAddress(@RequestBody AddressDto addr) throws Exception {
		addrService.updateAddress(addr);
		return "redirect:/rest/address";
	}
	
	@RequestMapping(value="/api/address/delete", method=RequestMethod.DELETE)
	public String restDeleteAddress(@RequestParam int num) throws Exception {
		addrService.deleteAddress(num);
		return "redirect:/rest/address";
	}
}
