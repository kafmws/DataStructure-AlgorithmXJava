public class LinkedList {//无头结点链表

    private Node head;
    private int length;

    public LinkedList() {
    }
    public LinkedList(DataType data) {
        this.head = new Node(data);
        length = 1;
    }

    public boolean isEmpty(){
        return length == 0;
    }

    public int length(){
        return length;
    }

    @Override
    public String toString() {
        if(length==0)
            return "[]";
        StringBuffer sb = new StringBuffer("[");
        Node tem = head;
        while (true) {
            sb.append(tem.data.value);
            if (tem.hasNext()) {
                sb.append(", ");
                tem = tem.next;
            }else
                break;
        }
        sb.append("]");
        return sb.toString();
    }

    private void addNode2TheEnd(Node node) {
        //node.next = null;//产生Node时自动置空 或  用于连接两条链
        if (head == null) {
            head = node;
        } else {
            Node tem = head;
            while (tem.next != null) {
                tem = tem.next;
            }
            tem.next = node;
        }
    }

    private void addNode2TheHead(Node node) {//头插
        if (head == null) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    public void addNode(int data) {//默认尾插
        addNode(data, false);
    }

    /**
     * @param pos if true add2the head else add2the end
     */
    public void addNode(int data, boolean pos) {
        if (pos) {
            addNode2TheHead(new Node(data));
        } else {
            addNode2TheEnd(new Node(data));
        }
        length++;
        //无需考虑新增node的next是否为空
//        while(node.hasNext()){
//            length++;
//            node = node.next;
//        }
    }

    public Node searchNode(int data) {
        Node tem = head;
        DataType obj = new DataType(data);
        for (; tem != null && !tem.data.equals(obj); tem = tem.next) ;
        return tem;
    }

    public Node searchNodeInJava(int data) {
        if(head==null)
            return null;
        DataType obj = new DataType(data);
        Node tem = head;
        do{
            if (tem.data.equals(obj))
                return tem;
        }while (tem.hasNext());
        return null;
    }

    public boolean removeNode(int data) {
        if (head == null)
            return false;
        DataType obj = new DataType(data);
        Node tem = head;
        boolean isDeleted = false;
        if (head.data.equals(obj)) {
            head = head.next;
            isDeleted = true;
        } else {
            while (tem.hasNext()) {
                if (tem.next.data.equals(obj)) {
                    isDeleted = true;
                    tem.next = tem.next.next;
                    break;
                }
            }
        }
        if(isDeleted)
            length--;
        return isDeleted;
    }

    public boolean removeNodeAll(int data) {
        if (head == null)
            return false;
        DataType obj = new DataType(data);
        int before = length;
        if (head.data.equals(obj)) {
            while (head.data.equals(obj)){
                head = head.next;
                length--;
            }
        }
        Node tem = head;
        while (tem!=null&&tem.hasNext()) {
            if (tem.next.data.equals(obj)) {
                tem.next = tem.next.next;
                length--;
            }
            tem = tem.next;
        }
        return length != before;
    }

    public boolean modifyNode(int obj, int newData){
        Node objNode = searchNode(obj);
        if(objNode!=null)
            objNode.data = new DataType(newData);
        return objNode!=null;
    }

    public void extendList(LinkedList anotherList){
        if(anotherList == this){
            System.out.println("can not be itself");
            return;
        }
        if(head == null){
            head = anotherList.head;
            length = anotherList.length;
            return;
        }
        Node tem = head;
        for (int i = 0;i<length-1;i++)
            tem = tem.next;
        tem.next = anotherList.head;
        length += anotherList.length;
    }

    private class DataType {
        private int value;

        public DataType(int data) {
            this.value = data;
        }

        public int getData() {
            return value;
        }

        public void setData(int data) {
            this.value = data;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof DataType)) return false;
            DataType dataType = (DataType) o;
            return getData() == dataType.getData();
        }
    }

    private class Node {
        private DataType data;
        private Node next;

        private Node(int data) {
            this.data = new DataType(data);
            this.next = null;
        }

        private Node(DataType data) {
            this.data = data;
            this.next = null;
        }

        private boolean hasNext(){
            return next != null;
        }

        public DataType getData() {
            return data;
        }

        public void setData(DataType data) {
            this.data = data;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return getData().equals(node.getData());
        }

    }

}

class LinkedListTest{
    public static void main(String[] args) {
        LinkedList list1 = new LinkedList();
        LinkedList list2 = new LinkedList();
        for(int i = 1;i<10;i++){
            list1.addNode(i);
            list2.addNode(i,true);
        }
        System.out.println(list1);
        for(int i = 1;i<6;i++){
            list1.removeNode(i);
            if(i==1){
                System.out.println(list1);
            }
        }
        list1.modifyNode(6,7);
        System.out.println(list1);
        System.out.println(list1.searchNode(6));
        list1.removeNodeAll(7);
        System.out.println(list1);
        list1.extendList(list2);
        System.out.println(list1);

        list1 = new LinkedList();
        int [] num = {7,7,7,8,7,6,6,7};
        for(int k:num){
            list1.addNode(k);
        }
        System.out.println(list1);
        list1.removeNodeAll(7);
        System.out.println(list1);
    }
}