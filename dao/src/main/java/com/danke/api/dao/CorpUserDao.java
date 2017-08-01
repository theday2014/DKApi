package com.danke.api.dao;

import com.danke.api.domain.CorpUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wanghao on 2017/7/31.
 */
@SuppressWarnings("UnnecessaryInterfaceModifier")
@Repository
public interface CorpUserDao {
    List<CorpUser> getCorpUsers();
}
