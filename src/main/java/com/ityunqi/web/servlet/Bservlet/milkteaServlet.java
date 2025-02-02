package com.ityunqi.web.servlet.Bservlet;

import com.alibaba.fastjson.JSON;
import com.ityunqi.pojo.Milktea;
import com.ityunqi.pojo.Result;
import com.ityunqi.service.B.Milkteaservice;
import com.ityunqi.service.impl.Bimpl.Milkteaserviceimpl;
import com.ityunqi.web.servlet.BaseServlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/milktea/*")
public class milkteaServlet extends BaseServlet {

    private Milkteaservice milkteaservice = new Milkteaserviceimpl();

    public void selectall(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        List<Milktea> milkteas= milkteaservice.selectall();
        String jsonString = JSON.toJSONString(milkteas);
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(jsonString);
        Result result=Result.success(milkteas);
        httpServletResponse.getWriter().write(JSON.toJSONString(result));

    }

    public void add(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        BufferedReader bufferedReader = httpServletRequest.getReader();
        String params = bufferedReader.readLine();
        Milktea milktea = JSON.parseObject(params, Milktea.class);
        milkteaservice.add(milktea);
        Result result=Result.success(milktea);
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }

    public void deleteid(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String id = httpServletRequest.getParameter("id");
        milkteaservice.deletebyid(Integer.parseInt(id));
        Result result=Result.success();
        httpServletResponse.getWriter().write(JSON.toJSONString(result));

    }

    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        BufferedReader br = httpServletRequest.getReader();
        String params = br.readLine();
        String _id = httpServletRequest.getParameter("id");
        int id = Integer.parseInt(_id);
        Milktea milktea = JSON.parseObject(params, Milktea.class);
        Milktea milktea1 = new Milktea();

        milktea1.setId(id);

        milktea1.setIntroduce(milktea.getIntroduce());

        milktea1.setKindid(milktea.getKindid());

        milktea1.setPrice(milktea.getPrice());

        milktea1.setCount(milktea.getCount());

        milktea1.setSalenumber(milktea.getSalenumber());
        milkteaservice.update(milktea1);
        Result result=Result.success(milktea1);
        httpServletResponse.getWriter().write(JSON.toJSONString(result));

    }

    public void kindidselect(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        BufferedReader br = httpServletRequest.getReader();
        String params = br.readLine();
        Milktea milktea = JSON.parseObject(params, Milktea.class);
        String _kindid = httpServletRequest.getParameter("kindid");

        int kindid = Integer.parseInt(_kindid);
        List<Milktea> milkteas = milkteaservice.kindidselect(kindid);
        String jsonString = JSON.toJSONString(milkteas);
        httpServletResponse.setContentType("text/json;charset=utf-8");
        Result result=Result.success(milktea);
        httpServletResponse.getWriter().write(JSON.toJSONString(result));



    }
    public void salenumberselect(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse)throws ServletException,IOException{
        BufferedReader br = httpServletRequest.getReader();
        String params = br.readLine();
        Milktea milktea = JSON.parseObject(params, Milktea.class);
        String _salenumber = httpServletRequest.getParameter("salenumber");

        int salenumber = Integer.parseInt(_salenumber);
        List<Milktea> milkteas = milkteaservice.salenumberselect(salenumber);
        String jsonString = JSON.toJSONString(milkteas);
        httpServletResponse.setContentType("text/json;charset=utf-8");
        Result result=Result.success(milktea);
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }



}
