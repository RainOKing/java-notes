package com.notes.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.google.common.collect.Lists;
import com.notes.domain.BrandInfo;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/easypoi")
public class EasyPoiExcelController {

    private static final Logger log = LoggerFactory.getLogger(EasyPoiExcelController.class);

    @GetMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response){
        log.info("请求 exportExcel start ......");


        BrandInfo brandInfo = new BrandInfo();
        brandInfo.setBrandGuid("123");
        brandInfo.setBrandName("aaa");
        brandInfo.setCustomerid("123");
        brandInfo.setFlag("1");
        brandInfo.setSource(1);
        // 获取用户信息
        List<BrandInfo> list = Lists.newArrayList(brandInfo);

        try {
            // 设置响应输出的头类型及下载文件的默认名称
            String fileName = new String("demo信息表.xls".getBytes("utf-8"), "ISO-8859-1");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setContentType("application/vnd.ms-excel;charset=gb2312");

            //导出
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), BrandInfo.class, list);
            workbook.write(response.getOutputStream());

            //导入
//            ExcelImportUtil.importExcel();
            log.info("请求 exportExcel end ......");
        } catch (IOException e) {
            log.info("请求 exportExcel 异常：{}", e.getMessage());
        }
    }
}