package com.in28minutes.unittesting.unittesting.model;

import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class MapMockTest {

    //@Mock:相当于Mockito.mock(Map.class);
    Map<String,Object> map= Mockito.mock(Map.class);


    @Captor
    private ArgumentCaptor<String> captorForString;

    @Captor
    private ArgumentCaptor<Object> captorForObject;

    @Test
    public void size(){
        when(map.size()).thenReturn(0);
        assertEquals(5,map.size());
    }

    @Test
    //@Disabled:取消该方法的执行
    public void callThreeTimes(){
        when(map.size()).thenReturn(1,2,3);
        assertEquals(1,map.size());
        assertEquals(2,map.size());
        assertEquals(3,map.size());

    }

    @Test
    public void returnOneElement(){
        Address myAddress=new Address("shanghai","road");
        Address myAddress1=new Address("shanghai","road");
        when(map.get("myAddress")).thenReturn(myAddress);
        assertEquals(myAddress1,map.get("myAddress"));//myAddress.equals(map.get("myAddress"))
        assertSame(myAddress,map.get("myAddress"));//myAddress==(map.get("myAddress"))
    }

    @Test
    public void returnWithGenericElements() {
        when(map.get(anyString())).thenReturn("any");

        assertEquals("any", map.get("a"));
        assertEquals("any", map.get("b"));

        verify(map).get("a");
        verify(map).get("b");
        verify(map,times(1)).get("a");
        verify(map,atLeast(1)).get(anyString());
        verify(map,atMost(2)).get(anyString());
        verify(map,never()).get(3);
    }

    @Test
    //捕捉参数
    public void argumentCapturing(){
        Address myAddress=new Address("shanghai","road");
        map.put("myAddress",myAddress);

        ArgumentCaptor<String> captorForString=ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Object> captorForObject=ArgumentCaptor.forClass(Object.class);

        verify(map).put(captorForString.capture(),captorForObject.capture());

        assertEquals("myAddress",captorForString.getValue());
        assertEquals(myAddress,captorForObject.getValue());
    }

    @Test
    public void multipleArgumentCapturing() {

        map.get("1");
        map.get("2");

        ArgumentCaptor<String> captorForString=ArgumentCaptor.forClass(String.class);

        verify(map,times(2)).get(captorForString.capture());

        assertEquals("2",captorForString.getValue());
        assertEquals("1",captorForString.getAllValues().get(0));
        assertEquals("2",captorForString.getAllValues().get(1));


    }

}
