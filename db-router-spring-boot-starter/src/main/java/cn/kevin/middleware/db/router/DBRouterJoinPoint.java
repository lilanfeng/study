package cn.kevin.middleware.db.router;

import cn.kevin.middleware.db.router.annotation.DBRouter;
import cn.kevin.middleware.db.router.strategy.IDBRouterStrategy;
import javafx.beans.binding.ObjectBinding;
import org.aopalliance.intercept.Joinpoint;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description 数据路由切面，通过自定义注解的方式，拦截被切面的方法，进行数据库路由
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
@Aspect
public class DBRouterJoinPoint {
    private Logger logger = LoggerFactory.getLogger(DBRouterJoinPoint.class);

    private DBRouterConfig dbRouterConfig;

    private IDBRouterStrategy dbRouterStrategy;

    public DBRouterJoinPoint(DBRouterConfig dbRouterConfig, IDBRouterStrategy dbRouterStrategy)
    {
        this.dbRouterConfig = dbRouterConfig;
        this.dbRouterStrategy = dbRouterStrategy;
    }

    @Pointcut("@annotation(cn.kevin.middleware.db.router.annotation.DBRouter)")
    public void aopPoint(){

    }

    /**
     * 所有需要分库分表的操作，都需要使用自定义注解进行拦截，拦截后读取方法中的入参字段，根据字段进行路由操作。
     * 1. dbRouter.key() 确定根据哪个字段进行路由
     * 2. getAttrValue 根据数据库路由字段，从入参中读取出对应的值。比如路由 key 是 uId，那么就从入参对象 Obj 中获取到 uId 的值。
     * 3. dbRouterStrategy.doRouter(dbKeyAttr) 路由策略根据具体的路由值进行处理
     * 4. 路由处理完成比，就是放行。 jp.proceed();
     * 5. 最后 dbRouterStrategy 需要执行 clear 因为这里用到了 ThreadLocal 需要手动清空。关于 ThreadLocal 内存泄漏介绍 https://t.zsxq.com/027QF2fae
     */
    @Around("aopPoint() && @annotation(dbRouter)")
    public Object doRouter(ProceedingJoinPoint joinPoint, DBRouter dbRouter) throws Throwable {

        String dbKey = dbRouter.key();
        if (StringUtils.isBlank(dbKey) && StringUtils.isBlank(dbRouterConfig.getRouterKey())) {
            throw new RuntimeException("dbRouter.key() is null and dbRouterConfig.getRouterKey() is null");
        }
        dbKey = StringUtils.isBlank(dbKey) ? dbRouterConfig.getRouterKey() : dbKey;
        // 路由属性
        String dbKeyAttr = getAttrValue(dbKey, joinPoint.getArgs());

        // 路由策略
        dbRouterStrategy.doRouter(dbKeyAttr);
        try {
            return joinPoint.proceed();
        } finally {
            dbRouterStrategy.clear();
        }

    }

    private Method getMethod(JoinPoint joinPoint) throws NoSuchMethodException {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return joinPoint.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }

    public String getAttrValue(String attrName, Object[] args) throws Exception {
        if(1 == args.length){
            Object arg = args[0];
            if(arg instanceof String){
                return arg.toString();
            }
        }
        String fieldValue = null;
        for (Object arg : args) {
            try {
               if(StringUtils.isNotBlank(fieldValue)){
                   break;
               }
               fieldValue = String.valueOf( getValueByName(arg, attrName));
            } catch (Exception e) {
                logger.error("获取路由属性值失败 attr:{}",attrName, e);
            }
        }
        return fieldValue;
    }

    /**
     * 获取对象的特定属性值
     *
     * @author tang
     * @param item 对象
     * @param name 属性名
     * @return 属性值
     */
    private Object getValueByName(Object item, String name){
        try {
            Field field = getFieldByName(name, item);
            if (field == null) {
                return null;
            }
            field.setAccessible(true);
            Object obj = field.get(item);
            field.setAccessible(false);
            return obj;
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    /**
     * 根据名称获取方法，该方法同时兼顾继承类获取父类的属性
     *
     * @author tang
     * @param item 对象
     * @param name 属性名
     * @return 该属性对应方法
     */
    private Field getFieldByName(String name, Object item) {

        try {
            Field field = null;
            try {
                field = item.getClass().getDeclaredField(name);
            }catch (NoSuchFieldException e){
                field = item.getClass().getSuperclass().getDeclaredField(name);
            }
            return field;
        }catch (NoSuchFieldException e){
            return null;
        }
    }

}
