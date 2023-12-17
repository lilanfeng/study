package com.lilanfeng2089.study.proxy;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;

import java.io.IOException;

import static jdk.internal.org.objectweb.asm.Opcodes.ASM5;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description  ASM代理
 * 场景：全链路监控、破解工具包、CGLIB、Spring获取类元数据等
 * 点评：这种代理就是使用字节码编程的方式进行处理，它的实现方式相对复杂，而且需要了解Java虚拟机规范相关的知识。因为你的每一步代理操作，都是在操作字节码指令，例如：Opcodes.GETSTATIC、Opcodes
 * .INVOKEVIRTUAL，除了这些还有小200个常用的指令。但这种最接近底层的方式，也是最快的方式。所以在一些使用字节码插装的全链路监控中，会非常常见。
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class ASMProxy extends ClassLoader{

    public static <T> T getProxy(Class clazz) throws IOException, InstantiationException, IllegalAccessException {

        ClassReader classReader = new ClassReader(clazz.getName());
        ClassWriter classWriter = new ClassWriter(classReader,ClassWriter.COMPUTE_MAXS);
        classReader.accept(new ClassVisitor(ASM5,classWriter) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {

                //方法过滤
                if (!"queryUserInfo".equals(name)) {
                    return super.visitMethod(access,name,descriptor,signature,exceptions);
                }
                final  MethodVisitor methodVisitor  = super.visitMethod(access,name,descriptor,signature,exceptions);
                return new AdviceAdapter(ASM5,methodVisitor,access,name,descriptor) {
                    @Override
                    protected void onMethodEnter() {
                        //执行指令，获取静态属性
                        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC,"java/lang/System","out","Ljava/io/PrintStream;");
                        //加载常量 load constant
                        methodVisitor.visitLdcInsn(name + " 你被代理了，By ASM！");

                        //调用方法
                        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL,"java/io/PrintStrem","println","(Ljava/lang/String;)V",false);

                        super.onMethodEnter();
                    }
                };
            }
        },ClassReader.EXPAND_FRAMES);
        byte[] bytes = classWriter.toByteArray();
        return (T) new ASMProxy().defineClass(clazz.getName(),bytes,0,bytes.length).newInstance();
    }


    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {
        IUserApi userApi = ASMProxy.getProxy(UserApi.class);
        String invoke = userApi.queryUserInfo();
        System.out.println("测试结果:" + invoke);
    }
}
