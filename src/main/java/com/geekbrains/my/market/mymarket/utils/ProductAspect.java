package com.geekbrains.my.market.mymarket.utils;

import com.geekbrains.my.market.mymarket.model.Product;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

@Aspect
@Component
public class ProductAspect {
    @AfterReturning(
            pointcut = "execution(public * com.geekbrains.my.market.mymarket.restControllers.RestProductController.*Product(..))",
            returning = "result")
    public void beforeUserModifyInUserDAOClass(ResponseEntity result) {

        try {
            String fileUrl = "src/main/files/history.txt";
            File file = new File(fileUrl);
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(fileUrl,true);

            StringBuilder message = new StringBuilder();
            message.append(new Date()).append(": ");
            if(result.getStatusCode() == HttpStatus.OK){
                message.append("Найден продукт:");
            } else if(result.getStatusCode() == HttpStatus.CREATED){
                message.append("Создан продукт:");
            } else if(result.getStatusCode() == HttpStatus.ACCEPTED){
                message.append("Изменен продукт:");
            }
            message.append(result.toString());
            fileWriter.write(message.toString());
            //fileWriter.write(System.currentTimeMillis()+". Что-то делают с продуктом: "+result.toString());
            fileWriter.append('\n');
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
