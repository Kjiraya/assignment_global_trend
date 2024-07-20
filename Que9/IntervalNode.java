class IntervalNode {
    int start;
    int end;
    int maxEnd; 
    IntervalNode left;
    IntervalNode right;

    public IntervalNode(int start, int end) {
        this.start = start;
        this.end = end;
        this.maxEnd = end;
        this.left = null;
        this.right = null;
    }
}
