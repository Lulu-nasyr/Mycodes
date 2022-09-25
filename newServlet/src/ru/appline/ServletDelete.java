package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Model;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/delete")

public class ServletDelete extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws IOException {
//        response.setContentType("text/html; charset=utf-8");
//        request.setCharacterEncoding("UTF-8");
//
//        PrintWriter pw = response.getWriter();
//
//        Model model = Model.getInstance();
//
//        int id = Integer.parseInt(request.getParameter("id"));
//
//        if (id>0& id<model.getFromList().size()) {
//            model.delete(id);
//            pw.print("<html>" +
//                    "<h3>Пользователь с таким id успешно удален!</h3>" +
//                    "<a href=\"index.jsp\"> Домой </a>" +
//                    "</html>");
//        } else {
//            pw.print("<html>" +
//                    "<h3>Такого пользователя нет :( </h3>" +
//                    "<a href=\"index.jsp\"> Домой </a>" +
//                    "</html>");
//        }
//    }

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Model model = Model.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer jb = new StringBuffer();
        String line;

        PrintWriter pw = response.getWriter();

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) !=null) {
                jb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }

        JsonObject jobj = gson.fromJson(String.valueOf(jb),JsonObject.class);

        request.setCharacterEncoding("UTF-8");

        int id = jobj.get("id").getAsInt();

        if (id>0& id<model.getFromList().size()) {
            model.delete(id);

            JsonObject jer = new JsonObject();
            jer.addProperty("Result", "Пользователь с таким id успешно удален!");
            pw.print(jer);


        } else {

            JsonObject jer = new JsonObject();
            jer.addProperty("Error", "Такого пользователя нет :( ");
            pw.print(jer);

        }

    }

        }


