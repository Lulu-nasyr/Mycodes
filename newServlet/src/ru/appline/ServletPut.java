package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Model;
import ru.appline.logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/put")
public class ServletPut extends HttpServlet {
    //    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws  IOException {
//
//        response.setContentType("text/html; charset=utf-8");
//        request.setCharacterEncoding("UTF-8");
//        PrintWriter pw = response.getWriter();
//        int id = Integer.parseInt(request.getParameter("id"));
//        Model model = Model.getInstance();
//
//        if (id > 0 & id < model.getFromList().size()) {
//            String name = request.getParameter("name");
//            String surname = request.getParameter("surname");
//            double salary = Double.parseDouble(request.getParameter("salary"));
//
//            User user = new User(name, surname, salary);
//            model.update(user, id);
//            pw.print("<html>" +
//                    "<h3>Пользователь с таким id успешно обновлен!</h3>" +
//                    "<a href=\"index.jsp\"> Домой </a>" +
//                    "</html>");
//        } else {
//            pw.print("<html>" +
//                    "<h3>Такого пользователя нет :( </h3>" +
//                    "<a href=\"index.jsp\"> Домой </a>" +
//                    "</html>");
//
//
//        }
//    }
    Model model = Model.getInstance();

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer jb = new StringBuffer();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }

        JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);

        request.setCharacterEncoding("UTF-8");

        int id = jobj.get("id").getAsInt();

        String name = jobj.get("name").getAsString();
        String surname = jobj.get("surname").getAsString();
        Double salary = jobj.get("salary").getAsDouble();

        User user = new User(name, surname, salary);

        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();

        pw.print(gson.toJson(model.getFromList()));


        if (id > 0 & id < model.getFromList().size()) {

            model.update(user, id);

            JsonObject jer = new JsonObject();
            jer.addProperty("Result", "Пользователь с таким id успешно обновлен!");
            pw.print(jer);

        } else {

            JsonObject jer = new JsonObject();
            jer.addProperty("Error", "Такого пользователя нет :( ");
            pw.print(jer);
        }
    }
}
