<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Report App</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
        rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
        crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h3 class="pb-3 pt-3"> Report Application </h3>


<form:form action="" modelAttribute="search" method="POST">
    <table>
        <tr>
            <td>Plan Name:</td>
            <td>
                <form:select path="planName">
                    <form:option value="">--Select--</form:option>
                </form:select>
            </td>
       
        
            <td>Plan Status:</td>
            <td>
                <form:select path="planStatus">
                    <form:option value="">--Select--</form:option>
                </form:select>
            </td>

            <td>Gender:</td>
            <td>
                <form:select path="gender">
                    <form:option value="">--Select--</form:option>
                    <form:option value="Male">Male</form:option>
                    <form:option value="Fe-Male">Fe-Male</form:option>
                </form:select>
            </td>
        </tr>

        <tr>
            <td>Start Date:</td>
            <td><form:input path="startDate" /></form></td>
       
            <td>End Date:</td>
            <td><form:input path="startDate" /></form></td>
        </tr>

        <tr>
            <td><input type="submit" value="Search" class="btn btn-primary" /></td>
        </tr>
        
    </table>

</form:form>
<hr>
<hr>

Export : <a href="">Excel</a> <a href="">Pdf</a>


</div> 



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
crossorigin="anonymous">
</script>
</body>
</html>
