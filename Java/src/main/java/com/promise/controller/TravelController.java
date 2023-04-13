package com.promise.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.promise.controller.result.TravelResult;
import com.promise.pojo.Travel;
import com.promise.service.QuestionServiceImpl;
import com.promise.service.TravelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/travels")
public class TravelController {
    @Autowired
    private TravelServiceImpl travelService;

    @Autowired
    private QuestionServiceImpl questionService;

    /**
     * 分页查询旅游策略
     *
     * @param currentPage 当前页码
     * @param pageSize    页面数据条数
     * @return TravelResult
     */
    @GetMapping("/{currentPage}/{pageSize}")
    @ResponseBody
    public TravelResult getUserByPage(@PathVariable Integer currentPage,
                                      @PathVariable Integer pageSize) {
        IPage<Travel> iPage = new Page<>(currentPage, pageSize);
        IPage<Travel> page = travelService.page(iPage, null);
        Integer code = page.getRecords() != null ? Code.SUCCESS_CODE : Code.ERROR_CODE;
        String msg = page.getRecords() != null ? "查询成功" : "查询失败";
        Map<String, Object> data = new HashMap<>();
        data.put("pages", page.getPages());
        data.put("currentPage", page.getCurrent());
        data.put("pageSize", page.getSize());
        data.put("plannings", page.getRecords());
        data.put("total", page.getTotal());
        return new TravelResult(code, data, msg);
    }

    /**
     * 添加旅游策略
     *
     * @param travel 旅游策略
     * @return TravelResult
     */
    @PostMapping
    @ResponseBody
    public TravelResult save(@RequestBody Travel travel) {
        boolean flag = travelService.save(travel);
        Integer code = flag ? Code.SUCCESS_CODE : Code.ERROR_CODE;
        String msg = flag ? "添加成功" : "添加失败";
        return new TravelResult(code, flag, msg);
    }

    /**
     * 修改旅游策略
     *
     * @param travel 旅游策略
     * @return TravelResult
     */
    @PutMapping
    @ResponseBody
    public TravelResult update(@RequestBody Travel travel) {
        boolean flag = travelService.updateById(travel);
        Integer code = flag ? Code.SUCCESS_CODE : Code.ERROR_CODE;
        String msg = flag ? "修改成功" : "修改失败";
        return new TravelResult(code, flag, msg);
    }

    /**
     * 根据id查询旅游策略
     *
     * @param id 旅游策略的id
     * @return TravelResult
     */
    @GetMapping("/{id}")
    @ResponseBody
    public TravelResult getTravelById(@PathVariable Integer id) {
        Travel travel = travelService.getTravelById(id);
        Integer code = travel != null ? Code.SUCCESS_CODE : Code.ERROR_CODE;
        String msg = travel != null ? "查询成功" : "id不存在或查询失败";
        return new TravelResult(code, travel, msg);
    }

    /**
     * 根据id逻辑删除旅游策略
     *
     * @param id 旅游策略的id
     * @return TravelResult
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public TravelResult delete(@PathVariable Integer id) {
        Travel travel = travelService.getById(id);
        boolean flag = travelService.removeById(id);
        // 修改tb_question中的answered数据
        boolean questionFlag = true;
        if (travel != null && travel.getQuestionID() != null) {
            questionFlag = questionService.updateAnswered(travel.getQuestionID(), 0);
        }
        Integer code = flag && questionFlag ? Code.SUCCESS_CODE : Code.ERROR_CODE;
        String msg = flag && questionFlag ? "删除成功" : "删除失败";
        return new TravelResult(code, flag, msg);
    }

    /**
     * 查询逻辑删除的旅游策略
     *
     * @return TravelResult
     */
    @GetMapping("/deleted")
    @ResponseBody
    public TravelResult getDeletedUsers() {
        List<Travel> deletedTravels = travelService.getDeleted();
        Integer code = deletedTravels != null ? Code.SUCCESS_CODE : Code.ERROR_CODE;
        String msg = deletedTravels != null ? "查询成功" : "查询失败";
        return new TravelResult(code, deletedTravels, msg);
    }
}
