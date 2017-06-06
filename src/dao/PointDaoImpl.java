package dao;

import area.Point;
import util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

public class PointDaoImpl implements PointDao {

    public void addPoint(Point point) throws SQLException {
        EntityManager manager = null;
        try {
            manager = HibernateUtil.getEntityManager();
            manager.getTransaction().begin();
            manager.persist(point);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
    }

    public Point getPoint(int id) throws SQLException {
        Point result = null;
        EntityManager manager = null;
        try {
            manager = HibernateUtil.getEntityManager();
            result = manager.find(Point.class, id);
        } catch (Exception e){
            manager.getTransaction().rollback();
        }
        return result;
    }

    public List<Point> getPoints() throws SQLException {
        List<Point> points = null;
        EntityManager manager = null;
        try {
            manager = HibernateUtil.getEntityManager();
            CriteriaBuilder cb = manager.getCriteriaBuilder();
            CriteriaQuery<Point> cq = cb.createQuery(Point.class);
            Root<Point> pet = cq.from(Point.class);
            cq.select(pet);
            TypedQuery<Point> q = manager.createQuery(cq);
            points = q.getResultList();
        } catch (Exception e){
            manager.getTransaction().rollback();
        }

        return points;
    }

    public void updatePoint(Point newPoint) throws SQLException {
        EntityManager manager = null;
        try {
            manager = HibernateUtil.getEntityManager();
            Point point = manager.find(Point.class, newPoint.getId());
            manager.getTransaction().begin();
            point.setX(newPoint.getX());
            point.setY(newPoint.getY());
            point.setResult(newPoint.getResult());
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
    }
}
