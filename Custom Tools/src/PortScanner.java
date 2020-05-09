import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.net.Socket;
import java.util.Set;
import java.util.TreeSet;


public class PortScanner {
    final static int MIN_PORT = 1024;
    final static int MAX_PORT = 49151;

    public static void main(String[] args) throws Exception {
        Scanner reader = new Scanner(System.in);

        System.out.println("which address should I scan?");
        String target = reader.nextLine();
        System.out.println("Tell me what range to scan:");
        System.out.println("Starting port?");
        int startPort = Integer.valueOf(reader.nextLine());
        System.out.println("Ending port?");
        int endPort = Integer.valueOf(reader.nextLine());

        Set<Integer> ports = getAccessiblePorts(target, startPort, endPort);
        System.out.println("");

        if(ports.isEmpty()) {
            System.out.println("None found:(");
        } else {
            System.out.println("found:");
            ports.stream()
                    .forEach(p -> System.out.println("\t" + p));
        }
    }

    public static Set<Integer> getAccessiblePorts(String host, int start, int end) {
        Set<Integer> accessiblePorts = new TreeSet<>();
        start = Math.max(start, MIN_PORT);
        end = Math.min(end, MAX_PORT);
        for (int i = start; i <= end; i++){
            try {
                Socket socket = new Socket(host, i);
                accessiblePorts.add(i);
            } catch (UnknownHostException f) {
                System.out.println("Connection to host refused.");
            } catch (IOException e) {
                System.out.println("Port: "+i+" Closed");
            }
        }
        return accessiblePorts;
    }
}
