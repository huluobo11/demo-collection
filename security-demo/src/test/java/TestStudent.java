import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestStudent {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        // 一切皆对象，所以 Student类  本身也是一个对象。 这个对象是 Class类型的
        // 根据类名获取 能表示student类 的 Class 类型的对象
        Class<?> studentClass = Class.forName("com.security.example.Student");

        // 用反射创建 Student 类型的一个对象，   作用同 new Student();
        Object obj = studentClass.newInstance();
        // 打印 obj 对象的类名
        System.out.println(obj.getClass().getName());
        System.out.println();

        // 打印student 类的 全部方法 的方法名称，并调用 方法
        Method[] methods = studentClass.getDeclaredMethods();
        for (Method method : methods) {
            // 打印 方法名
            System.out.println(method.getName());

            // 调用方法
            method.invoke(obj);
        }



    }

    @Test
    public void te() {
        Runnable r = new Runnable(){
            @Override
            public void run() {
                System.out.println("123");
            }
        };

        // 打印 r对象 对应的类 的类名  ，类名就叫  TestStudent$1
        System.out.println(r.getClass().getName());
    }
}
