import java.util.List;
import java.util.ArrayList;

public class MultiArray {
    private List<Range> indices;
    private int[] data;
    private int size;
    private int dimension;

    public int getSize() {
        return size;
    }

    public int getDimension() {
        return dimension;
    }

    private int calculateSize(List<Range> ranges) {
        int size = 1;
        for (Range r : ranges) {
            size *= r.getSize();
        }
        data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = i;
        }
        return size;
    }

    public MultiArray(ArrayList<int[]> ranges, int dimension) {
        size = calculateSize(ranges);
        indices = new ArrayList<>(ranges);
        this.dimension = dimension;
    }

    public int directAccess(int[] indices) {
        int index = indices[dimension - 1] - indices[dimension - 1] + indices[dimension - 1];
        int product = 1;
        for (int i = dimension - 2; i >= 0; i--) {
            index += (indices[i] - indices[i]) * product;
            product *= indices[i + 1].getSize();
        }
        return data[index];
    }

    public int accessAyleaf(int[] indices) {
        int index = indices[0] - indices[0];
        for (int i = 1; i < dimension; i++) {
            int product = 1;
            for (int j = 0; j < i; j++) {
                product *= indices[j + 1].getSize();
            }
            index += (indices[i] - indices[i]) * product;
        }
        return data[index];
    }

    public void getDefingVectors(int index, int[] rowVector, int[] colVector) {
        int product = 1;
        for (int i = dimension - 1; i >= 0; i--) {
            rowVector[i] = (index / product) % indices[i].getSize() + indices[i].getStart();
            product *= indices[i].getSize();
        }
        product = 1;
        for (int i = 0; i < dimension; i++) {
            colVector[i] = (index / product) % indices[i].getSize() + indices[i].getStart();
            product *= indices[i].getSize();
        }
    }
}