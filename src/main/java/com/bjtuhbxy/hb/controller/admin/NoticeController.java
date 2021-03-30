package com.bjtuhbxy.hb.controller.admin;

import com.alibaba.fastjson.JSON;
import com.bjtuhbxy.hb.entity.Article;
import com.bjtuhbxy.hb.result.Result;
import com.bjtuhbxy.hb.result.ResultFactory;
import com.bjtuhbxy.hb.service.ArticleService;
import com.bjtuhbxy.hb.util.MyPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.sql.Date;


@ResponseBody
@Controller
public class NoticeController {

    @Autowired
    ArticleService articleService;

    Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @CrossOrigin
    @PostMapping("api/admin/content/article")
    public void saveArticle(@RequestBody @Validated Article article) {
        logger.info("文章：{}",article);

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



        article.setDate(sdf.format(date));
        logger.info("time:{}",date);
        articleService.addArticle(article);
    }

    @CrossOrigin
    @GetMapping("/api/article/{size}/{page}")
    public Result listArticles(@PathVariable("size") int size, @PathVariable("page") int page) {


        MyPage myPage = articleService.list(page - 1, size);
//        logger.info("myPageJSON:{}", JSON.toJSONString(myPage));
//        logger.info("RESULTJSON:{}", ResultFactory.buildSuccessResult(myPage).getResult());

        return ResultFactory.buildSuccessResult(myPage);
    }

    @CrossOrigin
    @DeleteMapping("/api/admin/content/article/{id}")
    public Result deleteArticle(@PathVariable("id") int id) {
        articleService.delete(id);
        return ResultFactory.buildSuccessResult("删除成功");
    }


    @CrossOrigin
    @GetMapping("/api/article/{id}")
    public Result getOneArticle(@PathVariable("id") int id) {
        return ResultFactory.buildSuccessResult(articleService.findById(id));
    }

}
