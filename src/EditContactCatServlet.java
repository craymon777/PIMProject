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

@WebServlet(name = "EditContactCatServlet")
public class EditContactCatServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String idcontact_cat = request.getParameter("idcontact_cat");

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cld",
                    "root","1234");
            String sql = "update cld.contact_cat set category = ? " +
                    "where  idcontact_cat = ?";
            PreparedStatement stm = conn.prepareStatement(sql);

            stm.setString(1, name);
            stm.setString(2, idcontact_cat);

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
