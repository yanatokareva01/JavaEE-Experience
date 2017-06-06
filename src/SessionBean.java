import area.Point;
import dao.PointDao;
import dao.PointDaoImpl;
import org.primefaces.event.SlideEndEvent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.SQLException;
import java.util.List;

@ManagedBean(name = "sessionBean", eager = true)
@SessionScoped
public class SessionBean {
    public SessionBean () {
        this.setR(1.0);
        this.updatePoints();
    }

    private Point point = new Point();
    private double R;
    private PointDao pointDao = new PointDaoImpl();
    private List<Point> pointList;

    public Point getPoint() {
        return point;
    }
    public void setPoint(Point point) {
        this.point = point;
    }
    public double getR() {
        return this.R;
    }
    public void setR(double R) {
        this.R = R;
    }
    public PointDao getPointDao() {
        return this.pointDao;
    }
    public void setPointDao(PointDao pointDao) {
        this.pointDao = pointDao;
    }
    public List<Point> getPointList(){
        try {
            this.pointList = pointDao.getPoints();
        } catch (Exception e){

        }

        return this.pointList;
    }
    public void setPointList(List<Point> list){
        this.pointList = list;
    }

    public void updatePoints () {
        this.getPointList();
        if (this.pointList != null) {
            for (Point p : this.pointList) {
                p.setResult(p.check(this.getR()));
                try{
                    this.pointDao.updatePoint(p);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void onSlideEnd(SlideEndEvent event) {
        this.setR(event.getValue());
        this.updatePoints();
    }

    public void savePoint () {
        try {
            Point p = new Point();
            p.setX(this.point.getX());
            p.setY(this.point.getY());
            p.setResult(this.point.check(this.R));
            this.pointDao.addPoint(p);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
