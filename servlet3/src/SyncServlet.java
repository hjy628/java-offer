import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @auther: hjy
 * @Date: 2021/6/29 15:40
 * @Description:
 */
@WebServlet("SyncServlet")
public class SyncServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SyncServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long t1 = System.currentTimeMillis();

        // 执行业务代码
        doSomeThing(req, resp);

        System.out.println("sync use:" + (System.currentTimeMillis() - t1));
    }


    private void doSomeThing(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {

        // 模拟耗时操作
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
        }

        //
        response.getWriter().append("done");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
