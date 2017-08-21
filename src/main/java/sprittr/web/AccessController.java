package sprittr.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sprittr.exception.StatusSuccess;
import sprittr.pojo.AsUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Controller
public class AccessController {
    private static Logger logger =LoggerFactory.getLogger(sprittController.class);

    @RequestMapping(value="/toa",method = RequestMethod.GET)
    @ResponseBody
    public Object asyeee(String ces) throws StatusSuccess{
        logger.debug("************************日志记录开始***************" + ces);
        ;
        throw new StatusSuccess("你是");


    }
}
