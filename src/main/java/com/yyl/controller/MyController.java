package com.yyl.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.yyl.dao.UserDao;
import com.yyl.dao.UserDao2;
import com.yyl.domain.MyGroovyUser;
import com.yyl.domain.MyUser;
import com.yyl.domain.User;
import com.yyl.rabbitmq.MyMsg;
import com.yyl.rabbitmq.MyProducer;
import com.yyl.service.CloudService;
import com.yyl.service.Yyl;
import com.yyl2.MyAutoConfiguration;
import com.yyl2.groovy.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.Signal;
import sun.misc.SignalHandler;

import javax.annotation.PostConstruct;
import javax.ws.rs.POST;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author yang.yonglian
 * @ClassName: com.yyl
 * @Description: TODO(这里描述)
 * @Date 2019/5/31 0031
 */
@Controller
public class MyController {
    @Autowired
    @Qualifier("jdbcTemplate2")
    private JdbcTemplate jdbcTemplate;
    @Value("${age:}")
    private String age;
    @Autowired
    private MyUser myUser;
    @Autowired
    @Qualifier("dataSource")
    private DruidDataSource dataSource;
    @Autowired
    private CloudService cloudService;
    @Autowired
    private MyAutoConfiguration myAutoConfiguration;
    @Autowired
    private Yyl yyl;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserDao2 userDao2;
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private Mapper mapper;
    @Autowired
    private MyProducer myProducer;

    @PostConstruct
    public void test3(){
        System.out.println(myUser);
        System.out.println(dataSource.getDriverClassName()+"----"+dataSource.getPassword()+"------------------");
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select username,password from test1");
        List<Map<String, Object>> list1 = jdbcTemplate.queryForList("select username,password from test1");
        cloudService.test1();
        System.out.println(yyl);
        //mybatis的一级缓存是建立在transaction的基础上的，查看源码可知如果存在事务，
        //在创建dao的时候，每个dao中的SqlSessionTemplate是不同的，但是在同一事务中
        // 所有SqlSessionTemplate中的DefaultSqlSession都是使用的同一个，也就是从spring
        //事务中的TransactionSynchronizationManager.getResource获取，所以事务中的查询可以使用缓存
        //但是中间出现修改操作，无论这个操作和查询的方法是不是同一个dao，那么下一次同样的查询将不从缓存中获取
        //至于不存在事务状态下，SqlSessionTemplate中的DefaultSqlSession每次都是获取同一个，所以无法使用
        //mybatis的一级缓存
        transactionTemplate.execute((ss)->{
            List<User> list3 = userDao.findList();
            //可以看到 即使userDao和userDao2属于不同的SqlSessionTemplate，但是其中的DefaultSqlSession
            //是同一个，所以后面的查询同样会查数据库
            userDao2.update();
            List<User> list4 = userDao.findList();
            return 1;
        });
    }

    /**
     * groovy文件 JavaBean测试
     */
    @RequestMapping("/test3")
    @ResponseBody
    public String test1() throws Exception{
        System.out.println(myUser);
        Thread.sleep(5000);
        MyGroovyUser myGroovyUser = mapper.map(myUser, MyGroovyUser.class);
        System.out.println(myGroovyUser);
        return "hahahh";
    }

    @RequestMapping("/sendMsg")
    @ResponseBody
    public String test2(){
        MyMsg myMsg = new MyMsg();
        myMsg.setName("yyl1");
        myMsg.setId(UUID.randomUUID().toString());
        myProducer.sendMsg(myMsg);
        return "success";
    }

    @ExceptionHandler
    public void exceptionResolve(){

    }

}
