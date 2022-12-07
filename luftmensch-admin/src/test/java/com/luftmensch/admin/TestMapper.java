package com.luftmensch.admin;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luftmensch.framework.pojo.User;
import com.luftmensch.framework.mapper.UserMapper;
import com.luftmensch.framework.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestMapper {

    @Test
    public void test01() {
       /* TestUtils<UserMapper, User> test = new TestUtils<>();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> test1 = test.test(UserMapper.class, User.class, Wrapper.class, "selectList", queryWrapper);*/
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> selectList = TestUtils.test(UserMapper.class, User.class, Wrapper.class, "selectList", queryWrapper);
        assert selectList != null;
        selectList.forEach(System.out::println);
    }
}
