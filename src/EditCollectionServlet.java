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

@WebServlet(name = "EditCollectionServlet")
public class EditCollectionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String collectionName = request.getParameter("collectionName");
        String idcollection = request.getParameter("idcollection");

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cld",
                    "root","1234");
            String sql = "update cld.collection set collectionName = ? " +
                    "where  idcollection = ?";
            PreparedStatement stm = conn.prepareStatement(sql);

            stm.setString(1, collectionName);
            stm.setString(2, idcollection);

            stm.execute();

            response.sendRedirect("collection.jsp?idcollection=" + idcollection + "&collectionName=" + collectionName);

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
