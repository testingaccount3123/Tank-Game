package rccookie.geometry;

public class Rotation implements Cloneable {

    public static final String SAVE_DIR = "saves\\geometry\\rotations";

    private static final long serialVersionUID = -8758776053760328675L;

    public double z;
    public double y;
    public double x;


    public Rotation() {
        this(0, 0, 0);
    }

    public Rotation(Rotation copy) {
        this(copy.z, copy.y, copy.x);
    }
    
    public Rotation(double z) {
        this(z, 0, 0);
    }

    public Rotation(double z, double y) {
        this(z, y, 0);
    }

    public Rotation(double z, double y, double x) {
        this.z = z;
        this.y = y;
        this.x = x;
    }



    @Override
    public Rotation clone() {
        return new Rotation(this);
    }

    @Override
    public String toString() {
        return "{z=" + z + "|y=" + y + "|x=" + x + "}: " + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!(obj instanceof Rotation)) return false;
        return z == ((Rotation)obj).z && y == ((Rotation)obj).y && x == ((Rotation)obj).x;
    }


    public Rotation add(Rotation addedRotation) {
        if(addedRotation != null) {
            z += addedRotation.z;
            y += addedRotation.y;
            x += addedRotation.x;
        }
        return this;
    }

    public Rotation added(Rotation addedRotation) {
        return clone().add(addedRotation);
    }
}
