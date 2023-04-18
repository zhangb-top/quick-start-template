package com.promise.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.promise.dao.TravelDao;
import com.promise.pojo.Travel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelServiceImpl extends ServiceImpl<TravelDao, Travel> {
}
