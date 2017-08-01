package com.danke.api.web;

import com.danke.api.domain.CorpUser;
import com.danke.api.domain.SuperResponse;
import com.danke.api.service.CorpUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wanghao on 2017/7/31.
 */
@Controller
@RequestMapping(value = "/credit/blacklist")
public class BusinessController {
    @Autowired
    private CorpUserService corpUserService;

    @RequestMapping(value = "/query",method= RequestMethod.GET)
    @ResponseBody
    public List<CorpUser> queryGet(@RequestParam("card_no") Long cardNo) {
        return corpUserService.getCorpUsers();
    }
@RequestMapping(value = "/query",method= RequestMethod.POST)
    @ResponseBody
    public List<CorpUser> queryPost(@RequestParam("card_no") Long cardNo) {
        return corpUserService.getCorpUsers();
    }

    @RequestMapping(value = "/query",method= RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<List<CorpUser>> queryPut(@RequestParam("card_no") Long cardNo) {
        ResponseEntity<List<CorpUser>> response = new ResponseEntity<List<CorpUser>>(corpUserService.getCorpUsers(),HttpStatus.CREATED);
        return response;
    }
}
