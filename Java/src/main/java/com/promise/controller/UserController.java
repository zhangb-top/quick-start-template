package com.promise.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.promise.controller.result.UserResult;
import com.promise.pojo.User;
import com.promise.service.UserServiceImpl;
import com.promise.util.CodeUtil;
import com.promise.util.PasswordUtils;
import com.promise.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    /**
     * 分页查询用户
     *
     * @param currentPage 当前页码
     * @param pageSize    页面数据条数
     * @return UserResult
     */
    @PostMapping("/{currentPage}/{pageSize}")
    @ResponseBody
    public UserResult getUserByPage(@PathVariable Integer currentPage, @PathVariable Integer pageSize,
                                    @RequestBody User searchData) {
        // 查询条件
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!StringUtils.isEmpty(searchData.getUsername()),
                        User::getUsername, searchData.getUsername())
                .eq(!StringUtils.isEmpty(searchData.getIdentity()), User::getIdentity,
                        searchData.getIdentity());

        IPage<User> iPage = new Page<>(currentPage, pageSize);
        IPage<User> page = userService.page(iPage, queryWrapper);
        Integer code = page.getRecords() != null ? Code.SUCCESS_CODE : Code.ERROR_CODE;
        String msg = page.getRecords() != null ? "查询成功" : "查询失败";
        Map<String, Object> data = new HashMap<>();
        data.put("pages", page.getPages());
        data.put("currentPage", page.getCurrent());
        data.put("pageSize", page.getSize());
        data.put("users", page.getRecords());
        data.put("total", page.getTotal());
        return new UserResult(code, data, msg);
    }

    /**
     * 用户登录
     *
     * @param user 用户信息
     * @return UserResult
     */
    @PostMapping("/login")
    @ResponseBody
    public UserResult login(@RequestBody User user) throws NoSuchAlgorithmException {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername()).eq(User::getIdentity,
                user.getIdentity());
        List<User> users = userService.list(queryWrapper);

        Map<String, Object> data = new HashMap<>();
        if (users != null && users.size() == 1) {
            // 验证密码是否正确
            boolean flag = PasswordUtils.verifyPassword(user.getPassword(), users.get(0).getPassword());
            Integer code = flag ? Code.SUCCESS_CODE
                    : Code.ERROR_CODE;
            String msg = flag ? "登录成功" : "用户名或密码或身份错误";
            String token = flag ? TokenUtils.token(user.getUsername(),
                    user.getPassword()) : null;
            data.put("token", token);
            data.put("userID", users.get(0).getId());
            data.put("username", users.get(0).getUsername());
            String identity = users.get(0).getIdentity() == 1 ? "管理员" : "用户";
            data.put("identity", identity);
            return new UserResult(code, data, msg);
        }
        return new UserResult(Code.ERROR_CODE, data, "用户名或密码或身份错误");
    }

    /**
     * 用户注册
     *
     * @param user 用户数据
     * @return UserResult
     */
    @PostMapping("/register")
    @ResponseBody
    public UserResult save(HttpServletRequest request, @RequestBody User user) {
        // 验证码错误
        if (!request.getServletContext().getAttribute("code").equals(user.getCode().toUpperCase()))
            return new UserResult(Code.ERROR_CODE, false, "验证码错误");
        // 判断用户名是否重复
        List<User> list = userService.getByUsername(user.getUsername());
        boolean flag = list.size() == 0;
        Integer code = flag ? Code.SUCCESS_CODE : Code.ERROR_CODE;
        String msg = flag ? "注册成功" : "用户名重复";
        if (!flag) return new UserResult(code, false, msg);

        flag = userService.save(user);
        code = flag ? Code.SUCCESS_CODE : Code.ERROR_CODE;
        msg = flag ? "注册成功" : "注册失败";
        return new UserResult(code, flag, msg);
    }

    /**
     * 根据id删除用户
     *
     * @param id 用户的id
     * @return UserResult
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public UserResult delete(@PathVariable Integer id) {
        boolean flag = userService.removeById(id);
        Integer code = flag ? Code.SUCCESS_CODE : Code.ERROR_CODE;
        String msg = flag ? "删除成功" : "删除失败";
        return new UserResult(code, flag, msg);
    }

    /**
     * 查询逻辑删除的用户
     *
     * @return UserResult
     */
    @GetMapping("/deleted")
    @ResponseBody
    public UserResult getDeletedUsers() {
        List<User> deletedUsers = userService.getDeleted();
        Integer code = deletedUsers != null ? Code.SUCCESS_CODE : Code.ERROR_CODE;
        String msg = deletedUsers != null ? "查询成功" : "查询失败";
        return new UserResult(code, deletedUsers, msg);
    }

    /**
     * 获取验证码
     *
     * @param response 响应对象
     * @return 验证码
     * @throws IOException 异常
     */
    @GetMapping("/code")
    public String getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletOutputStream os = response.getOutputStream();
        String code = CodeUtil.outputVerifyImage(100, 50, os, 4);
        request.getServletContext().setAttribute("code", code);
        return code;
    }
}
