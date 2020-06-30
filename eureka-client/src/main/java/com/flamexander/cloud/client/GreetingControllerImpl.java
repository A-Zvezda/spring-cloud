package com.flamexander.cloud.client;

import com.flamexander.cloud.client.repositories.CategoryRepository;
import com.flamexander.cloud.client.services.CategoryService;
import com.netflix.discovery.EurekaClient;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

@RestController
public class GreetingControllerImpl implements GreetingController {
    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    private TestRepository repository;

    public GreetingControllerImpl(@Autowired TestRepository repository) {
        this.repository = repository;
    }

    private CategoryService categoryService;

    @Autowired
    public void setCategoryRepository(@Autowired CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    ExcelReader excelReader;

    WriteExcel writeExcel;

    @Autowired
    public void setGreetingClient(ExcelReader excelReader) {
        this.excelReader = excelReader;
    }

    @Autowired
    public void setWriteExcel(WriteExcel writeExcel) {
        this.writeExcel = writeExcel;
    }

    @Value("${spring.application.name}")
    private String appName;

    @Value("${userValue}")
    private String username;

    @Override
    public String greeting() {
//        try {
//            return String.format(excelReader.read().toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InvalidFormatException e) {
//            e.printStackTrace();
//        }
//        return "";

        List<ProductDTO> products = repository.getProducts(1).stream().collect(toCollection(ArrayList::new));
         System.out.println(categoryService.getCategoryById(1L).toString() + "aaaaaaaaaaaaa");
        return products.get(0).getTitle();
    }

    @Override
    public String greeting1() {
        return String.format("Hello1");
    }

    @Override
    public String greeting2() {
        return String.format("new Param");
    }

    @Override
    public void getIdByValue(String fileName) {
        try {
            ExcelReader.file = String.format("%s.xlsx",fileName);
            writeExcel.write(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public String getTableBase() {
//        return String.format("Hello1");
//    }

    @GetMapping("/abc")
    public void test() {
        System.out.println(username);
    }
}
