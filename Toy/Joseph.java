import java.util.Scanner;

class People {
    private static int maxNum;
    private int num;
    private int password;
    private People next;

    public People(int num, int password) {
        this.num = num;
        this.password = password;
        if (num < maxNum) {
            System.out.printf("请输入第%d个人的password:",num+1);
            next = new People(num + 1, new Scanner(System.in).nextInt());
        }
    }

    public static void setMaxNum(int maxnum) {
        People.maxNum = maxnum;
    }

    public static People out(People head, int begin, int pd) {
        People dead = null, tem = head;
        if (head.num == begin) {
            dead = head;
            while (tem.next != head)
                tem = tem.next;
            head = tem;
        } else {
            while (head.next.num != begin) {
                head = head.next;
            }
        }
        //System.out.println(dead.num+","+head.num);
        while (head.next != head) {
            pd %= maxNum;
            if(pd == 0)pd = maxNum;
            for (int i = 1; i < pd; head = head.next,i++);//head为起始前一个，dead = head.next
            dead = head.next;
            pd = dead.password;
            head.next = dead.next;
            System.out.println(dead.num + "号出列,password为"+dead.password);
            print(dead.next);
            dead = null;
            maxNum--;
            //head = head.next;
        }
        return head;
    }

    public People getNext() {
        return next;
    }

    public void setNext(People next) {
        this.next = next;
    }

    public static void print(People head){
        System.out.print(head.num+" ");
        People p = head;
        for(head = head.next;p!=head;head= head.next)
            System.out.print(head.num+" ");
        System.out.println();
    }

    public int getNum() {
        return num;
    }

    public int getPassword() {
        return password;
    }
}

public class Joseph {

    public static void main(String[] args) {
        System.out.print("请输入人数,起始编号和起始password:");
        Scanner input = new Scanner(System.in);
        People.setMaxNum(input.nextInt());
        int begin = input.nextInt();
        int password = input.nextInt();
        System.out.print("请输入第1个人的password:");
        People one = new People(1, input.nextInt());
        People tem = one;
        while (tem.getNext() != null) {
            tem = tem.getNext();
        }
        tem.setNext(one);//首尾相连
        People.print(one);
        tem = People.out(one, begin, password);
        System.out.printf("最后的人是%d号，其password为%d\n",tem.getNum(),tem.getPassword());
    }
}
