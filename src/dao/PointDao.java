package dao;

import area.Point;

import java.sql.SQLException;
import java.util.List;

public interface PointDao {
    void addPoint(Point point) throws SQLException;
    Point getPoint(int id)throws SQLException;
    List<Point> getPoints()throws SQLException;
    void updatePoint(Point point)throws SQLException;
}
