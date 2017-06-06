package area;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "points", indexes = {})
public class Point implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "x")
    private double  x;

    @Column(name = "y")
    private double y;

    @Column(name = "result")
    private boolean result;

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public boolean isResult() {
        return result;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public boolean getResult() { return result; }
    public void setResult(boolean result) { this.result = result; }

    public Point () {

    }

    public boolean check (double r) {
        if (this.getX() > 0 && this.getY() > 0 && this.getX() < r / 2 && this.getY() < r) {
            return true;
        } else if (this.getX() < 0 && this.getY() > 0 && this.getX()*this.getX() + this.getY()*this.getY() < r*r) {
            return true;
        } else if (this.getX() < 0 && this.getY() < 0 && this.getY() > -this.getX() - r / 2) {
            return true;
        } else if (this.getX() == 0 && this.getY() > -r / 2 && this.getY() < r) {
            return true;
        } else if (this.getY() == 0 && this.getX() > - r && this.getX() < r / 2) {
            return true;
        }
        return false;
    }
}
