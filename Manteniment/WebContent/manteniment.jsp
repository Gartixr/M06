<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,manteniment.Vehicle"%>
<%@page import="java.util.*,manteniment.Propietari"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Manteniment</title>
    </head>

    <body>
        <form method="POST" action="VehicleServlet">
            Data Importacio: <input type="text" name="name" />
            Nom Model: <input type="text" name="name" />
			Preu: <input type="text" name="name" />
            Arreglat: <input type="text" name="name" />
            <input type="submit" value="Add" />
        </form>

        <hr><ol> <%
            @SuppressWarnings("unchecked") 
            List<Vehicle> vehicles = (List<Vehicle>)request.getAttribute("vehicle");
            for (Vehicle vehicle : vehicles) { %>
                <li> <%= vehicle %> </li> <%
            } %>
        </ol><hr>
 
        <iframe src="http://www.objectdb.com/pw.html?web-eclipse"
            frameborder="0" scrolling="no" width="100%" height="30"> </iframe>
     </body>
 </html>