package conjuntistas.funcionesHash;

public class funciones {
    
    public static int hashDoblamiento(int n){

        int salida = 0;
        // Pasamos a string para partirlo

        String s=String.valueOf(n);

        // Separamos el string en 3 grupos
        String s1 = s.substring(0, s.length()/3);
        String s2 = s.substring(s.length()/3, s.length()*2/3);
        String s3 = s.substring(s.length()*2/3, s.length());

        salida = Integer.valueOf(s1) + Integer.valueOf(s2) + Integer.valueOf(s3);

        return salida;
    }

    public static int hashCadena(String n){

        int suma = 0;
        int i;

        for(i=0;i<n.length();i++){
            int asciiValue = n.charAt(i);
            suma += asciiValue;
        }

        return suma;

    }

    public static int rehash(Object elem){

        return elem.hashCode() + 1 ;
    }

    private static int h2(Object elem){

        int salida;

        String s=String.valueOf(elem);

        String s1 = s.substring(0, 0);

        salida = Integer.valueOf(s1);

        return salida;
    }

    public static int rehashDoble(int pos, Object elem){

        return pos + h2(elem);
    }


}
