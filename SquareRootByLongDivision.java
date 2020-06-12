import java.util.Scanner;
public class SquareRootByLongDivision{
    static Scanner sc = new Scanner(System.in);
    static int noofdig(int n){
        int cnt = 0;
        while(n>0){
            n/=10;
            cnt++;
        }
        return cnt;
    }
    static int[] splitarr(int n){
        int[] arr = new int[noofdig(n)];
        int cnt = 0;
        int n1 = noofdig(n); int n2 = n;
        while(n>0){
            arr[cnt] = n%10;
            n/=10;
            cnt++;
        }
        int[] a = new int[n1];
        cnt = 0;
        while(cnt<noofdig(n2)){
            a[cnt]=0;
            a[cnt] = arr[--n1];
            cnt++;
        }
        return a;
    }
    static void nearPerfectSquare(int n, double qr[]){
        int i;
        for(i = 0; i*i<=n;i++) ;
        i--;
        qr[0] = i;
        qr[1] = n - i*i;
    }
    static int[] createGroup(int[] a, int cnt, int index){
        int ga[] = new int[cnt];
        for(int i = 0;index<a.length;index++,i++){
            ga[i]=a[index]*10 +a[++index];
            //System.out.println(ga[i]);
        }
        return ga;
    }
    static int floorDiviser(int d, int r){
        d *= 10;
        int i = 0;
        for (i = 0; i<10; i++){
            if(r-(d+i)*i < (d+i)) break;
        }
        return i;
    }
    static void longDivision(int ga[], double qr[]){
        int divisor = (int)qr[0];
        int remainder = (int)qr[1];
        for(int i = 0; i<ga.length; i++) {
            divisor += divisor % 10;
            remainder = remainder * 100 + ga[i];
            int j = floorDiviser(divisor, remainder);
            qr[0] =qr[0]*10 + j ;
            divisor = divisor*10 + j;
            remainder -= divisor*j;
        }
        qr[1] = remainder;
        //System.out.println(qr[0] + "\n" + qr[1]);
    }
    static double decimalPart(double[] qr){
        int divisor = (int)qr[0]*2;
        qr[1] *= 100;
        int i = floorDiviser(divisor, (int)qr[1]);
        divisor = divisor*10 + i;
        qr[1] -= divisor*i;
        qr[0] += i*0.1;
        qr[1] *= 100;
        i = floorDiviser(divisor, (int)qr[1]);
        divisor = divisor*10 + i;
        qr[1] -= divisor*i;
        qr[0] += i*0.01;
        return qr[0];
    }
    public static void main(String args[]){
        System.out.println("Enter the integer:");
        int n = sc.nextInt();
        int[] a = splitarr(n);
        double[] qr = {0.0,0.0};
        if(n<=0){
            System.out.println("Number less than or equal to 0");
            System.exit(0);
        }
        int index = 0;
        //Solving the first group
        boolean startdigbool = (noofdig(n)%2==0)?true:false;
        int temp = (startdigbool)?(a[0]*10+a[1]):(a[0]);
        index += startdigbool?2:1;//System.out.println(index);
        nearPerfectSquare(temp, qr);
        //Dividing the remaining into groups
        if (noofdig(n)>2) {
            int groupedArray[] = new int[noofdig(n) / 2 - (index-1)];
            groupedArray = createGroup(a, (noofdig(n) / 2 - (index - 1)), index);
            longDivision(groupedArray, qr);
        }
        if(qr[1]==0){
            System.out.println(qr[0]);
        }
        else System.out.println(decimalPart(qr));
    }
}