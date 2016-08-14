import org.objectweb.asm.ClassVisitor;

import static org.objectweb.asm.Opcodes.ASM4;

/**
 * Author: Íõ¿¡³¬
 * Time: 2016-08-15 06-37 10:58
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class MultiClassAdapter extends ClassVisitor {
    protected ClassVisitor[] cvs;

    public MultiClassAdapter(ClassVisitor[] cvs) {
        super(ASM4);
        this.cvs = cvs;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        for (ClassVisitor cv : cvs) {
            cv.visit(version, access, name, signature, superName, interfaces);
        }
    }
}
