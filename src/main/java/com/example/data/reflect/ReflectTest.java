package com.example.data.reflect;

import com.example.data.demo.domain.entity.UserEntity;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTest {

    public static void main(String[] args) throws Exception {

        UserEntity userEntity = new UserEntity();

        Class<? extends UserEntity> clazz = userEntity.getClass();

        Field[] declaredFields = clazz.getDeclaredFields();

        Field idField = clazz.getDeclaredField("id");
        Field nameField = clazz.getDeclaredField("name");
        Field field = clazz.getDeclaredField("id");
        field.setAccessible(true);
        field.set(userEntity, "admin");
        System.out.println(userEntity);

        Method[] declaredMethods = clazz.getDeclaredMethods();

        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }

        BeanWrapper  beanWrapper = new BeanWrapperImpl(userEntity);
        beanWrapper.setPropertyValue(idField.getName(),"id");

        beanWrapper.setPropertyValue(new PropertyValue(nameField.getName(),"username"));


    }




}
