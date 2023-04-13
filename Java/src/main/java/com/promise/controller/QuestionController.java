package com.promise.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.promise.controller.result.QuestionResult;
import com.promise.pojo.Question;
import com.promise.pojo.Travel;
import com.promise.service.QuestionServiceImpl;
import com.promise.service.TravelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionServiceImpl questionService;

    @Autowired
    private TravelServiceImpl travelService;

    /**
     * 分页查询所有用户的问题
     *
     * @return QuestionResult
     */
    @GetMapping("/{currentPage}/{pageSize}")
    @ResponseBody
    public QuestionResult getQuestionByPage(@PathVariable Integer currentPage,
                                            @PathVariable Integer pageSize) {
        Page<Question> iPage = new Page<>(currentPage, pageSize);
        IPage<Question> page = questionService.page(iPage, null);
        Integer code = page.getRecords() != null ? Code.SUCCESS_CODE : Code.ERROR_CODE;
        String msg = page.getRecords() != null ? "查询成功" : "查询失败";
        // 添加查询回复ID的功能
        if (page.getRecords() != null) {
            LambdaQueryWrapper<Travel> queryWrapper = new LambdaQueryWrapper<>();
            for (int i = 0; i < page.getRecords().size(); i++) {
                if (page.getRecords().get(i).getAnswered() == 0) continue;
                queryWrapper.eq(Travel::getQuestionID, page.getRecords().get(i).getId());
                page.getRecords().get(i).setAnswer(travelService.getOne(queryWrapper));
            }
        }
        Map<String, Object> data = new HashMap<>();
        data.put("pages", page.getPages());
        data.put("currentPage", page.getCurrent());
        data.put("pageSize", page.getSize());
        data.put("plannings", page.getRecords());
        data.put("total", page.getTotal());
        return new QuestionResult(code, data, msg);
    }

    /**
     * 添加提问
     *
     * @param question 问题对象
     * @return QuestionResult
     */
    @PostMapping
    @ResponseBody
    public QuestionResult save(@RequestBody Question question) {
        boolean flag = questionService.save(question);
        Integer code = flag ? Code.SUCCESS_CODE : Code.ERROR_CODE;
        String msg = flag ? "感谢您的提问，我们会尽快回复的" : "提交失败";
        return new QuestionResult(code, flag, msg);
    }

    /**
     * 查询某个用户id下的问题
     *
     * @param id          用户id
     * @param currentPage 当前页面
     * @param pageSize    页面数据个数
     * @return QuestionResult
     */
    @GetMapping("/{id}/{currentPage}/{pageSize}")
    @ResponseBody
    public QuestionResult getQuestionByPageAndId(@PathVariable Integer id,
                                                 @PathVariable Integer currentPage,
                                                 @PathVariable Integer pageSize) {
        LambdaQueryWrapper<Question> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Question::getUserID, id).orderByDesc(Question::getAnswered);

        Page<Question> iPage = new Page<>(currentPage, pageSize);
        IPage<Question> page = questionService.page(iPage, queryWrapper);
        Integer code = page.getRecords() != null ? Code.SUCCESS_CODE : Code.ERROR_CODE;
        String msg = page.getRecords() != null ? "查询成功" : "查询失败";
        // 添加查询回复ID的功能
        if (page.getRecords() != null) {
            LambdaQueryWrapper<Travel> queryWrapper1 = new LambdaQueryWrapper<>();
            for (int i = 0; i < page.getRecords().size(); i++) {
                if (page.getRecords().get(i).getAnswered() == 0) continue;
                queryWrapper1.eq(Travel::getQuestionID, page.getRecords().get(i).getId());
                page.getRecords().get(i).setAnswer(travelService.getOne(queryWrapper1));
            }
        }
        Map<String, Object> data = new HashMap<>();
        data.put("pages", page.getPages());
        data.put("currentPage", page.getCurrent());
        data.put("pageSize", page.getSize());
        data.put("plannings", page.getRecords());
        data.put("total", page.getTotal());
        return new QuestionResult(code, data, msg);
    }

    /**
     * 回复用户的提问
     *
     * @param id 问题的id
     * @return QuestionResult
     */
    @PutMapping("/{id}")
    @ResponseBody
    public QuestionResult answerQuestion(@PathVariable Integer id, @RequestBody Travel travel) {
        // 在tb_travel中添加数据
        boolean saveFlag = travelService.save(travel);
        // 更新answered数据
        boolean flag = questionService.updateAnswered(id, 1);
        Integer code = flag && saveFlag ? Code.SUCCESS_CODE : Code.ERROR_CODE;
        String msg = flag && saveFlag ? "回复成功" : "回复失败";
        return new QuestionResult(code, flag, msg);
    }
}
