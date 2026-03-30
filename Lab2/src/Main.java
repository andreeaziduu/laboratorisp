void main() {
        List<Integer> x = new ArrayList();
        List<Integer> y = new ArrayList();

        x.add(2); x.add(3); x.add(10); x.add(1); x.add(7);
        y.add(1); y.add(8); y.add(6); y.add(5); y.add(4); y.add(2); y.add(0);

        System.out.println("x: " + x);
        System.out.println("y: " + y);

        List<Integer> xPlusY = new ArrayList();
        xPlusY.addAll(x);
        xPlusY.addAll(y);
        Collections.sort(xPlusY);
        System.out.println("xPlusY: " + xPlusY);


        Set<Integer> zSet = new TreeSet();
        for (Integer element : x) {
            if (y.contains(element)){
                zSet.add(element);}
        }
    System.out.println("zSet: " + zSet);

        List<Integer> xMinusY = new ArrayList();
        xMinusY.addAll(x);
        xMinusY.removeAll(y);
    System.out.println("xMinusY: " + xMinusY);

    int p = 4;
    List<Integer> xPlusYLimitedByP = new ArrayList();
    for (Integer element : xPlusY){
        if (element <= p){
            xPlusYLimitedByP.add(element);
        }
    }
    System.out.println("xPlusYLimitedByP: " + xPlusYLimitedByP);

}

