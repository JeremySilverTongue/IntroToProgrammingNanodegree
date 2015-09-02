package com.example.silver.myapplication.backend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TipServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        List<QuickTip> tips = QuickTip.loadAll();
        List<Map> cleanTips = new ArrayList();
        for (QuickTip tip : tips){
            Map mMap = new HashMap<String,String>();
            mMap.put("title",tip.title);
            mMap.put("tip",tip.tip);
            cleanTips.add(mMap);
        }
        req.setAttribute("tips", cleanTips);
        req.getRequestDispatcher("/index.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("p");
        if (param == null){
            param = "New tip";
        }
        if (param.equals("debug")){
            QuickTip.StoreNew("Debug Tip", "Thank you for adding a debug tip! faded bicycle system a vehicle engine pre-courier boy geodesic military-grade lights fluidity singularity.");
        } else if (param.equals("clear")) {
            QuickTip.deleteAll();
        } else if (param.equals("notes")){
            QuickTip.addLesson4Notes();

        } else {
            log("ADDING A NEW TIP FROM THE FORM");
            String title = req.getParameter("title");
            String tip = req.getParameter("tip");
            log(title);
            log(tip);
            QuickTip.StoreNew(title, tip);

        }
        resp.sendRedirect("/");
    }
}
