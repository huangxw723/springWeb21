/**
 * 
 */
package sprittr.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sprittr.dao.AsUserMapper;
import sprittr.pojo.AsUser;





/**   
 * @Title:
 * @Package 
 * @Description: 
 * @author huangxw
 * @date 2017年04月10日
 * @version V1.0   
 */
@Controller
@RequestMapping(value="/")
public class HahaTestController{
   
	
	@Autowired
	private AsUserMapper asUserMapper;
	@RequestMapping(value="/asy{name}", method=RequestMethod.GET)
	@ResponseBody
	public Object asy(@PathVariable String name){
		System.out.println(name);
		AsUser obj  = asUserMapper.findBySysUserIdd("1");
		return (Object)obj;
	}
	
	@RequestMapping(value="/asycount", method=RequestMethod.GET)
	public String asycount(int num){
		
		return "home";
	}

	@RequestMapping(value="/springTags", method=RequestMethod.GET)
	@ResponseBody
	public String springTags(){
		
		
		return "sdfdfd";
	}

}
