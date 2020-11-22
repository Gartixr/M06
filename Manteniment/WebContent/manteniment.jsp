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
            Data Importacio: <input type="text" name="dataImportacio" />
            Nom Model: <input type="text" name="nomModel" />
			Preu: <input type="text" name="preu" />
            Arreglat: <input type="text" name="arreglat" />
			<input type="submit" value="Add" />
		</form>
		
		<form action="PropietariServlet">
            Data Peticio: <input type="text" name="dataPeticio" />
            Nom Propietari: <input type="text" name="nomPropietari" />
			Taller: <input type="text" name="taller" />
            Premium: <input type="text" name="premium" />
            <input type="submit" value="Add" />
        </form>
        
 <hr><ol> <%
            @SuppressWarnings("unchecked") 
            List<Vehicle> vehicles = (List<Vehicle>)request.getAttribute("vehicles");
            for (Vehicle vehicle : vehicles) { %>
                <li> <%= vehicle %> </li> <%
            } %>
        </ol><hr>
        <br>
        <hr><ol> <%
            @SuppressWarnings("unchecked") 
            List<Propietari> propietaris = (List<Propietari>)request.getAttribute("propietaris");
            for (Propietari propietari : propietaris) { %>
                <li> <%= propietari %> </li> <%
            } %>
        </ol><hr>
        <br> 
        
     </body>
 </html>