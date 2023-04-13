package com.promise.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.promise.dao.QuestionDao;
import com.promise.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionDao, Question> {
    @Autowired
    private QuestionDao questionDao;

    public boolean updateAnswered(Integer id, Integer answered) {
        return questionDao.updateAnswered(id, answered) > 0;
    }
}
