import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet(name = "AddContactServlet")
public class AddContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String position = request.getParameter("position");
        String location = request.getParameter("location");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String idcontact_cat = request.getParameter("idcontact_cat");

        HttpSession session = request.getSession();
        String uid = (String) session.getAttribute("uid");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cld",
                    "root","1234");
            String sql = "insert into cld.contact(name, position , gender, email, address, phone, location, contact_cat_id, user_id) " +
                    "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareStatement(sql);

            stm.setString(1, name);
            stm.setString(2, position);
            stm.setString(3, gender);
            stm.setString(4, email);
            stm.setString(5, address);
            stm.setString(6, phone);
            stm.setString(7, location);
            stm.setString(8, idcontact_cat);
            stm.setString(9, uid);

            stm.execute();

            response.sendRedirect("contact.jsp?idcontact_cat=" + idcontact_cat);

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
