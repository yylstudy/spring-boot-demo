package com.yyl.configuration;//package com.yyl;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.FactoryBean;
//import org.springframework.beans.factory.SmartFactoryBean;
//import org.springframework.beans.factory.SmartInitializingSingleton;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.support.DefaultListableBeanFactory;
//import org.springframework.beans.factory.support.RootBeanDefinition;
//import org.springframework.context.weaving.LoadTimeWeaverAware;
//import org.springframework.core.convert.ConversionService;
//import org.springframework.util.StringValueResolver;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.security.AccessController;
//import java.security.PrivilegedAction;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @Author yang.yonglian
// * @ClassName: com.yyl
// * @Description: TODO(这里描述)
// * @Date 2019/6/11 0011
// */
//public class MyAnnotationConfigEmbeddedWebApplicationContext extends AnnotationConfigEmbeddedWebApplicationContext {
//    @Override
//    protected void finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory) {
//        // Initialize conversion service for this context.
//        if (beanFactory.containsBean(CONVERSION_SERVICE_BEAN_NAME) &&
//                beanFactory.isTypeMatch(CONVERSION_SERVICE_BEAN_NAME, ConversionService.class)) {
//            beanFactory.setConversionService(
//                    beanFactory.getBean(CONVERSION_SERVICE_BEAN_NAME, ConversionService.class));
//        }
//
//        // Register a default embedded value resolver if no bean post-processor
//        // (such as a PropertyPlaceholderConfigurer bean) registered any before:
//        // at this point, primarily for resolution in annotation attribute values.
//        if (!beanFactory.hasEmbeddedValueResolver()) {
//            beanFactory.addEmbeddedValueResolver(new StringValueResolver() {
//                @Override
//                public String resolveStringValue(String strVal) {
//                    return getEnvironment().resolvePlaceholders(strVal);
//                }
//            });
//        }
//
//        // Initialize LoadTimeWeaverAware beans early to allow for registering their transformers early.
//        String[] weaverAwareNames = beanFactory.getBeanNamesForType(LoadTimeWeaverAware.class, false, false);
//        for (String weaverAwareName : weaverAwareNames) {
//            getBean(weaverAwareName);
//        }
//
//        // Stop using the temporary ClassLoader for type matching.
//        beanFactory.setTempClassLoader(null);
//
//        // Allow for caching all bean definition metadata, not expecting further changes.
//        beanFactory.freezeConfiguration();
//
//        // Instantiate all remaining (non-lazy-init) singletons.
////        beanFactory.preInstantiateSingletons();
//        try{
//            preInstantiateSingletons((DefaultListableBeanFactory)beanFactory);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
//
//    public void preInstantiateSingletons(DefaultListableBeanFactory beanFactory) throws Exception {
//        // Iterate over a copy to allow for init methods which in turn register new bean definitions.
//        // While this may not be part of the regular factory bootstrap, it does otherwise work fine.
//        Field field = beanFactory.getClass().getDeclaredField("beanDefinitionNames");
//        field.setAccessible(true);
//        List<String> beanNames = new ArrayList<String>((List<String>)field.get(beanFactory));
//
//        // Trigger initialization of all non-lazy singleton beans...
//        for (String beanName : beanNames) {
//            Method method = beanFactory.getClass().getSuperclass().getSuperclass().getDeclaredMethod("getMergedLocalBeanDefinition", String.class);
//            method.setAccessible(true);
//            RootBeanDefinition bd = (RootBeanDefinition)method.invoke(beanFactory, beanName);
//            if (!bd.isAbstract() && bd.isSingleton() && !bd.isLazyInit()) {
//                if (beanFactory.isFactoryBean(beanName)) {
//                    final FactoryBean<?> factory = (FactoryBean<?>) getBean(FACTORY_BEAN_PREFIX + beanName);
//                    boolean isEagerInit;
//                    if (System.getSecurityManager() != null && factory instanceof SmartFactoryBean) {
//                        isEagerInit = AccessController.doPrivileged(new PrivilegedAction<Boolean>() {
//                            @Override
//                            public Boolean run() {
//                                return ((SmartFactoryBean<?>) factory).isEagerInit();
//                            }
//                        }, beanFactory.getAccessControlContext());
//                    }
//                    else {
//                        isEagerInit = (factory instanceof SmartFactoryBean &&
//                                ((SmartFactoryBean<?>) factory).isEagerInit());
//                    }
//                    if (isEagerInit) {
//                        getBean(beanName);
//                    }
//                }
//                else {
//                    long l1 = System.currentTimeMillis();
//                    getBean(beanName);
//                    System.out.println("create bean "+beanName+" time :"+(System.currentTimeMillis()-l1));
//                }
//            }
//        }
//
//        // Trigger post-initialization callback for all applicable beans...
//        for (String beanName : beanNames) {
//            Object singletonInstance = beanFactory.getSingleton(beanName);
//            if (singletonInstance instanceof SmartInitializingSingleton) {
//                final SmartInitializingSingleton smartSingleton = (SmartInitializingSingleton) singletonInstance;
//                if (System.getSecurityManager() != null) {
//                    AccessController.doPrivileged(new PrivilegedAction<Object>() {
//                        @Override
//                        public Object run() {
//                            smartSingleton.afterSingletonsInstantiated();
//                            return null;
//                        }
//                    }, beanFactory.getAccessControlContext());
//                }
//                else {
//                    smartSingleton.afterSingletonsInstantiated();
//                }
//            }
//        }
//    }
//}
