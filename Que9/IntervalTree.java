import java.util.ArrayList;
import java.util.List;

public class IntervalTree {
    private IntervalNode root;

    public IntervalTree() {
        this.root = null;
    }

    public void insertInterval(int start, int end) {
        root = insert(root, start, end);
    }

    private IntervalNode insert(IntervalNode node, int start, int end) {
        if (node == null) {
            return new IntervalNode(start, end);
        }

        
        if (start < node.start) {
            node.left = insert(node.left, start, end);
        } else {
            node.right = insert(node.right, start, end);
        }

        
        if (end > node.maxEnd) {
            node.maxEnd = end;
        }

        return node;
    }

    public void deleteInterval(int start, int end) {
        root = delete(root, start, end);
    }

    private IntervalNode delete(IntervalNode node, int start, int end) {
        if (node == null) {
            return null;
        }

        if (start < node.start) {
            node.left = delete(node.left, start, end);
        } else if (start > node.start) {
            node.right = delete(node.right, start, end);
        } else {
            if (end == node.end) {
                
                if (node.left == null) {
                    return node.right;
                } else if (node.right == null) {
                    return node.left;
                }

                IntervalNode successor = findMin(node.right);
                node.start = successor.start;
                node.end = successor.end;
                node.right = delete(node.right, successor.start, successor.end);
            } else {
                node.right = delete(node.right, start, end);
            }
        }

        updateMaxEnd(node);
        return node;
    }

    private IntervalNode findMin(IntervalNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

     private void updateMaxEnd(IntervalNode node) {
        if (node != null) {
            int maxEnd = node.end;
            if (node.left != null) {
                maxEnd = Math.max(maxEnd, node.left.maxEnd);
            }
            if (node.right != null) {
                maxEnd = Math.max(maxEnd, node.right.maxEnd);
            }
            node.maxEnd = maxEnd;
        }
    }

    public List<IntervalNode> findOverlappingIntervals(int start, int end) {
        List<IntervalNode> result = new ArrayList<>();
        findOverlapping(root, start, end, result);
        return result;
    }

    private void findOverlapping(IntervalNode node, int start, int end, List<IntervalNode> result) {
        if (node == null) {
            return;
        }

        if (node.start <= end && node.end >= start) {
            result.add(node);
        }

        if (node.left != null && node.left.maxEnd >= start) {
            findOverlapping(node.left, start, end, result);
        }

        if (node.right != null && node.start <= end && node.right.maxEnd >= start) {
            findOverlapping(node.right, start, end, result);
        }
    }

    public static void main(String[] args) {
        IntervalTree intervalTree = new IntervalTree();

        intervalTree.insertInterval(15, 20);
        intervalTree.insertInterval(10, 30);
        intervalTree.insertInterval(17, 19);
        intervalTree.insertInterval(5, 20);
        intervalTree.insertInterval(12, 15);
        intervalTree.insertInterval(30, 40);

        int queryStart = 14;
        int queryEnd = 16;

        List<IntervalNode> overlappingIntervals = intervalTree.findOverlappingIntervals(queryStart, queryEnd);
        System.out.println("Intervals overlapping with [" + queryStart + ", " + queryEnd + "]:");
        for (IntervalNode interval : overlappingIntervals) {
            System.out.println("[" + interval.start + ", " + interval.end + "]");
        }
    }
}
