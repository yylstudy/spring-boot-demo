package com.yyl;//package com.yyl;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
//import org.springframework.context.ConfigurableApplicationContext;
//
//import java.lang.reflect.Field;
//
///**
// * @Author yang.yonglian
// * @ClassName: com.yyl
// * @Description: TODO(这里描述)
// * @Date 2019/6/11 0011
// */
//public class MySpringApplication extends SpringApplication {
//    public MySpringApplication(Object... sources){
//        super(sources);
//    }
//
//    @Override
//    protected ConfigurableApplicationContext createApplicationContext() {
//        try{
//            AnnotationConfigEmbeddedWebApplicationContext context = new MyAnnotationConfigEmbeddedWebApplicationContext();
//            return context;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
