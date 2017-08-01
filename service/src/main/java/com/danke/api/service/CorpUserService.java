package com.danke.api.service;

import com.danke.api.dao.CorpUserDao;
import com.danke.api.domain.CorpUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wanghao on 2017/7/31.
 */
@Service
public class  CorpUserService {
    @Resource
    private CorpUserDao corpUserDao;
    public List<CorpUser> getCorpUsers(){
        return corpUserDao.getCorpUsers();
    }
}
