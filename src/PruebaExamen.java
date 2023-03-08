public class PruebaExamen {
    public static float [] fuerza(){
        float [] res = new float [2];
        int i =0, j = 0;
        float max = 0;
        while(5*i+20*j <= 291){
            while(5*i+20*j <= 291){
                if (3*i*j+9*j >= max){
                    res[0] = i;
                    res[1] = j;
                    max = 3*i*j+9*j;
                }
                j++;
            }
            j = 1;
            i++;
        }
        return res;
    }
    public static void main(String[] args) {
        float [] f = fuerza();
        System.out.println(f[0]);
    }
}
