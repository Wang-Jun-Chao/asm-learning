import org.objectweb.asm.ClassWriter;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Opcodes.ACC_ABSTRACT;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;

/**
 * Author: 王俊超
 * Time: 2016-08-14 08-58 10:58
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class StubClassLoader extends ClassLoader {
    @Override
    protected Class findClass(String name) throws ClassNotFoundException {
        if (!name.endsWith("_Stub")) { // 与原文档的代码不一样，多了一个非（!）
            ClassWriter cw = new ClassWriter(0);

            cw.visit(V1_5, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE,
                    "pkg/Comparable", null, "java/lang/Object",
                    new String[]{"pkg/Mesurable"});
            cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I",
                    null, new Integer(-1)).visitEnd();
            cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I",
                    null, new Integer(0)).visitEnd();
            cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I",
                    null, new Integer(1)).visitEnd();
            cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo",
                    "(Ljava/lang/Object;)I", null, null).visitEnd();
            cw.visitEnd();

            byte[] b = cw.toByteArray();
            return defineClass(name, b, 0, b.length);
        }
        return super.findClass(name);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        StubClassLoader stubClassLoader = new StubClassLoader();
        Class clazz = stubClassLoader.findClass("pkg.Comparable");
        System.out.println(clazz);
    }
}
