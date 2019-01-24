package com.hu.boot;

import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TestJava8 {
    @Test
    public void get() throws Exception{
        ArrayList<String> strings = new ArrayList<>();
        strings.add("aaa");
        strings.add("bbb");
        strings.add("ccc");
        strings.forEach(e ->System.out.println(e));
        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.instant());
        final LocalDate now = LocalDate.now();
        System.out.println(now);
        LocalTime now1 = LocalTime.now();
        System.out.println(now1);
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName( "JavaScript" );

        System.out.println( engine.getClass().getName() );
        System.out.println( "Result:" + engine.eval( "function f() { return 1; }; f() + 1;"  ));


    }

}
