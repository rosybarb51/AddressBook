package address.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import address.dto.AddressDto;
import address.service.AddressService;

@Controller
public class AddressController {
	@Autowired
	private AddressService addrService;
	
//	전체 목록 조회 페이지, 상세 내용 페이지, 사용자 입력 페이지 열기, 실제 입력 페이지, 수정 페이지 열기, 실제 수정 페이지, 삭제 페이지 열기, 실제 삭제 페이지
//	위의 내용대로 8개의 메서드가 필요함
	
	@RequestMapping("/addr/openListAddress.do")
	public ModelAndView openListAddress() throws Exception {
		ModelAndView mv = new ModelAndView("/addr/listAddr");
		
		List<AddressDto> addrList = addrService.selectListAddress();
		mv.addObject("addrList", addrList);
		return mv;
	}
		
	@RequestMapping("/addr/openDetailAddress.do")
//	num은 @requestParma 부분이 들어가야 인식이 된다. 
	public ModelAndView openDetailAddress(@RequestParam int num) throws Exception {
		ModelAndView mv = new ModelAndView("addr/detailAddr");
		
		AddressDto addr = addrService.selectDetailAddress(num);
		mv.addObject("addr", addr);
		
		return mv;
	}
	
//	사용자 입력 양식 받는 부분
	@RequestMapping("/addr/writeAddress.do")
	public String writeAddress() throws Exception {
		return "/addr/writeAddr";
	}
	
	@RequestMapping("/addr/insertAddress.do")
//	전체 다 가져와야함
	public String ginsertAddress(AddressDto addr) throws Exception {
		addrService.insertAddress(addr);
		return "redirect:/addr/openListAddress.do";
	}
	
	@RequestMapping("/addr/editAddress.do")
	public ModelAndView edtiAddress(@RequestParam int num) throws Exception {
		ModelAndView mv = new ModelAndView("/addr/editAddr");
		
		AddressDto addr = addrService.selectDetailAddress(num);
		mv.addObject("addr", addr);
		
		return mv;
	}
	
	@RequestMapping("/addr/updateAddress.do")
	public String updateAddress(AddressDto addr) throws Exception {
		addrService.updateAddress(addr);
		return "redirect:/addr/openListAddress.do";
		
	}
	
	@RequestMapping("/addr/removeAddress.do")
	public ModelAndView removeAddress(@RequestParam int num) throws Exception {
		ModelAndView mv = new ModelAndView("/addr/removeAddr");
		
		AddressDto addr = addrService.selectDetailAddress(num);
		mv.addObject("addr", addr);
		
		return mv;
	}
	
	@RequestMapping("/addr/deleteAddress.do")
	public String deleteAddress(@RequestParam int num) throws Exception {
		addrService.deleteAddress(num);
		return "redirect:/addr/openListAddress.do";
		
	}
	
	
	
	
//	rest 방식
	@RequestMapping(value="/rest/address", method=RequestMethod.GET)
	public ModelAndView restOpenListAddress() throws Exception {
		ModelAndView mv = new ModelAndView("/rest/restListAddr");
		
		List<AddressDto> addrList = addrService.selectListAddress();
		mv.addObject("addrList", addrList);
		
		return mv;
	}
	
//	위랑 주소는 동일한데, /{num}이 붙었음. 
	@RequestMapping(value="/rest/address/{num}", method=RequestMethod.GET)
//	매개변수를 이용해서 @PathVaribale 사용하면 우리가 주소창에 입력한 것을 그대로 받아서 사용할 수 있게 함.
	public ModelAndView restOpenDetailAddress(@PathVariable("num") int num) throws Exception {
		ModelAndView mv = new ModelAndView("/rest/restDetailAddr");
		
		AddressDto addr = addrService.selectDetailAddress(num);
		mv.addObject("addr", addr);
		
		return mv;
	}
	
//	작성하기
	@RequestMapping(value="/rest/address/write", method=RequestMethod.GET)
	public String restWriteAddress() throws Exception {
		return "/rest/restWriteAddr";
	}
	
	@RequestMapping(value="/rest/address/write", method=RequestMethod.POST)
	public String restInsertAddress(AddressDto addr) throws Exception {
		addrService.insertAddress(addr);
		
		return "redirect:/rest/address";
	}
	
//	update 부분은 화면 출력하는 부분이 있어서 조금 추가 돼있다.
	@RequestMapping(value="/rest/address/update/{num}", method=RequestMethod.GET)
	public ModelAndView restEditAddress(@PathVariable("num") int num) throws Exception {
		ModelAndView mv = new ModelAndView("/addr/editAddr");
		
		AddressDto addr = addrService.selectDetailAddress(num);
		mv.addObject("addr", addr);
		
		return mv;
	}
	
	@RequestMapping(value="/rest/address/update", method=RequestMethod.PUT)
	public String restUpdateAddress(AddressDto addr) throws Exception {
		addrService.updateAddress(addr);
		return "redirect:/rest/address";
	}
	
	@RequestMapping(value="/rest/address/delete/{num}", method=RequestMethod.GET)
	public ModelAndView restRemoveAddress(@PathVariable("num") int num) throws Exception {
ModelAndView mv = new ModelAndView("/addr/removeAddr");
		
		AddressDto addr = addrService.selectDetailAddress(num);
		mv.addObject("addr", addr);
		
		return mv;
	}
	
//	삭제하기
	@RequestMapping(value="/rest/address/delete", method=RequestMethod.DELETE)
	public String restDeleteAddress(@RequestParam int num) throws Exception {
//		서비스 이용해서 deleteAddress 실행
		addrService.deleteAddress(num);
		return "redirect:/rest/address";
	}
}



















