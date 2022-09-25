import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns ="/calc")

public class Calc extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    request.setCharacterEncoding("UTF-8");
    response.setContentType("application/json; charset=utf-8");

    StringBuffer jb = new StringBuffer();
    String line;

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
try {
    BufferedReader reader = request.getReader();
while ((line = reader.readLine()) !=null) {
        jb.append(line);
    }

} catch (Exception e) {

}

    JsonObject jobjrequest = gson.fromJson(String.valueOf(jb),JsonObject.class);

    double firstNum = jobjrequest.get("a").getAsDouble();
    double secondNum= jobjrequest.get("b").getAsDouble();
    String operation = jobjrequest.get("math").getAsString();


    JsonObject jobjresponse = new JsonObject();

    if (operation.equals("+")) {
    jobjresponse.addProperty("result", firstNum + secondNum);
    }

    else if (operation.equals("-")) {
    jobjresponse.addProperty("result", firstNum - secondNum);
    }

    else if (operation.equals("*")) {
    jobjresponse.addProperty("result", firstNum * secondNum);
    }

    else if (operation.equals("/")) {
    jobjresponse.addProperty("result", firstNum / secondNum);
    }

    else {
    jobjresponse.addProperty("result", "Неизвестная операция");
    }

    response.setContentType("application/json; charset=utf-8");
    PrintWriter pw = response.getWriter();

    pw.print(jobjresponse);

    }
}


